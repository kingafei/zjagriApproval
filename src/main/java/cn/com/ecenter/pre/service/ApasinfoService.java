package cn.com.ecenter.pre.service;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.generator.entity.Table;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.List;

public interface ApasinfoService {
    /**
     * 获取办件待收件列表
     *
     * @param handlestart    handlestart
     * @param request      request
     * @param datasource   datasource
     * @return 办件表分页数据
     */
    IPage<ApasinfoEntity> getTables(String handlestart, String param, String userId, QueryRequest request, String datasource);

    /**
     * 根据办件id查询，回显表单yoga
     * @param projid
     * @return
     */
    ApasinfoEntity getById(String projid);

    /**
     * 通过申报者证件号码获取申报历史记录
     * @param cardId       cardId
     * @return 办件表分页数据
     */
    List<ApasinfoEntity> getByCardId(String cardId);

    /**
     * 根存修改记录
     * @param apasinfo
     * @return
     */
    void update(ApasinfoEntity apasinfo);

    /**
     * 添加办件
     * @param apasinfo
     * @return
     */
    FebsResponse save(ApasinfoEntity apasinfo, ArrayList<AttrEntity> list);

    /**
     * 修改办件信息，材料信息
     * @param apasinfo
     * @param tableData
     * @return
     */
    public FebsResponse updateAll(ApasinfoEntity apasinfo, List<AttrEntity> tableData);

}
