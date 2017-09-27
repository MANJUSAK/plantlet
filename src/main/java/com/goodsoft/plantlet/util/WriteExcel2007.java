package com.goodsoft.plantlet.util;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * function 写入数据到excel表工具类
 * Created by 严彬荣 on 2017/9/27.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class WriteExcel2007 {
    /**
     * 创建Excel2007类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static WriteExcel2007 instance;

    private WriteExcel2007() {
    }

    public static WriteExcel2007 getInstance() {
        if (instance == null) {
            synchronized (WriteExcel2007.class) {
                if (instance == null)
                    instance = new WriteExcel2007();
            }
        }
        return instance;
    }

    /**
     * 创建excel表格
     * 将表单数据写入excel表格中
     *
     * @param list 写入到表格数据
     * @return XSSFWorkbook
     */
    protected XSSFWorkbook createExcel(XSSFWorkbook wb, List list, String type) {
        return this.createHeaderStyle(wb, list, type);
    }

    /**
     * 创建excel表头样式表格
     *
     * @param list 写入到表格数据
     * @return XSSFWorkbook
     */
    private XSSFWorkbook createHeaderStyle(XSSFWorkbook wb, List list, String type) {
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
        return this.writeHeaderCellExcel(wb, style, list, type);
    }

    /**
     * function 创建excel表头单元格
     * 将表单数据写入excel表格中
     *
     * @param wb 表属性，style 表样式，data 写入到表格数据
     * @return XSSFWorkbook
     */
    private XSSFWorkbook writeHeaderCellExcel(XSSFWorkbook wb, XSSFCellStyle style, List list, String type) {
        List data = new ArrayList();
        int size = list.size();
        XSSFSheet sheet = null;
        CellRangeAddress cra = null;
        if (size > 10000) {
            int i = (size + 10000) / 10000;
            for (int k = 1; k <= i; ++k) {
                if (k < i) {
                    data = list.subList(((k - 1) * 10000), k * 10000);
                    //创建一张excel表
                    switch (type) {
                        case "in":
                            sheet = wb.createSheet("省内苗圃信息表" + k);
                            cra = new CellRangeAddress(0, 0, 0, 19);
                            break;
                        case "out":
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
                    cell.setCellValue("苗圃信息数据");
                    cell.setCellStyle(style);
                    writeHeaderCell(sheet, cell, style, type);
                    wb = this.createCellStyle(wb, sheet, data, type);
                } else {
                    data = list.subList(((k - 1) * 10000), size);
                    //创建一张excel表
                    switch (type) {
                        case "in":
                            sheet = wb.createSheet("省内苗圃信息表" + k);
                            cra = new CellRangeAddress(0, 0, 0, 19);
                            break;
                        case "out":
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
                        case "in":
                            cell.setCellValue("省内苗圃信息数据");
                            break;
                        case "out":
                            cell.setCellValue("省外苗圃信息数据");
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
                case "in":
                    sheet = wb.createSheet("省内苗圃信息表1");
                    cra = new CellRangeAddress(0, 0, 0, 19);
                    break;
                case "out":
                    sheet = wb.createSheet("省外苗圃信息表1");
                    cra = new CellRangeAddress(0, 0, 0, 18);
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
                case "in":
                    cell.setCellValue("省内苗圃信息数据");
                    break;
                case "out":
                    cell.setCellValue("省外苗圃信息数据");
                    break;
                default:
                    break;
            }
            cell.setCellStyle(style);
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
            case "in":
                cell = row.createCell(0);
                cell.setCellValue("序号");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("数据编号");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("省份");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("市");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("所属区县");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("苗圃名称");
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue("地址");
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue("邮编");
                cell.setCellStyle(style);
                cell = row.createCell(8);
                cell.setCellValue("电话");
                cell.setCellStyle(style);
                cell = row.createCell(9);
                cell.setCellValue("传真");
                cell.setCellStyle(style);
                cell = row.createCell(10);
                cell.setCellValue("联系人");
                cell.setCellStyle(style);
                cell = row.createCell(11);
                cell.setCellValue("邮箱");
                cell.setCellStyle(style);
                cell = row.createCell(12);
                cell.setCellValue("植物名称");
                cell.setCellStyle(style);
                cell = row.createCell(13);
                cell.setCellValue("规格");
                cell.setCellStyle(style);
                cell = row.createCell(14);
                cell.setCellValue("数量");
                cell.setCellStyle(style);
                cell = row.createCell(15);
                cell.setCellValue("单价");
                cell.setCellStyle(style);
                cell = row.createCell(16);
                cell.setCellValue("种类");
                cell.setCellStyle(style);
                cell = row.createCell(17);
                cell.setCellValue("苗圃面积");
                cell.setCellStyle(style);
                cell = row.createCell(18);
                cell.setCellValue("生产许可证编号");
                cell.setCellStyle(style);
                cell = row.createCell(19);
                cell.setCellValue("经营许可证编号");
                cell.setCellStyle(style);
                break;
            case "out":
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
        for (int i = 0, length = list.size(); i < length; ++i) {
            XSSFRow row = sheet.createRow(2 + i);
            Nursery data = (Nursery) list.get(i);
            writeData(row, cell, style, data, type, (1 + i));
        }
        return wb;
    }

    private void writeData(XSSFRow row, XSSFCell cell, XSSFCellStyle style, Nursery data, String type, int i) {
        // 创建表内容单元格
        switch (type) {
            case "in":
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(data.getId());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(data.getProvince());
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(data.getDistricts());
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue(data.getCounty());
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue(data.getNurseryName());
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue(data.getNurseryAdd());
                cell.setCellStyle(style);
                cell = row.createCell(7);
                int code = data.getPostCode();
                if (code != 0)
                    cell.setCellValue(code);
                cell.setCellStyle(style);
                cell = row.createCell(8);
                long tel = data.getTel();
                if (tel != 0)
                    cell.setCellValue(tel);
                cell.setCellStyle(style);
                cell = row.createCell(9);
                cell.setCellValue(data.getFax());
                cell.setCellStyle(style);
                cell = row.createCell(10);
                cell.setCellValue(data.getContact());
                cell.setCellStyle(style);
                cell = row.createCell(11);
                cell.setCellValue(data.getEmail());
                cell.setCellStyle(style);
                cell = row.createCell(12);
                cell.setCellValue(data.getPlantName());
                cell.setCellStyle(style);
                cell = row.createCell(13);
                StringBuilder sb = new StringBuilder();
                String spec = data.getSpec();
                if (spec != "") {
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
                cell = row.createCell(14);
                double num = data.getNum();
                if (num != 0)
                    cell.setCellValue(data.getNum());
                cell.setCellStyle(style);
                cell = row.createCell(15);
                double price = data.getPrice();
                if (price != 0)
                    cell.setCellValue(data.getPrice());
                cell.setCellStyle(style);
                cell = row.createCell(16);
                cell.setCellValue(data.getTypes());
                cell.setCellStyle(style);
                cell = row.createCell(17);
                double area = data.getArea();
                if (area != 0)
                    cell.setCellValue(area);
                cell.setCellStyle(style);
                cell = row.createCell(18);
                cell.setCellValue(data.getProLicenseNum());
                cell.setCellStyle(style);
                cell = row.createCell(19);
                cell.setCellValue(data.getOperLicenseNum());
                cell.setCellStyle(style);
            case "out":
            default:
                break;
        }
    }
}
