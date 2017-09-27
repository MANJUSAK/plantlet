package com.goodsoft.plantlet;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ASUS on 2017/9/27.
 */
@SuppressWarnings("ALL")
public class Tests {

    public static void main(String[] args) {
        try {
            writeExcel(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String writeExcel(List<T> list, String path) throws Exception {
        String p = "D://plantlet/plfile/excel/20170827.xlsx";
        File file = new File(p);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook();
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 9);
        XSSFSheet sheet = wb.createSheet("1");
        /*sheet = wb.createSheet("2");*/
        //在sheet里增加合并单元格
        sheet.addMergedRegion(cra);
        Row row = sheet.createRow(0);
        Cell cell_1 = row.createCell(0);
        cell_1.setCellValue("When you're right , no one remembers, when you're wrong ,no one forgets .");
        //cell 位置3-9被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。
        Cell cell_2 = row.createCell(10);
        cell_2.setCellValue("what's up ! ");
        wb.write(out);
        out.close();
        return p;
    }
}
