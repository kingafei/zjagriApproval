package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.annotation.ControllerEndpoint;
import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.FormFileEntity;
import cn.com.ecenter.pre.service.FormFileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * 办件申报材料附件
 */
@Slf4j
@RestController
@RequestMapping("formfile")
@RequiredArgsConstructor
@CrossOrigin // 接口跨域
public class FormFileController extends BaseController {

    private final FormFileService formFileService;


    @GetMapping("deleteByUUID")
    public FebsResponse deleteByUUID(String unid) {
        formFileService.deleteByUUID(unid);
        return new FebsResponse().success().message("删除成功");
    }


    //    文件上传
    @PostMapping("upload")
    public FormFileEntity upload(MultipartFile file, ApasinfoEntity apasinfoEntity, Long tongid) {


        //上传文件路径
//        String path = servletRequest.getServletContext().getRealPath("/uploadFile");

        FormFileEntity formFileEntity = null;


        log.info("上传文件"+file);
        //获取后缀名
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //重命名文件
        String fileName = UUID.randomUUID().toString() + extName;


        File filepath = new File("E:\\", fileName);
        // 判断目标路径是否存在  不存在则创建
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        Integer id = 0;
        //流上传
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filepath));
            FormFileEntity entity = new FormFileEntity();

            entity.setUnid(UUID.randomUUID().toString());
            // 设置附件产生阶段
            if (apasinfoEntity == null){
                entity.setType("02");// 第一次上传文件处于受理阶段
            }else {
//                entity.setType();// 其余根据当前办件的阶段进赋值
            }
            entity.setName(fileName);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setFilepath(filepath.getPath());
            entity.setCreateTime(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
            entity.setDataversion(1);

             formFileEntity = formFileService.insertSelective(entity, apasinfoEntity, tongid);


            log.info(file.getOriginalFilename()+"附件上传成功 ");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {

            return formFileEntity;
        }
    }
}
