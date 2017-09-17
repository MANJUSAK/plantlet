package com.goodsoft.plantlet.util;

/**
 * function 获取操作系统类型工具类
 * Created by 严彬荣 on 2017/9/8.
 */
@SuppressWarnings("ALL")
public class GetOsName {
    /**
     * 创建本类的单例模式（具体说明参见本包下的UUIDUtil类） start
     */
    private volatile static GetOsName instance;

    private GetOsName() {
    }

    public static GetOsName getInstance() {
        if (instance == null) {
            synchronized (GetOsName.class) {
                if (instance == null) {
                    instance = new GetOsName();
                }
            }
        }
        return instance;
        //创建本类的单例模式（具体说明参见本包下的UUIDUtil类） end
    }

    //定义初始变量为Linux系统
    private final String OSNAME = "linux";

    /**
     * 获取操作系统类型方法
     *
     * @return 操作系统类型
     */

    public boolean getOsName() {
        String osName = System.getProperty("os.name").toLowerCase();
        switch (osName) {
            case OSNAME:
                return true;
            default:
                return false;
        }
    }

}
