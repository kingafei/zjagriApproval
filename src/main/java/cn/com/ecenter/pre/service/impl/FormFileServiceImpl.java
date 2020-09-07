package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.FormFileEntity;
import cn.com.ecenter.pre.mapper.FormFileMapper;
import cn.com.ecenter.pre.service.FormFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FormFileServiceImpl implements FormFileService {

    private final FormFileMapper formFileMapper;

    @Override
    @Transactional
    public void deleteByUUID(String unid) {
        formFileMapper.deleteByUUID(unid);
    }


    @Override
    @Transactional
    public FormFileEntity  insertSelective(FormFileEntity file, ApasinfoEntity apasinfoEntity, Long tongid) {
        formFileMapper.insertSelective(file);
        return file;
    }

}
