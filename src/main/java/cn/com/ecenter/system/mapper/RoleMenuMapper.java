package cn.com.ecenter.system.mapper;

import cn.com.ecenter.system.entity.RoleMenu;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
}
