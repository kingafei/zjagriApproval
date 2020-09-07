package cn.com.ecenter.xzspxt.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.com.ecenter.common.entity.FebsConstant;
import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.common.utils.SortUtil;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.mapper.NytQlsxMapper;
import cn.com.ecenter.xzspxt.service.NytQlsxService;

@Service
public class NytQlsxServiceImpl  implements NytQlsxService {
    @Autowired
    private NytQlsxMapper nytQlsxMapper;

    @Override
    public void add(NytQlsxEntity nytQlsx) {
        nytQlsxMapper.add(nytQlsx);
    }

    @Override
    public <T> IPage<NytQlsxEntity> selectByUserId(Map map, QueryRequest request) {


        String param = (String) map.get("param");
        Long id = (Long) map.get("id");
         Integer i = Math.toIntExact(id);
        Page<NytQlsxEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(nytQlsxMapper.getTotal(Integer.valueOf(i),param));
        SortUtil.handlePageSort(request, page, "QL_MAINITEM_ID", FebsConstant.ORDER_ASC, false);
        IPage<NytQlsxEntity> select = nytQlsxMapper.selectByUserId(page,i,param);
        return select;
    }

    @Override
    public NytQlsxEntity selByTongId(String tongid) {
        NytQlsxEntity nytQlsxEntity = nytQlsxMapper.selectByTongId(tongid);
        return nytQlsxEntity;
    }


}
