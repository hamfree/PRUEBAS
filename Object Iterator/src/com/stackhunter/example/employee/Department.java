package com.stackhunter.example.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Department {

    private long deptId;
    private String name;
    private final List<Employee> employeeList = new ArrayList<>();
    private int[] deptFloors = {5, 9, 17};

    public Department() {
    }

    public Department(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public long getDeptId() {
        return deptId;
    }

    public Department setDeptId(long deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    public Department setEmployees(Employee... employees) {
        employeeList.addAll(Arrays.asList(employees));
        return this;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int[] getDeptFloors() {
        return deptFloors;
    }

    public Department setDeptFloors(int... deptFloors) {
        this.deptFloors = deptFloors;
        return this;
    }

}
