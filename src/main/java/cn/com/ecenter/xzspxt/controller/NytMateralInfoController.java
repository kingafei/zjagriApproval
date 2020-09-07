package cn.com.ecenter.xzspxt.controller;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.xzspxt.entity.NytMateralInfoEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.service.NytMateralInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("nytMateralInfo")
@RestController
@Log4j2
public class NytMateralInfoController {

  @Autowired
   private   NytMateralInfoService nytMateralInfoService;

    @GetMapping("selectByChildren")
    ResultData<List<NytMateralInfoEntity>> selectByChildren(String pkId){
//    Map<String,Object> selectByChildren(String pkId){
        ResultData<List<NytMateralInfoEntity>> resultData =   new  ResultData<List<NytMateralInfoEntity>>();
//        result.put("rows",);
        List<NytMateralInfoEntity> list=  nytMateralInfoService.selectPId(pkId);
        resultData.setCode("0");
        resultData.setData(list);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code",0);
//        map.put("msg","成功");
//        map.put("data",new Object[]{list});
        return resultData;


    }
}
