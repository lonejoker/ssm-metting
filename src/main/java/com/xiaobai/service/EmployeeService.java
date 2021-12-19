package com.xiaobai.service;

import com.xiaobai.mapper.EmployeeMapper;
import com.xiaobai.mapper.MeetingParticipantsMapper;
import com.xiaobai.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:35
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName EmployeeService
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MeetingParticipantsMapper meetingParticipantsMapper;

    public Employee doLogin(String username, String password) {
        Employee employee = employeeMapper.loadEmpByUsername(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            return null;
        }
        return employee;
    }

    public Integer doReg(Employee employee) {
        Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
        if (emp != null) {
            return -1;
        }
        employee.setRole(1);
        employee.setStatus(0);
        return employeeMapper.doReg(employee);
    }

    public List<Employee> getAllEmpsByStaus(Integer status) {
        return employeeMapper.getAllEmpsByStaus(status);
    }

    public Integer updateStatus(Integer employeeid, Integer status) {
        return employeeMapper.updateStatus(employeeid, status);
    }

    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        return employeeMapper.getAllEmps(employee, page, pageSize);
    }

    public Long getTotal(Employee employee) {
        return employeeMapper.getTotal(employee);
    }

    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeMapper.getEmpsByDepId(depId);
    }

    public List<Employee> getEmpByMeetingId(Integer meetingid) {
        List<Integer> list = meetingParticipantsMapper.getIdList(meetingid);
        return employeeMapper.getAllEmpsByEmpId(list);
    }
}
