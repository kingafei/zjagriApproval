package cn.com.ecenter.xzspxt.controller;


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
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultData<List<NytDataDictEntity>> list(
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
        return NytDataDictService.list(qryMap);
    }


    /**
     * 查找单个信息
     */
    @RequestMapping(value = "/sel", method = RequestMethod.GET)
    public ResultData<NytDataDictEntity> sel(
            @RequestParam(value = "id", required = true) String id
    ){
        return NytDataDictService.sel(id);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultData<NytDataDictEntity> save(
            @ModelAttribute NytDataDictEntity NytDataDict
    ){
        return NytDataDictService.save(NytDataDict);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultData<NytDataDictEntity> update(
            @ModelAttribute NytDataDictEntity NytDataDict
            ){
        return NytDataDictService.update(NytDataDict);
    }

    /**
     * 批量软删除
     */
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    public ResultData<String> delByIds(
            @RequestParam(value = "ids", required = true) String ids
    ){
        return NytDataDictService.delByIds(ids);
    }

}
