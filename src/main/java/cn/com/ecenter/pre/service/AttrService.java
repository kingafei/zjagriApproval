package cn.com.ecenter.pre.service;

import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface AttrService {
    /**
     * 根据申报号查询相关的申报材料
     * @param projid
     * @return
     */
    List<AttrEntity> getById(String projid);

    /**
     * 根据事项表关联id查询相关的申报材料
     * @param tongId
     * @param projid
     * @return
     */
    List<AttrEntity> getByPId(String tongId, String projid);

    void update(List<AttrEntity> list);


}
