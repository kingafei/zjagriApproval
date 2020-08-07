package cn.com.ecenter.monitor.mapper;

import cn.com.ecenter.monitor.entity.SystemLog;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface LogMapper extends BaseMapper<SystemLog> {

}
