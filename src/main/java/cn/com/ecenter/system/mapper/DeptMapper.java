package cn.com.ecenter.system.mapper;

import cn.com.ecenter.system.entity.Dept;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface DeptMapper extends BaseMapper<Dept> {
}
