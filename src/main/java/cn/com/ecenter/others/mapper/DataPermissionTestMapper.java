package cn.com.ecenter.others.mapper;

import cn.com.ecenter.common.annotation.DataPermission;
import cn.com.ecenter.others.entity.DataPermissionTest;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DataPermission(methods = {"selectPage"})
@DS(value = "base")
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
