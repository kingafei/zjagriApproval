package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.ApasinfoEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@DS(value = "base")
public interface ApasinfoMapper {

    <T> IPage<ApasinfoEntity> getTables(Page<T> page, @Param("handlestart") String[] handlestart, @Param("servicename") String servicename,  @Param("userId") String userId, @Param("databaseType") String databaseType);

    ApasinfoEntity selectByPrimaryKey(String projid);

    List<ApasinfoEntity> getByCardId(@Param("cardId") String cardId);

    void  updateByProId(ApasinfoEntity apasinfo);

    Integer insertSelective(ApasinfoEntity apasinfo);

}
