package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.entity.FormFileEntity;
import cn.com.ecenter.pre.mapper.AttrMapper;
import cn.com.ecenter.pre.mapper.FormFileMapper;
import cn.com.ecenter.pre.service.AttrService;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.mapper.NytQlsxMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttrServiceImpl implements AttrService {

    private final AttrMapper attrMapper;

    private  final NytQlsxMapper nytQlsxMapper;

    private final FormFileMapper formFileMapper;

    /**
     * 根据申报号查询相关的申报材料
     * @param projid
     * @return
     */
    @Override
    public List<AttrEntity> getById(String projid) {
        return attrMapper.getById(projid);
    }

    /**
     * 根据事项表关联id查询相关的申报材料
     * @param tongId
     * @param projid
     * @return
     */
    @Override
    public List<AttrEntity> getByPId(String tongId, String projid) {
        NytQlsxEntity qlsx = nytQlsxMapper.selectByTongId(tongId);
        List<AttrEntity> result = attrMapper.getByPId(qlsx.getRowguid() + "_" + qlsx.getId(), projid);
        if (result != null && result.size() > 0) {
            for (AttrEntity arr : result) {
                if (!StringUtils.isBlank(arr.getUnid())) {
                    arr.getList().addAll(formFileMapper.getByUUID(arr.getUnid()));
                }
            }
        }
        return result;
    }


    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void update(List<AttrEntity> list) {
        for (AttrEntity attr : list) {
            if (null != attr.getUnid()) {
                attrMapper.updateByUNID(attr);
                List<FormFileEntity> files = attr.getList();
                for (FormFileEntity file : files) {
                    formFileMapper.updateByUNID(file);
                }
            }
        }

    }


}
