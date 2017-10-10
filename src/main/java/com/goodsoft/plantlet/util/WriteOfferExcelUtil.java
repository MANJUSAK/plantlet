package com.goodsoft.plantlet.util;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOfferStatistics;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * function 造价excel导出工具类
 * Created by 严彬荣 on 2017/10/9.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class WriteOfferExcelUtil {
    /**
     * 创建WriteOfferExcelUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static WriteOfferExcelUtil instance;

    private WriteOfferExcelUtil() {
    }

    public static WriteOfferExcelUtil getInstance() {
        if (instance == null) {
            synchronized (WriteOfferExcelUtil.class) {
                if (instance == null)
                    instance = new WriteOfferExcelUtil();
            }
        }
        return instance;
        //创建WriteOfferExcelUtil类的单例（详情见本包下UUIDUtil类） end
    }

    /**
     * 创建excel表格
     * 将表单数据写入excel表格中
     *
     * @param list  写入到表格数据
     * @param year  年份
     * @param month 月份
     * @return XSSFWorkbook
     */
    protected XSSFWorkbook createExcel(XSSFWorkbook wb, List list, String type, int year, int month) {
        return this.createHeaderStyle(wb, list, type, year, month);
    }

    /**
     * 创建excel表头样式表格
     *
     * @param list 写入到表格数据
     * @return XSSFWorkbook
     */
    private XSSFWorkbook createHeaderStyle(XSSFWorkbook wb, List list, String type, int year, int month) {
        XSSFCellStyle style = wb.createCellStyle();
        //水平居中
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置单元格背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //设置上下左右边框
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        // 自动换行
        style.setWrapText(true);
        XSSFFont font = wb.createFont();
        // 设置字体颜色
        font.setColor(HSSFColor.BLACK.index);
        // 设置字体样式
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 设置字体粗细
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式中
        style.setFont(font);
        return this.writeHeaderCellExcel(wb, style, list, type, year, month);
    }


    /**
     * 创建excel表头单元格
     * 将表单数据写入excel表格中
     *
     * @param wb
     * @param style
     * @param list  导出数据
     * @param type  导出类型
     * @return XSSFWorkbook
     */
    private XSSFWorkbook writeHeaderCellExcel(XSSFWorkbook wb, XSSFCellStyle style, List list, String type, int year, int month) {
        List data = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        XSSFSheet sheet = null;
        CellRangeAddress cra = null;
        //判断数据是否超过一万条
        if (size > 10000) {
            //算出sheet表数
            int i = (size + 10000) / 10000;
            for (int k = 1; k <= i; ++k) {
                //判断是否为最后一张sheet表
                if (k < i) {
                    //截取写入到分表的数据
                    data = list.subList(((k - 1) * 10000), k * 10000);
                    //创建一张excel表
                    switch (type) {
                        case "only":
                            sb.append("造价息信分期统计表（0表示无数据）");
                            sb.append(k);
                            sheet = wb.createSheet(sb.toString());
                            //合并单元格（第一行，第1-20列）
                            cra = new CellRangeAddress(0, 0, 0, 5);
                            break;
                        case "more":
                            sb.append("全年造价息信统计表（0表示无数据）");
                            sb.append(k);
                            sheet = wb.createSheet(sb.toString());
                            //合并单元格（第一行，第1-20列）
                            cra = new CellRangeAddress(0, 0, 0, 15);
                            break;
                        default:
                            break;
                    }
                    //在sheet里增加合并单元格
                    sheet.addMergedRegion(cra);
                    //获取第一行 写入标题名称
                    XSSFRow row = sheet.createRow(0);
                    //设置列宽
                    sheet.setDefaultColumnWidth(35);
                    //创建表头
                    row = sheet.createRow(0);
                    //设置行高
                    row.setHeight((short) (16 * 25));
                    XSSFCell cell = row.createCell(0);
                    switch (type) {
                        case "only":
                            sb.append(year);
                            sb.append("年");
                            sb.append(month);
                            sb.append("月");
                            sb.append("造价息信统计表（0表示无数据）");
                            cell.setCellValue(sb.toString());
                            break;
                        case "more":
                            sb.append(year);
                            sb.append("年");
                            sb.append("全年造价息信统计表（0表示无数据）");
                            cell.setCellValue(sb.toString());
                            break;
                        default:
                            break;
                    }
                    cell.setCellStyle(style);
                    writeHeaderCell(sheet, cell, style, type);
                    wb = this.createCellStyle(wb, sheet, data, type);
                } else {
                    data = list.subList(((k - 1) * 10000), size);
                    //创建一张excel表
                    switch (type) {
                        case "only":
                            sb.append("造价息信统计表（0表示无数据）");
                            sb.append(k);
                            sheet = wb.createSheet(sb.toString());
                            //合并单元格（第一行，第1-20列）
                            cra = new CellRangeAddress(0, 0, 0, 5);
                            break;
                        case "more":
                            sb.append("全年造价息信统计表（0表示无数据）");
                            sb.append(k);
                            sheet = wb.createSheet(sb.toString());
                            //合并单元格（第一行，第1-20列）
                            cra = new CellRangeAddress(0, 0, 0, 15);
                            break;
                        default:
                            break;
                    }
                    //在sheet里增加合并单元格
                    sheet.addMergedRegion(cra);
                    XSSFRow row = sheet.createRow(0);
                    //设置列宽
                    sheet.setDefaultColumnWidth(35);
                    //创建表头
                    row = sheet.createRow(0);
                    //设置行高
                    row.setHeight((short) (16 * 25));
                    XSSFCell cell = row.createCell(0);
                    switch (type) {
                        case "only":
                            sb.append(year);
                            sb.append("年");
                            sb.append(month);
                            sb.append("月");
                            sb.append("造价息信统计表（0表示无数据）");
                            cell.setCellValue(sb.toString());
                            break;
                        case "more":
                            sb.append(year);
                            sb.append("年");
                            sb.append("全年造价息信统计表（0表示无数据）");
                            cell.setCellValue(sb.toString());
                            break;
                        default:
                            break;
                    }
                    cell.setCellStyle(style);
                    writeHeaderCell(sheet, cell, style, type);
                    wb = this.createCellStyle(wb, sheet, data, type);
                }
            }
            return wb;
        } else {
            //创建一张excel表
            switch (type) {
                case "only":
                    sheet = wb.createSheet("造价息信分期统计表（0表示无数据）");
                    //合并单元格（第一行，第1-20列）
                    cra = new CellRangeAddress(0, 0, 0, 5);
                    break;
                case "more":
                    sheet = wb.createSheet("全年造价息信统计表（0表示无数据）");
                    //合并单元格（第一行，第1-20列）
                    cra = new CellRangeAddress(0, 0, 0, 15);
                    break;
                default:
                    break;
            }
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
            XSSFRow row = sheet.createRow(0);
            //设置列宽
            sheet.setDefaultColumnWidth(35);
            //创建表头
            row = sheet.createRow(0);
            //设置行高
            row.setHeight((short) (16 * 25));
            XSSFCell cell = row.createCell(0);
            switch (type) {
                case "only":
                    sb.append(year);
                    sb.append("年");
                    sb.append(month);
                    sb.append("月");
                    sb.append("造价息信统计表（0表示无数据）");
                    cell.setCellValue(sb.toString());
                    break;
                case "more":
                    sb.append(year);
                    sb.append("年");
                    sb.append("全年造价息信统计表（0表示无数据）");
                    cell.setCellValue(sb.toString());
                    break;
                default:
                    break;
            }
            cell.setCellStyle(style);
            //写入字段名称
            writeHeaderCell(sheet, cell, style, type);
            return this.createCellStyle(wb, sheet, list, type);
        }
    }

    /**
     * excel表头信息
     *
     * @param sheet
     * @param cell
     * @param style
     */
    private void writeHeaderCell(XSSFSheet sheet, XSSFCell cell, XSSFCellStyle style, String type) {
        XSSFRow row = sheet.createRow(1);
        // 创建表头单元格
        switch (type) {
            case "only":
                cell = row.createCell(0);
                cell.setCellValue("序号");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("苗木名称");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("规格（cm）");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("单位");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("造价（元）");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("备注");
                cell.setCellStyle(style);
                break;
            case "more":
                cell = row.createCell(0);
                cell.setCellValue("序号");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("苗木名称");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("规格（cm）");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("单位");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("1月");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("2月");
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue("3月");
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue("4月");
                cell.setCellStyle(style);
                cell = row.createCell(8);
                cell.setCellValue("5月");
                cell.setCellStyle(style);
                cell = row.createCell(9);
                cell.setCellValue("6月");
                cell.setCellStyle(style);
                cell = row.createCell(10);
                cell.setCellValue("7月");
                cell.setCellStyle(style);
                cell = row.createCell(11);
                cell.setCellValue("8月");
                cell.setCellStyle(style);
                cell = row.createCell(12);
                cell.setCellValue("9月");
                cell.setCellStyle(style);
                cell = row.createCell(13);
                cell.setCellValue("10月");
                cell.setCellStyle(style);
                cell = row.createCell(14);
                cell.setCellValue("11月");
                cell.setCellStyle(style);
                cell = row.createCell(15);
                cell.setCellValue("12月");
                cell.setCellStyle(style);
                break;
            default:
                break;
        }
    }

    /**
     * 创建excel单元格样式
     * 将表单数据写入excel表格中
     *
     * @param wb    表属性
     * @param sheet 表
     * @param list  写入到表格数据
     * @return XSSFWorkbook
     */
    private XSSFWorkbook createCellStyle(XSSFWorkbook wb, XSSFSheet sheet, List list, String type) {
        XSSFCellStyle style = wb.createCellStyle();
        //水平居中
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置上下左右边框
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        //设置自动换行
        //style.setWrapText(true);
        Font font = wb.createFont();
        // 设置字体颜色
        font.setColor(HSSFColor.BLACK.index);
        // 设置字体样式
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 9);
        // 设置字体粗细
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式中
        style.setFont(font);
        return this.nursery(wb, sheet, style, list, type);
    }

    /**
     * 创建excel单元格内容
     * 将表单数据写入excel表格中
     *
     * @param wb    表属性
     * @param sheet 表
     * @param style 表样式
     * @param list  写入到表格数据
     * @return XSSFWorkbook
     */
    private XSSFWorkbook nursery(XSSFWorkbook wb, XSSFSheet sheet, XSSFCellStyle style, List list, String type) {
        XSSFCell cell = null;
        switch (type) {
            case "only":
                for (int i = 0, length = list.size(); i < length; ++i) {
                    XSSFRow row = sheet.createRow(2 + i);
                    SeedlingOffer data = (SeedlingOffer) list.get(i);
                    //写入数据到sheet表中
                    writeData(row, cell, style, data, null, type, (1 + i));
                }
                break;
            case "more":
                for (int i = 0, length = list.size(); i < length; ++i) {
                    XSSFRow row = sheet.createRow(2 + i);
                    SeedlingOfferStatistics data = (SeedlingOfferStatistics) list.get(i);
                    //写入数据到sheet表中
                    writeData(row, cell, style, null, data, type, (1 + i));
                }
                break;
            default:
                break;
        }
        return wb;
    }

    /**
     * 写入数据到excel表中
     *
     * @param row
     * @param cell
     * @param style
     * @param data    省内苗圃数据
     * @param dataOut 省外苗圃数据
     * @param type    数据类型
     * @param i       序号
     */
    private void writeData(XSSFRow row, XSSFCell cell, XSSFCellStyle style, SeedlingOffer data, SeedlingOfferStatistics dataSta, String type, int i) {
        StringBuilder sb = new StringBuilder();
        // 创建表内容单元格
        switch (type) {
            case "only":
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(data.getSdName());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                String spec = data.getSpec();
                if (!"".equals(spec)) {
                    if (data.getSpecMax() != 0) {
                        sb.append(spec);
                        sb.append(data.getSpecMin());
                        sb.append("-");
                        sb.append(data.getSpecMax());
                        cell.setCellValue(sb.toString());
                    } else {
                        sb.append(spec);
                        sb.append(data.getSpecMin());
                        cell.setCellValue(sb.toString());
                    }
                }
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(data.getUnit());
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue(data.getSdOffer());
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue(data.getComment());
                cell.setCellStyle(style);
                break;
            case "more":
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(dataSta.getSdName());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                String specs = dataSta.getSpec();
                if (!"".equals(specs)) {
                    if (dataSta.getSpecMax() != 0) {
                        sb.append(specs);
                        sb.append(dataSta.getSpecMin());
                        sb.append("-");
                        sb.append(dataSta.getSpecMax());
                        cell.setCellValue(sb.toString());
                    } else {
                        sb.append(specs);
                        sb.append(dataSta.getSpecMin());
                        cell.setCellValue(sb.toString());
                    }
                }
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(dataSta.getUnit());
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue(dataSta.getJan());
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue(dataSta.getFeb());
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue(dataSta.getMar());
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue(dataSta.getApr());
                cell.setCellStyle(style);
                cell = row.createCell(8);
                cell.setCellValue(dataSta.getMay());
                cell.setCellStyle(style);
                cell = row.createCell(9);
                cell.setCellValue(dataSta.getJun());
                cell.setCellStyle(style);
                cell = row.createCell(10);
                cell.setCellValue(dataSta.getJul());
                cell.setCellStyle(style);
                cell = row.createCell(11);
                cell.setCellValue(dataSta.getAug());
                cell.setCellStyle(style);
                cell = row.createCell(12);
                cell.setCellValue(dataSta.getSep());
                cell.setCellStyle(style);
                cell = row.createCell(13);
                cell.setCellValue(dataSta.getOct());
                cell.setCellStyle(style);
                cell = row.createCell(14);
                cell.setCellValue(dataSta.getNov());
                cell.setCellStyle(style);
                cell = row.createCell(15);
                cell.setCellValue(dataSta.getDec());
                cell.setCellStyle(style);
                break;
            default:
                break;
        }
    }

}
