package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.QltQlsxEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@DS(value = "quartz")
public interface QltQlsxMapper extends BaseMapper<QltQlsxEntity> {

    List<QltQlsxEntity> findQlsx(Integer pageIndex, Integer pageSize);

    int fingTotal();
}
