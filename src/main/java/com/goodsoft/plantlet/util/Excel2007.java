package com.goodsoft.plantlet.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * function xlxs文件格式excel表读取工具类
 * Created by 严彬荣 on 2017/9/17.
 */
@SuppressWarnings("ALL")
public class Excel2007 {
    /**
     * 创建Excel2007类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static Excel2007 instance;

    private Excel2007() {
    }

    public static Excel2007 getInstance() {
        if (instance == null) {
            synchronized (Excel2007.class) {
                if (instance == null)
                    instance = new Excel2007();
            }
        }
        return instance;
    }

    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    // 默认单元格格式化日期字符串
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 格式化数字
    private DecimalFormat nf = new DecimalFormat("0");

    /**
     * 读取excel表
     *
     * @param fileId 文件编号
     * @param wb     读取到的表格
     * @return 读取数据
     */
    public List<List<Object>> getSheetExcel(XSSFWorkbook wb, String fileId) {
        List<List<Object>> list = null;
        //读取表格数 start
        int st = wb.getNumberOfSheets();
        //遍历excel表格数
        XSSFSheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            list = getRowExcel(sheet, fileId);
        }
        //读取表格数 end
        return list;
    }


    /**
     * 读取表格数据
     *
     * @param fileId 文件编号
     * @param sheet  读取的表
     * @return 读取数据
     */
    private List<List<Object>> getRowExcel(XSSFSheet sheet, String fileId) {
        // 默认单元格格式化日期字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化数字
        DecimalFormat nf = new DecimalFormat("0");
        //初始化集合存放读取表格数据
        List<List<Object>> list = new ArrayList<List<Object>>();
        //初始化集合存放读取表格临时数据
        List<Object> temp = null;
        XSSFRow row;
        XSSFCell cell;
        //读取表格中的数据 start
        int rowCount = sheet.getLastRowNum();
        //遍历行
        for (int i = sheet.getFirstRowNum() + 2; i <= rowCount; ++i) {
            temp = new ArrayList<Object>();
            row = sheet.getRow(i);
            //判断读取行是否为空且是否为最后一行
            if (row != null && i != rowCount) {
                temp.add(this.uuid.getUUID().toString());
                //遍历列
                for (int j = row.getFirstCellNum() + 1, cellCount = row.getLastCellNum(); j < cellCount; ++j) {
                    boolean tip = isMergedRegion(sheet, i, j);
                    if (tip) {
                        temp.add(getMergedRegionValue(sheet, i, j));
                        continue;
                    }
                    cell = row.getCell(j);
                    if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_STRING:
                                temp.add(cell.getStringCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_FORMULA:
                                temp.add(cell.getNumericCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                switch (cell.getCellStyle().getDataFormatString()) {
                                    case "General":
                                        temp.add(this.nf.format(cell.getNumericCellValue()));
                                        break;
                                    case "@":
                                        temp.add(this.nf.format(cell.getNumericCellValue()));
                                        break;
                                    default:
                                        String len = String.valueOf(cell.getNumericCellValue());
                                        if (len.length() > 10) {
                                            temp.add(this.sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())));
                                        } else {
                                            temp.add(this.nf.format(cell.getNumericCellValue()));
                                        }
                                        break;
                                }
                                break;
                            case XSSFCell.CELL_TYPE_BOOLEAN:
                                temp.add(cell.getBooleanCellValue());
                                break;
                            default:
                                break;
                        }
                    } else {
                        temp.add("");
                    }
                }
            }
            list.add(temp);
        }
        //读取表格中的数据 end
        return list;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return boolean
     */
    private boolean isMergedRegion(XSSFSheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet  表
     * @param row    行下标
     * @param column 列下标
     * @return 单元格数据
     */
    private Object getMergedRegionValue(XSSFSheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    XSSFRow fRow = sheet.getRow(firstRow);
                    XSSFCell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }
        return null;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public Object getCellValue(XSSFCell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                return this.nf.format(cell.getNumericCellValue());
            default:
                return "";
        }
    }
}
