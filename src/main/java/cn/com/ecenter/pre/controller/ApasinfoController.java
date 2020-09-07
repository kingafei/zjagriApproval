package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.generator.entity.GeneratorConstant;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.service.ApasinfoService;
import cn.com.ecenter.system.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 办件主表相关操作
 */
@Slf4j
@RestController
@RequestMapping("apasinfo")
@RequiredArgsConstructor
@CrossOrigin // 接口跨域
public class ApasinfoController extends BaseController {

    private final ApasinfoService apasinfoService;

    /**
     * 收件待办查询待办列表（分页）
     * @param handlestart
     * @param request
     * @return
     */
    @GetMapping("tables/info")
    @RequiresPermissions("waitHandle:view")
    public FebsResponse tablesInfo(String handlestart, String param, QueryRequest request) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> dataTable = getDataTable(apasinfoService.getTables(handlestart, param, String.valueOf(user.getId()), request, GeneratorConstant.DATABASE_TYPE));
        return new FebsResponse().success().data(dataTable);
    }

    /**
     * 通过主键申报号获取办件相关信息
     * @param projid
     * @return
     */
    @GetMapping("getById")
    public FebsResponse getById(String projid) {
        return new FebsResponse().success().data(apasinfoService.getById(projid));
    }

    /**
     * 通过申报者证件号码获取申报历史记录
     * @param cardId
     * @return
     */
    @GetMapping("getByCardId")
    public FebsResponse getByCardId(String cardId) {
        Map<String, Object> dataTable = new HashMap<>();
        dataTable.put("rows", apasinfoService.getByCardId(cardId));
        return new FebsResponse().success().data(dataTable);
    }

    /**
     * 保存修改记录
     * @param apasinfo
     * @return
     */
    @PostMapping("update")
    public FebsResponse update(ApasinfoEntity apasinfo) {
        String projid = apasinfo.getProjid();
        FebsResponse fb = new FebsResponse();
        if (StringUtils.isBlank(projid)) {
            fb.message("申报号不能为空！");
        } else {
            apasinfoService.update(apasinfo);
            fb.success();
        }
        return fb;
    }

    /**
     * 添加办件
     * @param apasinfo
     * @return
     */
    @PostMapping("save")
    public FebsResponse save(ApasinfoEntity apasinfo, @RequestParam(value ="tableData", required=false) String tableData, HttpServletRequest request) {

        Map<String, String[]> map = request.getParameterMap();

        String[] amountarray = request.getParameterValues("amount");

        System.out.println();

        for(String key : map.keySet()){
            String[] strings = map.get(key);

            for (String s : strings) {
                System.out.println(key+":"+s);
            }

        }

        FebsResponse fb = new FebsResponse();
        try{

            ArrayList<AttrEntity> attrList = JSON.parseObject(tableData, new TypeReference<ArrayList<AttrEntity>>(){});
//          fb = apasinfoService.save(apasinfo,attrList);
        }catch (Exception e){
            fb.message("添加失败~");
            e.printStackTrace();
        }finally {
            return  fb;
        }

    }

    /**
     * 修改办件信息，材料信息
     * @param apasinfo
     * @param tableData
     * @return
     */
    @PostMapping("updateAll")
    public FebsResponse acceppt(
            @RequestParam(value ="apasinfo", required=false) String apasinfo,
            @RequestParam(value ="tableData", required=false) String tableData) {
        ApasinfoEntity apasin = JSON.parseObject(apasinfo, new TypeReference<ApasinfoEntity>(){});
        ArrayList<AttrEntity> attrList = JSON.parseObject(tableData, new TypeReference<ArrayList<AttrEntity>>(){});

        return  apasinfoService.updateAll(apasin, attrList);
    }


}
