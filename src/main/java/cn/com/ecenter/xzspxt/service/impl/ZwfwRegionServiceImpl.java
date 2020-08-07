package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.xzspxt.common.Sys_Constant;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.entity.ZwfwRegionEntity;
import cn.com.ecenter.xzspxt.mapper.NytAreaMapper;
import cn.com.ecenter.xzspxt.mapper.ZwfwRegionMapper;
import cn.com.ecenter.xzspxt.service.NytAreaService;
import cn.com.ecenter.xzspxt.service.ZwfwRegionService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ZwfwRegionServiceImpl implements ZwfwRegionService {

    @Autowired
    private NytAreaMapper nytAreaMapper;

    @Autowired
    private ZwfwRegionMapper zwfwRegionMapper;

    @Autowired NytAreaService nytAreaService;



    /**
     * 转移区域数据
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ResultData<String> sel(String ids){
        ResultData<String> result = new ResultData<String>();
        try{
            changeData1();
            changeData2();
            result.setData("处理成功！");
            result.setCode(Sys_Constant.SUCCESS_CODE);
            result.setMsg(Sys_Constant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setCode(Sys_Constant.FAIL_CODE);
            result.setMsg(Sys_Constant.FAIL_MSG);
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 转移市级数据
     */
    public void changeData1(){
        List<NytAreaEntity> nlist = nytAreaMapper.list(2);
        NytAreaEntity n1 = nlist.get(0);
        List<ZwfwRegionEntity> zlist = zwfwRegionMapper.selectAll();
        NytAreaEntity n2 = new NytAreaEntity();
        for (ZwfwRegionEntity zw : zlist) {
            n2.setParentId(n1.getId());
            n2.setName(zw.getName());
            n2.setCode(zw.getAdCode());
            n2.setCodeUrl(n1.getCodeUrl());
            n2.setLevel(n1.getLevel());
            n2.setOrgcoding(zw.getOrgcoding());
            n2.setUncode(zw.getUncode());
            n2.setUniquecoding(zw.getUniquecoding());
            n2.setAdcode(zw.getAdCode());
            n2.setRegioncode(zw.getRegioncode());
            nytAreaService.save(n2);
        }
    }

    /**
     * 转移县级数据
     */
    public void changeData2(){
        List<NytAreaEntity> nlist = nytAreaMapper.list(3);
        for (NytAreaEntity n1 : nlist) {
            String str1 = n1.getAdcode().substring(0,4);
            String str2 = n1.getUniquecoding();
            List<ZwfwRegionEntity> zlist = zwfwRegionMapper.selectAllBycode(str1,str2);
            NytAreaEntity n2 = new NytAreaEntity();
            for (ZwfwRegionEntity zw : zlist) {
                if (n1.getAdcode().equals(zw.getAdCode())) {
                    continue;
                }
                n2.setParentId(n1.getId());
                n2.setName(zw.getName());
                n2.setCode(zw.getAdCode());
                n2.setCodeUrl(n1.getCodeUrl());
                n2.setLevel(n1.getLevel());
                n2.setOrgcoding(zw.getOrgcoding());
                n2.setUncode(zw.getUncode());
                n2.setUniquecoding(zw.getUniquecoding());
                n2.setAdcode(zw.getAdCode());
                n2.setRegioncode(zw.getRegioncode());
                nytAreaService.save(n2);
            }
        }
    }

}
