package cn.com.ecenter.xzspxt.controller;

import cn.com.ecenter.common.annotation.ControllerEndpoint;
import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.service.impl.NytQlsxServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("nytQlsx")
@RestController
@Log4j2
@CrossOrigin

public class NytQlsxController  extends BaseController {

    @Autowired
 private NytQlsxServiceImpl nytQlsxService;


    @RequestMapping(value = "/selQlsx", method = RequestMethod.GET)
    @ControllerEndpoint(exceptionMessage = "获取事项列表失败")
  public FebsResponse selectByUserId(Integer pageIndex ,Integer pageSize,String param, QueryRequest request
  ){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> map = new HashMap<>();
        log.info(user);
      if(user!=null && user.getUserId()!=null){
          map.put("id",user.getUserId());
      }else {
          return  new FebsResponse().message("未获取到当前登录用户").success().code(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      if(param!=null){
          map.put("param",param);
      }
        Map<String, Object> dataTable = getDataTable(nytQlsxService.selectByUserId(map, request));
      return  new FebsResponse().success().data(dataTable);
  }



    @RequestMapping(value = "/selByTongId", method = RequestMethod.GET)
    @ControllerEndpoint(exceptionMessage = "获取事项详情失败")
    public FebsResponse selByTongId(String tongId
    ){
        NytQlsxEntity nytQlsxEntity = nytQlsxService.selByTongId(tongId);
        return  new FebsResponse().success().data(nytQlsxEntity);
    }
}
