package cn.com.ecenter.system.mapper;

import cn.com.ecenter.system.entity.UserDataPermission;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface UserDataPermissionMapper extends BaseMapper<UserDataPermission> {

}
