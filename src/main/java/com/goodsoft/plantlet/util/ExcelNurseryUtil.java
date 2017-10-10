package com.goodsoft.plantlet.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * function 苗圃Excel表格工具类
 * Created by 严彬荣 on 2017/9/4.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class ExcelNurseryUtil {
    /**
     * 创建ExcelUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static ExcelNurseryUtil instance;

    private ExcelNurseryUtil() {
    }

    public static ExcelNurseryUtil getInstance() {
        if (instance == null) {
            synchronized (ExcelNurseryUtil.class) {
                if (instance == null)
                    instance = new ExcelNurseryUtil();
            }
        }
        return instance;
    }

    //实例化获取服务器系统标识工具类
    private GetOsNameUtil getOs = GetOsNameUtil.getInstance();
    //实例化创建excel工具类
    private WriteNurseryExcelUtil writeExcel = WriteNurseryExcelUtil.getInstance();

    /**
     * @param list
     * @param path
     * @return excel路径地址
     * @throws Exception
     */
    public String writeExcel(List list, String type) throws Exception {
        boolean bl = this.getOs.getOsName();
        StringBuilder sb = new StringBuilder();
        if (bl) {
            sb.append("/usr/plantlet");
        } else {
            sb.append("D:/plantlet");
        }
        String path = "";
        switch (type) {
            case "in":
                path = "/plfile/GS586B9BB86C49D98F07280F89C88B66.xlsx";
                sb.append(path);
                break;
            case "out":
                path = "/plfile/GS586B9BB86C49D98F07280F89C88B67.xlsx";
                sb.append(path);
                break;
            default:
                break;
        }
        File file = new File(sb.toString());
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook();
            this.writeExcel.createExcel(wb, list, type).write(new BufferedOutputStream(fileOut));
        } finally {
            fileOut.close();
        }
        return path;
    }
}
