package com.xiaobai.service;

import com.xiaobai.mapper.DepartmentMapper;
import com.xiaobai.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:34
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName DepartmentService
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDepById(Integer id) {
        return departmentMapper.getDepById(id);
    }

    public List<Department> getAllDeps() {
        return departmentMapper.getAllDeps();
    }


    public Integer addDepartments(String departmentname) {
        return departmentMapper.addDepartments(departmentname);
    }

    public Department getDepByName(String departmentname) {
        return departmentMapper.getDepByName(departmentname);
    }

    public Integer deletedepById(Integer departmentid) {
        return departmentMapper.deletedepById(departmentid);
    }

    public Integer updatedep(Integer id, String name) {
        return departmentMapper.updatedep(id, name);
    }
}
