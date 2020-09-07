package cn.com.ecenter.xzspxt.controller;


import cn.com.ecenter.common.annotation.ControllerEndpoint;
import cn.com.ecenter.common.entity.AreaTree;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.service.NytAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@RequestMapping("/NytArea")
public class NytAreaController {
    @Autowired
    private NytAreaService nytAreaService;

    /**
     * 根据父Id获取区域树列表
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ControllerEndpoint(exceptionMessage = "获取区域树失败")
    public FebsResponse treeList(
            @ModelAttribute NytAreaEntity NytArea
    ){
        List<AreaTree<NytAreaEntity>> menus = nytAreaService.treeList(NytArea);
        return new FebsResponse().success().data(menus);
    }



    /**
     * 查找单个信息
     */
    @RequestMapping(value = "/sel", method = RequestMethod.GET)
    public FebsResponse sel(
            @RequestParam(value = "id", required = true) String id
    ){
        return new FebsResponse().success().data(nytAreaService.sel(id));
    }

    /**
     * 保存
     */
    @ControllerEndpoint(exceptionMessage = "添加区域失败")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public FebsResponse save(
            @ModelAttribute NytAreaEntity NytArea
    ){
        nytAreaService.save(NytArea);
        return new FebsResponse().success();
    }

    /**
     * 修改
     */
    @ControllerEndpoint(exceptionMessage = "修改区域失败")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public FebsResponse update(
            @ModelAttribute NytAreaEntity NytArea
            ){
        nytAreaService.update(NytArea);
        return new FebsResponse().success();
    }

    /**
     * 批量软删除
     */
    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.GET)
    public FebsResponse delByIds(@NotBlank(message = "{required}") @PathVariable  String ids){
        nytAreaService.delByIds(ids);
        return new FebsResponse().success();
    }

    /**
     * 根据父Id获取区域子集
     */
    @RequestMapping(value = "/getByParent", method = RequestMethod.GET)
    public FebsResponse getByParent(
            @RequestParam(value = "parentId", required = false) Integer parentId
    ){
        return new FebsResponse().success().data(nytAreaService.getByParent(parentId));
    }

    /**
     * 根据当前用户获取其所在地区树
     */
    @RequestMapping(value = "/userTree", method = RequestMethod.GET)
    @ControllerEndpoint(exceptionMessage = "获取区域树失败")
    public FebsResponse userTree(){
        List<AreaTree<NytAreaEntity>> menus = nytAreaService.userTree();
        return new FebsResponse().success().data(menus);
    }

}
