/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackhunter.example.employee;

/**
 *
 * @author hamfree
 */
public class DepartmentArray {
    private long deptId;
    private String name;
    private Employee[] employees;
    private int[] deptFloors = {5, 9, 17};

    public DepartmentArray() {
    }

    public DepartmentArray(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public long getDeptId() {
        return deptId;
    }

    public DepartmentArray setDeptId(long deptId) {
        this.deptId = deptId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentArray setName(String name) {
        this.name = name;
        return this;
    }

    public Employee[] getEmployees() {
        return employees;
    }
    public DepartmentArray setEmployees(Employee... employees) {
        this.employees = employees;
        return this;
    }

    public int[] getDeptFloors() {
        return deptFloors;
    }

    public DepartmentArray setDeptFloors(int... deptFloors) {
        this.deptFloors = deptFloors;
        return this;
    }
}
