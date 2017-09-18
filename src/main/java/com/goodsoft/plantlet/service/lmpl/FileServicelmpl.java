package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.util.FileUpload;
import com.goodsoft.plantlet.util.GetOsName;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * function 文件上传业务接口实现类
 * Created by 严彬荣 on 2017/8/4.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class FileServicelmpl implements FileService {

    @Resource
    private FileUpload fileUpload;
    @Resource
    private FileDao dao;
    //实例化获取操作系统类型工具类
    private GetOsName getOsName = GetOsName.getInstance();
    //实例化日志管理
    private Logger logger = Logger.getLogger(FileServicelmpl.class);

    /**
     * 文件上传业务处理方法
     *
     * @param files    上传的文件,
     * @param fileType 上传文件类型（苗木、设备租赁等），
     * @param fileId   文件编号（用于查询文件）。
     * @return int 文件上传处理状态（0为成功，其余都失败）
     * @throws Exception
     */
    @Override
    @Transactional
    public int fileUploadService(MultipartFile[] files, String fileType, String fileId) {
        //判断文件是图片还是文档 start
        switch (fileType) {
            case "document":
                //判断文件是否为空
                if (!(files[0].isEmpty())) {
                    //判断文件大小是否小于30M start
                    if (files[0].getSize() > 30000000) {
                        return 601;
                    }
                    //判断文件大小是否小于30M end
                    // 获取文件名
                    String fileName = files[0].getOriginalFilename().toLowerCase();
                    // 判断文件格式是否正确 start
                    if (!(fileName.endsWith("doc") || fileName.endsWith("docx") || fileName.endsWith("pdf"))) {
                        return 603;
                    }
                    // 判断文件格式是否正确 end
                } else {
                    return 604;
                }
                // 判断文件格是否为空 end
                break;
            //判断文件是图片还是文档 end
            //判断文件是否为Excel start
            case "excel":
                //判断文件是否为空
                if (!(files[0].isEmpty())) {
                    //判断文件大小是否小于10M start
                    if (files[0].getSize() > 10000000) {
                        return 601;
                    }
                    //判断文件大小是否小于30M end
                    // 获取文件名
                    String fileName = files[0].getOriginalFilename().toLowerCase();
                    // 判断文件格式是否正确 start
                    if (!(fileName.endsWith("xlsx") || fileName.endsWith("xls"))) {
                        return 603;
                    }
                    // 判断文件格式是否正确 end
                } else {
                    return 604;
                }
                // 判断文件格是否为空 end
                break;
            //判断文件是否为Excel end
            //图片文件类型检查 start
            default:
                for (int i = 0, length = files.length; i < length; ++i) {
                    //判断文件是否为空 start
                    if (!(files[i].isEmpty())) {
                        //判断文件大小是否小于1.5M start
                        if (files[i].getSize() > 1500000) {
                            return 601;
                        }
                        //判断文件大小是否小于1.5M start
                        // 获取文件名
                        String fileName = files[i].getOriginalFilename().toLowerCase();
                        // 判断文件格式是否正确 start
                        if (!(fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("png") || fileName.endsWith("gif"))) {
                            return 603;
                        }
                        // 判断文件格式是否正确 end
                    } else {
                        return 604;
                    }
                    //判断文件是否为空 end
                }
                break;
            //图片文件类型检查 end
        }
        //文件保存根目录
        String var1 = null;
        if (this.getOsName.getOsName()) {
            //Linux文件路径
            var1 = "/usr/plantlet";
        } else {
            //windows文件路径
            var1 = "D:/plantlet";
        }
        //文件保存 start
        try {
            //初始化文件实体类
            FileData file = new FileData();
            //设置文件编号
            file.setFileId(fileId);
            List<String> fileList = this.fileUpload.fileUpload(files, fileType, var1);
            file.setBases(var1);
            //获取文件类型 start
            switch (fileType) {
                case "seedling":
                    file.setSort("苗木");
                    break;
                case "nursery":
                    file.setSort("苗圃");
                    break;
                case "user":
                    file.setSort("用户");
                    break;
                case "document":
                    file.setSort("文档文件");
                    break;
                case "excel":
                    file.setSort("表格文档");
                    break;
                default:
                    file.setSort("未知分类");
                    break;
            }
            //获取文件类型 end
            //文件信息保存 start
            for (int i = 0, length = fileList.size(); i < length; ++i) {
                //截取新文件名字符位置
                int j = fileList.get(i).lastIndexOf("/") + 1;
                //截取文件后缀字符位置
                int s = files[i].getOriginalFilename().lastIndexOf(".");
                //获取文件新命名
                file.setNewFileName(fileList.get(i).substring(j, fileList.get(i).length()));
                //获取原文件名
                file.setFileName(files[i].getOriginalFilename());
                //获取文件后缀
                file.setSuffix(files[i].getOriginalFilename().substring(s, files[i].getOriginalFilename().length()));
                //设置文件路径
                file.setPath(fileList.get(i));
                this.dao.saveFileDao(file);
            }
            //文件信息保存 end
            //清除集合里的内容  避免数据混乱
            fileList.clear();
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return 600;
        }
        return 0;
        //文件保存 end
    }
}
