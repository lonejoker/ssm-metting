package com.xiaobai.mapper;

import com.xiaobai.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department getDepById(Integer id);

    List<Department> getAllDeps();

    Integer addDepartments(String departmentname);

    Department getDepByName(String departmentname);

    Integer deletedepById(Integer departmentid);

    Integer updatedep(@Param("id") Integer id, @Param("name") String name);
}
