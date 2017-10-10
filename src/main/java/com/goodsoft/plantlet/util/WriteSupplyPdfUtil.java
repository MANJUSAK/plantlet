package com.goodsoft.plantlet.util;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * function 供需数据pdf导出工具类
 * Created by 严彬荣 on 2017/10/10.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class WriteSupplyPdfUtil {
    /**
     * 创建WriteSupplyPdfUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static WriteSupplyPdfUtil instance;

    private WriteSupplyPdfUtil() {
    }

    public static WriteSupplyPdfUtil getInstance() {
        if (instance == null) {
            synchronized (WriteSupplyPdfUtil.class) {
                if (instance == null)
                    instance = new WriteSupplyPdfUtil();
            }
        }
        return instance;
        //创建WriteSupplyPdfUtil类的单例（详情见本包下UUIDUtil类） end
    }

    //实例化获取服务器系统标识工具类
    private GetOsNameUtil getOs = GetOsNameUtil.getInstance();

    public String writePdf(java.util.List<SupplyInfo> data) throws Exception {
        boolean bl = this.getOs.getOsName();
        StringBuilder sb = new StringBuilder();
        if (bl) {
            sb.append("/usr/plantlet");
        } else {
            sb.append("D:/plantlet");
        }
        String path = "";
        path = "/plfile/GS586B9BB86C49D98F07280F89C88B71.pdf";
        sb.append(path);
        //创建一个文档对象
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            // 定义输出位置并把文档对象装入输出对象中
            PdfWriter.getInstance(doc, new BufferedOutputStream(new FileOutputStream(sb.toString())));
            // 打开文档对象
            doc.open();
            // 设置中文字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font title = new Font(bfChinese, 16, Font.BOLD);
            Font FontChinese = new Font(bfChinese, 10, Font.BOLD);
            Font FontChinese1 = new Font(bfChinese, 8, Font.NORMAL);
            for (int i = 0, len = data.size(); i < len; ++i) {
                sb.delete(0, sb.length());
                // 新建章节
                Chapter chapter = new Chapter(0);
                Section section = chapter.addSection("", 0);
                section.add(new Paragraph(data.get(i).getSdName(), title));
                section.add(new Paragraph("\n", FontChinese));
                sb.delete(0, sb.length());
                sb.append("类型：");
                sb.append(data.get(i).getSdType());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("苗木企业：");
                sb.append(data.get(i).getSeedlingComp());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("地址：");
                sb.append(data.get(i).getSdAdd());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("联系人：");
                sb.append(data.get(i).getContact());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("联系方式：");
                sb.append(data.get(i).getTel());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("规格：");
                sb.append(data.get(i).getSpec());
                sb.append(data.get(i).getSpecMin());
                double max = data.get(i).getSpecMax();
                if (max != 0) {
                    sb.append("-");
                    sb.append(max);
                }
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("数量：");
                sb.append(data.get(i).getNum());
                sb.append(" ");
                sb.append(data.get(i).getUnit());
                section.add(new Paragraph(sb.toString(), FontChinese));
                sb.delete(0, sb.length());
                sb.append("价格：");
                double price = data.get(i).getPrice();
                if (price != 0) {
                    sb.append(data.get(i).getPrice());
                    sb.append(" ￥");
                }
                section.add(new Paragraph(sb.toString(), FontChinese));
                section.add(new Paragraph("\n"));
                section.add(new Paragraph("供需说明：", FontChinese));
                section.add(new Paragraph("\n"));
                sb.delete(0, sb.length());
                sb.append("        ");
                sb.append(data.get(i).getSeedlingIntro());
                section.add(new Paragraph(sb.toString(), FontChinese1));
                doc.add(chapter);
                String image = data.get(i).getDirectory();
                if (image != null) {
                    // 加入图片
                    Image jpg = Image.getInstance(data.get(i).getDirectory());
                    jpg.scaleAbsolute(200, 150);// 直接设定显示尺寸
                    jpg.setAbsolutePosition(335, 630);
                    doc.add(jpg);
                }
            }
            return path;
        } finally {
            // 关闭文档对象，释放资源
            doc.close();
        }
    }
}
