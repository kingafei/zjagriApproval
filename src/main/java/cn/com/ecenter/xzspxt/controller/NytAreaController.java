package cn.com.ecenter.xzspxt.controller;


import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.Page;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.service.NytAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/NytArea")
public class NytAreaController {
    @Autowired
    private NytAreaService nytAreaService;

    /**
     * 根据父Id获取区域树列表
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public ResultData<List<NytAreaEntity>> list(
            @RequestParam(value = "parentId", required = false) Integer parentId
    ){

        return nytAreaService.treeList(parentId);
    }


    /**
     * 查找单个信息
     */
    @RequestMapping(value = "/sel", method = RequestMethod.GET)
    public ResultData<NytAreaEntity> sel(
            @RequestParam(value = "id", required = true) String id
    ){
        return nytAreaService.sel(id);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultData<NytAreaEntity> save(
            @ModelAttribute NytAreaEntity NytArea
    ){
        return nytAreaService.save(NytArea);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultData<NytAreaEntity> update(
            @ModelAttribute NytAreaEntity NytArea
            ){
        return nytAreaService.update(NytArea);
    }

    /**
     * 批量软删除
     */
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    public ResultData<String> delByIds(
            @RequestParam(value = "ids", required = true) String ids
    ){
        return nytAreaService.delByIds(ids);
    }

}
