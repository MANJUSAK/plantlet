package com.goodsoft.plantlet.util;

import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.AnalysisParam;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * function 省内苗圃超大excel读取工具类
 * Created by 严彬荣 on 2017/9/29.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class ReadExcelOutUtil extends DefaultHandler {
    /**
     * 创建ExcelUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static ReadExcelOutUtil instance;

    private ReadExcelOutUtil() {
    }

    public static ReadExcelOutUtil getInstance() {
        if (instance == null) {
            synchronized (ReadExcelOutUtil.class) {
                if (instance == null)
                    instance = new ReadExcelOutUtil();
            }
        }
        return instance;
    }

    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();
    // 格式化日期字符串
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    // 格式化数字
    private DecimalFormat db = new DecimalFormat("0.000");
    private DecimalFormat in = new DecimalFormat("0");
    // 共享字符串表
    private SharedStringsTable sst;
    // 上一次的内容
    private String lastContents;
    private boolean nextIsString;
    private int sheetIndex = -1;
    private List<String> rowList = new ArrayList<String>();
    public List<NurseryOut> list = new ArrayList<NurseryOut>();
    private String str = "";
    // 当前行
    private int curRow = 0;
    // 当前列
    private int curCol = 0;
    // 日期标志
    private boolean dateFlag;
    // 数字标志
    private boolean numberFlag;
    // 空值标志
    private boolean isTElement;

    /**
     * 遍历工作簿中所有的电子表格
     *
     * @param filename
     * @throws Exception
     */
    public void process(String filename) throws Exception {
        //由于容器为全局容器，每次封装excel数据时都清空一次容器里的数据。
        this.list.clear();
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        Iterator<InputStream> sheets = r.getSheetsData();
        while (sheets.hasNext()) {
            curRow = 0;
            sheetIndex++;
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
        pkg.close();
    }

    /**
     * 只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从1开始，1-3
     *
     * @param filename
     * @param sheetId
     * @throws Exception
     */
    public void process(String filename, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        // 根据 rId# 或 rSheet# 查找sheet
        InputStream sheet2 = r.getSheet("rId" + sheetId);
        sheetIndex++;
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        // c => 单元格
        if ("c".equals(name)) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            if ("s".equals(cellType)) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
            // 日期格式
            String cellDateType = attributes.getValue("s");
            if ("1".equals(cellDateType)) {
                dateFlag = true;
            } else {
                dateFlag = false;
            }
            String cellNumberType = attributes.getValue("s");
            if ("2".equals(cellNumberType)) {
                numberFlag = true;
            } else {
                numberFlag = false;
            }
        }
        // 当元素为t时
        if ("t".equals(name)) {
            isTElement = true;
        } else {
            isTElement = true;
        }
        // 置空
        lastContents = "";
    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            } catch (Exception e) {
            }
        }
        // t元素也包含字符串
        if (isTElement) {
            String value = lastContents.trim();
            rowList.add(curCol, value);
            curCol++;
            isTElement = false;
            // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
        } else if ("v".equals(name)) {
            String value = lastContents.trim();
            value = value.equals("") ? " " : value;
            try {
                // 日期格式处理
                if (dateFlag) {
                    Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    value = dateFormat.format(date);
                }
                // 数字类型处理
                if (numberFlag) {
                    BigDecimal bd = new BigDecimal(value);
                    value = bd.setScale(3, BigDecimal.ROUND_UP).toString();
                }
            } catch (Exception e) {
                // 转换失败仍用读出来的值
                e.printStackTrace();
            }
            rowList.add(curCol, value);
            curCol++;
        } else {
            // 如果标签名称为 row ，这说明已到行尾，调用 getRows() 方法
            if (name.equals("row")) {
                try {
                    getRows(sheetIndex + 1, curRow, rowList);
                } catch (Exception e) {
                }
                rowList.clear();
                curRow++;
                curCol = 0;
            }
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // 得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    /**
     * 获取行数据回调
     *
     * @param sheetIndex
     * @param curRow
     * @param rowList
     */
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {
        NurseryOut sd = new NurseryOut();
        for (int i = 0, len = rowList.size(); i < len; ++i) {
            this.str = rowList.get(i);
            switch (i) {
                case 0:
                    sd.setId(this.uuid.getUUID().toString());
                    break;
                case 1:
                    sd.setProvince(this.str);
                    break;
                case 2:
                    sd.setCompany(this.str);
                    break;
                case 3:
                    sd.setAddress(this.str);
                    break;
                case 4:
                    try {
                        if (this.str != "") {
                            long tel = Long.parseLong(this.str);
                            sd.setTel(tel);
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 5:
                    sd.setFax(this.str);
                    break;
                case 6:
                    sd.setWebSite(this.str);
                    break;
                case 7:
                    sd.setEmail(this.str);
                    break;
                case 8:
                    sd.setSeedlingName(this.str);
                    break;
                case 9:
                    if (this.str != "") {
                        try {
                            AnalysisParam var = this.analysisUtil.getSpecAnalysis(this.str);
                            sd.setSpec(var.getStr());
                            sd.setSpecMin(var.getNum());
                            sd.setSpecMax(var.getNum_1());
                        } catch (Exception e) {
                            sd.setSpec("");
                        }
                    } else {
                        sd.setSpec("");
                    }
                    break;
                case 10:
                    sd.setUnit(this.str);
                    break;
                case 11:
                    try {
                        if (this.str != "") {
                            int num = Integer.parseInt(this.str);
                            sd.setNum(num);
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 12:
                    try {
                        if (this.str != "") {
                            double price = Double.parseDouble(this.str);
                            sd.setPrice(price);
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 13:
                    sd.setTypes(this.str);
                    break;
                case 14:
                    try {
                        if (this.str != "") {
                            Date data = HSSFDateUtil.getJavaDate(Double.parseDouble(this.str));
                            sd.setDate(this.sdf.format(data));
                        } else {
                            sd.setDate("");
                        }
                    } catch (Exception e) {
                        sd.setDate(this.str);
                    }
                    break;
                case 15:
                    sd.setComment(this.str);
                    break;
                default:
                    break;
            }
        }
        list.add(sd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadExcelOutUtil)) return false;
        ReadExcelOutUtil that = (ReadExcelOutUtil) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
