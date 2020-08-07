package cn.com.ecenter.job.mapper;


import cn.com.ecenter.job.entity.JobLog;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface JobLogMapper extends BaseMapper<JobLog> {
}