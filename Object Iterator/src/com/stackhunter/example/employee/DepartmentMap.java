/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackhunter.example.employee;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author hamfree
 */
public class DepartmentMap {
    private long deptId;
    private String name;
    private final Map<Long, Employee> employeeMap = new LinkedHashMap<>();
    private int[] deptFloors = {5, 9, 17};

    public DepartmentMap() {
    }

    public DepartmentMap(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public long getDeptId() {
        return deptId;
    }

    public DepartmentMap setDeptId(long deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentMap setName(String name) {
        this.name = name;
        return this;
    }

    public DepartmentMap setEmployees(Employee... employees) {
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return this;
    }

    public Map<Long, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public int[] getDeptFloors() {
        return deptFloors;
    }

    public DepartmentMap setDeptFloors(int... deptFloors) {
        this.deptFloors = deptFloors;
        return this;
    }
}
