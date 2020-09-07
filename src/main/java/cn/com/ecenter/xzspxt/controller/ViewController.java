package cn.com.ecenter.xzspxt.controller;

import cn.com.ecenter.common.authentication.ShiroHelper;
import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsConstant;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.common.utils.FebsUtil;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.service.IUserService;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author MrBird
 */
@Controller("qlsxView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "qlsx")
@CrossOrigin
public class ViewController extends BaseController {
	
//    private final IUserService userService;
//	private final ShiroHelper shiroHelper;
//	private final NytFlowInfoService nytFlowInfoService;
	

    @GetMapping("windowHandle")
    @RequiresPermissions("windowHandle:view")
    public String qlsxWindowHandle() {
        return FebsUtil.view("qlsx/windowHandle/windowHandle");
    }

    @GetMapping("waitHandle")
    @RequiresPermissions("waitHandle:view")
    public String qlsxWaitHandle() {
        return FebsUtil.view("qlsx/waitHandle/waitHandle");
    }
    
    
    @GetMapping("proReceive/{projid}")
    @RequiresPermissions("waitHandle:view")
    public String proReceive(@PathVariable String projid, Model model) {
    	model.addAttribute("projid", projid);
        return FebsUtil.view("qlsx/waitHandle/receive");
    }


    @GetMapping("waiting")
    @RequiresPermissions("waiting:view")
    public String qlsxAlreadyHandle() {
        return FebsUtil.view("qlsx/waiting/waiting");
    }

    @GetMapping("applyopen/{projid}/{tongid}")
    @RequiresPermissions("waiting:view")
    public String applyopen(@PathVariable String projid, @PathVariable String tongid, Model model) {
        model.addAttribute("projid", projid);
        model.addAttribute("tongid", tongid);
        return FebsUtil.view("qlsx/waiting/applyopen");
    }

    @GetMapping("hang")
    @RequiresPermissions("hang:view")
    public String qlsxHang() {
        return FebsUtil.view("qlsx/hang/hang");
    }

    @GetMapping("bebeing")
    @RequiresPermissions("bebeing:view")
    public String qlsxBebeing() {
        return FebsUtil.view("qlsx/bebeing/bebeing");
    }

    @GetMapping("nodeopen/{projid}/{tongid}")
    @RequiresPermissions("bebeing:view")
    public String nodeopen(@PathVariable String projid, @PathVariable String tongid, Model model) {
        model.addAttribute("projid", projid);
        model.addAttribute("tongid", tongid);
        return FebsUtil.view("qlsx/bebeing/nodeopen");
    }

    @GetMapping("query")
    @RequiresPermissions("query:view")
    public String qlsxQuery() {
        return FebsUtil.view("qlsx/query/query");
    }

    @GetMapping("already")
    @RequiresPermissions("already:view")
    public String qlsxAlready() {
        return FebsUtil.view("qlsx/already/already");
    }

    @GetMapping("sendback")
    @RequiresPermissions("sendback:view")
    public String qlsxSendback() {
        return FebsUtil.view("qlsx/sendback/sendback");
    }

    @GetMapping("waitClassify")
    @RequiresPermissions("waitClassify:view")
    public String qlsxWaitClassify() {
        return FebsUtil.view("qlsx/waitClassify/waitClassify");
    }

    @GetMapping("alreadyClassify")
    @RequiresPermissions("alreadyClassify:view")
    public String qlsxAlreadyClassify() {
        return FebsUtil.view("qlsx/alreadyClassify/alreadyClassify");
    }
    
    @GetMapping("qlsxNodeDetail/{tongId}")
    @RequiresPermissions("windowHandle:view")
    public String qlsxNodeDetail(@PathVariable String tongId, Model model) {
    	model.addAttribute("tongid", tongId);
        return FebsUtil.view("qlsx/windowHandle/nodeView");
    }
    
    @GetMapping("qlsxDetail/{tongId}")
    @RequiresPermissions("windowHandle:view")
    public String qlsxDetail(@PathVariable String tongId, Model model) {
    	model.addAttribute("tongid", tongId);
        return FebsUtil.view("qlsx/windowHandle/qlsxView");
    }
    
    @GetMapping("qlsxAccept/{pkId}")
    @RequiresPermissions("windowHandle:view")
    public String qlsxAccept(@PathVariable String pkId, Model model) {
    	model.addAttribute("pkId", pkId);
        return FebsUtil.view("qlsx/windowHandle/accept");
    }
    
    

}
