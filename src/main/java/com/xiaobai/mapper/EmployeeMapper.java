package com.xiaobai.mapper;

import com.xiaobai.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Employee loadEmpByUsername(String username);

    Integer doReg(Employee employee);

    List<Employee> getAllEmpsByStaus(Integer status);

    Integer updateStatus(@Param("employeeid") Integer employeeid, @Param("status") Integer status);

    List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Long getTotal(Employee employee);

    List<Employee> getEmpsByDepId(Integer depId);

    List<Employee> getAllEmpsByEmpId(List<Integer> list);

}
