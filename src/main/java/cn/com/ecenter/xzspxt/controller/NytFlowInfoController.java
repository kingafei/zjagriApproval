package cn.com.ecenter.xzspxt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;

/**
 * 事项流程接口
 */

@RestController
@RequestMapping("/nytFlowInfo")
public class NytFlowInfoController {
    @Autowired
    private NytFlowInfoService nytFlowInfoService;


    @GetMapping("getOrderActivity")
    public FebsResponse getOrderActivity(String tongId) {
        FebsResponse result = new FebsResponse();
        result.success().data(nytFlowInfoService.getOrderActivity(tongId));
        return result;
    }
//    事项环节查看、
    @GetMapping("selectByPid")
    public ResultData<List<NytFlowInfoEntity>> selectByPid(String  tongId){
        ResultData<List<NytFlowInfoEntity>> resultData = new ResultData<List<NytFlowInfoEntity>>();
       resultData.setCode("0");
        List<NytFlowInfoEntity> orderActivity = nytFlowInfoService.getOrderActivity(tongId);
        resultData.setData(orderActivity);
        resultData.setCount(orderActivity.size());
       resultData.setMsg("查询成功~");
        return resultData;
    }
}
