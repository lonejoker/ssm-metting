package com.xiaobai.entity;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:29
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName Department
 */
public class Department {
    //部门编号
    private Integer departmentId;
    //部门名称
    private String departmentName;

    public Department() {
    }

    public Department(Integer departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}