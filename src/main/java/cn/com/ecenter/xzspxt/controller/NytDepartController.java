package cn.com.ecenter.xzspxt.controller;

import cn.com.ecenter.xzspxt.entity.NytDepartEntity;
import cn.com.ecenter.xzspxt.entity.Page;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.service.NytDepartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/NytDepart")
public class NytDepartController {
    @Autowired
    private cn.com.ecenter.xzspxt.service.NytDepartService NytDepartService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultData<List<NytDepartEntity>> list(
            @RequestParam(value = "pageNow", required = false) Integer pageNow,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "startTime", required = false) Long startTime,
            @RequestParam(value = "endTime", required = false) Long endTime,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "sort", required = false) Integer sort
    ){
        Page page = new Page();
        // 先生成基类的公共pageView
        if (pageNow != null) {
            page.setPageNow(pageNow);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        Map<String, Object> qryMap = new HashMap<String, Object>();
        qryMap.put("pageIndex", page.getPageIndex());
        qryMap.put("pageSize", page.getPageSize());
        if (sort != null) {
            qryMap.put("sort", sort);
        } else {
            qryMap.put("sort", 1);
        }
        if (startTime != null) {
            qryMap.put("startTime", startTime);
        }
        if (endTime != null) {
            qryMap.put("endTime", endTime);
        }
        if (state != null) {
            qryMap.put("state", state);
        }
        return NytDepartService.list(qryMap);
    }


    /**
     * 查找单个信息
     */
    @RequestMapping(value = "/sel", method = RequestMethod.GET)
    public ResultData<NytDepartEntity> sel(
            @RequestParam(value = "id", required = true) Integer id
    ){
        return NytDepartService.sel(id);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultData<NytDepartEntity> save(
            @ModelAttribute NytDepartEntity NytDepart
    ){
        return NytDepartService.save(NytDepart);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultData<NytDepartEntity> update(
            @ModelAttribute NytDepartEntity NytDepart
            ){
        return NytDepartService.update(NytDepart);
    }

    /**
     * 批量软删除
     */
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    public ResultData<String> delByIds(
            @RequestParam(value = "ids", required = true) String ids
    ){
        return NytDepartService.delByIds(ids);
    }

}
