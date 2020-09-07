package cn.com.ecenter.xzspxt.controller;


import cn.com.ecenter.common.annotation.ControllerEndpoint;
import cn.com.ecenter.common.entity.DictTree;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.xzspxt.entity.Page;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import cn.com.ecenter.xzspxt.service.NytDataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/NytDataDict")
public class NytDataDictController {
    @Autowired
    private NytDataDictService NytDataDictService;


    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public FebsResponse save(@ModelAttribute NytDataDictEntity NytDataDict){
        NytDataDictService.save(NytDataDict);
        return new FebsResponse().success();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public FebsResponse update(@ModelAttribute NytDataDictEntity NytDataDict){
        NytDataDictService.update(NytDataDict);
        return new FebsResponse().success();
    }




    //  获取字典表分组集合
    @RequestMapping(value = "/selectByPid", method = RequestMethod.GET)
    FebsResponse selectByPid(String pid){
        List<Map<String, String>> list = NytDataDictService.selectByPid(pid);
        return new FebsResponse().success().data(list);
    }

    //  获取分组集合子级
    @RequestMapping(value = "/selectTree", method = RequestMethod.GET)
    public FebsResponse selectTree(){
        List<DictTree<NytDataDictEntity>> list = NytDataDictService.selectTree();
        return new FebsResponse().success().data(list);
    }



}
