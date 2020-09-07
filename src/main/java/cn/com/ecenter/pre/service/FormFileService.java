package cn.com.ecenter.pre.service;


import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.FormFileEntity;

public interface FormFileService {
    void deleteByUUID(String unid);


    FormFileEntity insertSelective(FormFileEntity file, ApasinfoEntity apasinfoEntity, Long tongid);
}
