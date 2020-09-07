package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.service.AttrService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 办件申报材料表
 */
@Slf4j
@RestController
@RequestMapping("attr")
@RequiredArgsConstructor
@CrossOrigin // 接口跨域
public class AttrController extends BaseController {

    private final AttrService attrService;


    @GetMapping("getById")
    public FebsResponse getById(String projid) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", attrService.getById(projid));
        return new FebsResponse().success().data(result);
    }

    @GetMapping("getByTongId")
    public FebsResponse getByTongId(String tongId, String projid) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", attrService.getByPId(tongId, projid));
        return new FebsResponse().success().data(result);
    }


    /**
     * 保存修改记录
     * @param tableData
     * @return
     */
    @PostMapping("update")
    public FebsResponse update(@RequestParam(value ="tableData", required=false) String tableData) {
        FebsResponse fb = new FebsResponse();
        ArrayList<AttrEntity> attrList = JSON.parseObject(tableData, new TypeReference<ArrayList<AttrEntity>>(){});
        attrService.update(attrList);
        return  new FebsResponse().success();
    }

}
