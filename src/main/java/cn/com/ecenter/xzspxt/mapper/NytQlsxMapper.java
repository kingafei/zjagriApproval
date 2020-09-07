package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DS(value = "base")
public interface NytQlsxMapper extends BaseMapper<NytQlsxEntity> {

    void add(NytQlsxEntity nytQlsx);

    <T> IPage<NytQlsxEntity> selectByUserId(Page<T> page,@Param("id") Integer id,@Param("param")String param);

//    List<NytQlsxEntity> selectByUserId(Map map);

    Integer  getTotal(@Param("id") Integer id,@Param("param")String param);

    NytQlsxEntity selectByTongId(@Param("tongId") String tongId);


//    String  selBjType(String typeValue);
}
