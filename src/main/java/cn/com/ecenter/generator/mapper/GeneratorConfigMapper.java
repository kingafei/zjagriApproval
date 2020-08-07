package cn.com.ecenter.generator.mapper;

import cn.com.ecenter.generator.entity.GeneratorConfig;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface GeneratorConfigMapper extends BaseMapper<GeneratorConfig> {

}
