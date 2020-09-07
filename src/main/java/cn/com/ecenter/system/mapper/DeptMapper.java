package cn.com.ecenter.system.mapper;

import cn.com.ecenter.system.entity.Dept;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author MrBird
 */
@DS(value = "base")
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 添加部门
     * @param dept
     * @return
     */
    void add(Dept dept);

    /**
     * 通过AdCode查询部门
     * @param AdCode
     * @return
     */
    List<Dept> selectByAdCode(String AdCode);

    /**
     * 查询所有的部门
     * @param
     * @return
     */
    List<Dept> selectAll();

    /**
     * 通过ID查询部门
     * @param deptId
     * @return
     */
    List<Dept> selectByID(String deptId);
}
