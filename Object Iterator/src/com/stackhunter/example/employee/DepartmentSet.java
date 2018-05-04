/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackhunter.example.employee;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author hamfree
 */
public class DepartmentSet {
    private long deptId;
    private String name;
    private final Set<Employee> employeeSet = new LinkedHashSet<>();
    private int[] deptFloors = {5, 9, 17};

    public DepartmentSet() {
    }

    public DepartmentSet(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public long getDeptId() {
        return deptId;
    }

    public DepartmentSet setDeptId(long deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentSet setName(String name) {
        this.name = name;
        return this;
    }

    public DepartmentSet setEmployees(Employee... employees) {
        employeeSet.addAll(Arrays.asList(employees));
        return this;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public int[] getDeptFloors() {
        return deptFloors;
    }

    public DepartmentSet setDeptFloors(int... deptFloors) {
        this.deptFloors = deptFloors;
        return this;
    }
}
