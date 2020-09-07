package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.mapper.DeptMapper;
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

    @Autowired
    private  NytAreaService nytAreaService;

    @Autowired
    private DeptMapper deptMapper;





    /**
     * 转移区域数据
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ResultData<String> transferData(String ids){
        ResultData<String> result = new ResultData<String>();
        try{
           /* changeData1();
            changeData2();
            changeData3();
            changeData4();*/
            changeData5();
            changeData6();
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
        List<NytAreaEntity> nlist = nytAreaMapper.queryByLevel("1");
        for (NytAreaEntity n1 : nlist) {
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
    }

    /**
     * 转移县级数据
     */
    public void changeData2(){
        List<NytAreaEntity> nlist = nytAreaMapper.queryByLevel("2");
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

    /**
     * 转移镇级数据
     */
    public void changeData3(){
        List<NytAreaEntity> nlist = nytAreaMapper.queryByLevel("3");
        for (NytAreaEntity n1 : nlist) {
            String str1 = n1.getAdcode();
            List<ZwfwRegionEntity> zlist = zwfwRegionMapper.selectTownBycode(str1);
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
    }

    /**
     * 转移村级数据
     */
    public void changeData4(){
        List<NytAreaEntity> nlist = nytAreaMapper.queryByLevel("4");
        for (NytAreaEntity n1 : nlist) {
            String str1 = n1.getAdcode().substring(0,9);
            List<ZwfwRegionEntity> zlist = zwfwRegionMapper.selectVillageBycode(str1);
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
    }

    /**
     * 转移市部门级数据
     */
    public void changeData5(){
        List<ZwfwRegionEntity> zwlist = zwfwRegionMapper.selectDept("2","3");
        Date date = new Date();
        Dept d = new Dept();
        d.setParentId(3L);
        d.setDeptName("市级");
        d.setAdministrationCode("a" + "330000");
        deptMapper.add(d);
        List<Dept> ds = deptMapper.selectByAdCode("a" + "330000");
        for (ZwfwRegionEntity zw : zwlist) {
            d.setParentId(ds.get(0).getDeptId());
            d.setIsDelete("0");
            d.setDeptName(zw.getName());
            d.setUnifiedCreditCode(zw.getUncode());
            d.setOrgLive(zw.getOrgcoding());
            d.setUniqueCoding(zw.getUniquecoding());
            d.setAdministrationCode(zw.getAdCode());
            d.setRegioncode(zw.getRegioncode());
            d.setCreateTime(date);
            d.setModifyTime(date);
            deptMapper.add(d);

        }
    }

    /**
     * 转移县部门级数据
     */
    public void changeData6(){
        List<NytAreaEntity> arlist = nytAreaMapper.queryByLevel("2");
        for (NytAreaEntity ar : arlist) {
            Date date = new Date();
            List<Dept> dept = deptMapper.selectByAdCode(ar.getAdcode());
            if (dept.size() < 1) {
                continue;
            }
            List<ZwfwRegionEntity> zwlist = zwfwRegionMapper.selectDept("3",ar.getId()+"");
            Dept d = new Dept();
            d.setParentId(dept.get(0).getDeptId());
            d.setDeptName("县级");
            d.setAdministrationCode("a" + dept.get(0).getAdministrationCode());
            deptMapper.add(d);
            List<Dept> ds = deptMapper.selectByAdCode("a" + dept.get(0).getAdministrationCode());
            for (ZwfwRegionEntity zw : zwlist) {
                d.setParentId(ds.get(0).getDeptId());
                d.setIsDelete("0");
                d.setDeptName(zw.getName());
                d.setUnifiedCreditCode(zw.getUncode());
                d.setOrgLive(zw.getOrgcoding());
                d.setUniqueCoding(zw.getUniquecoding());
                d.setAdministrationCode(zw.getAdCode());
                d.setRegioncode(zw.getRegioncode());
                d.setCreateTime(date);
                d.setModifyTime(date);
                deptMapper.add(d);

            }
        }
    }

}
