package com.goodsoft.plantlet.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * function Excel表格工具类
 * Created by 严彬荣 on 2017/9/4.
 * version v1.0
 */
@SuppressWarnings("ALL")
public class ExcelUtil {
    /**
     * 创建ExcelUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static ExcelUtil instance;

    private ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        if (instance == null) {
            synchronized (ExcelUtil.class) {
                if (instance == null)
                    instance = new ExcelUtil();
            }
        }
        return instance;
    }

    //实例化excel读取工具类
    private Excel2003 excel2003 = Excel2003.getInstance();
    private Excel2007 excel2007 = Excel2007.getInstance();

    /**
     * 读取excel
     *
     * @param path   文件路径
     * @param fileId 文件编号
     * @return 读取数据
     * @throws Exception
     */
    public List<List<Object>> readExcel(String path, String fileId) throws Exception {
        //获取文件 start
        File file = new File(path);
        FileInputStream is = new FileInputStream(file);
        String fileName = file.getName();
        //获取文件 end
        //读取excel start
        XSSFWorkbook wb2007;
        HSSFWorkbook wb2003;
        try {
            if (fileName.endsWith("xlsx")) {
                wb2007 = new XSSFWorkbook(is);
                //读取excel end
                return this.excel2007.getSheetExcel(wb2007, fileId);
            } else {
                wb2003 = new HSSFWorkbook(is);
                //读取excel end
                return this.excel2003.getSheetExcel(wb2003, fileId);
            }
        } finally {
            is.close();
        }
    }

    public String writeExcel(List<T> list) {
        return "";
    }
}
