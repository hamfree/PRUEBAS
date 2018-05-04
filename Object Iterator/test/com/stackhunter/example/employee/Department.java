package com.stackhunter.example.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Department {

    private long deptId;
    private String name;
//    private Employee[] employees;
    private final List<Employee> employeeList = new ArrayList<Employee>();
//    private final Set<Employee> employeeSet = new LinkedHashSet<Employee>();
//    private final Map<Long,Employee> employeeMap = new LinkedHashMap<Long, Employee>();
//    private int[] deptFloors = {5, 9, 17};
    
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

//    public Employee[] getEmployees() {
//        return employees;
//    }

    public Department setEmployees(Employee ... employees) {
//        this.employees = employees;
        employeeList.addAll(Arrays.asList(employees));
//        employeeSet.addAll(Arrays.asList(employees));
//        for (Employee employee : employees) {
//            employeeMap.put(employee.getId(), employee);
//        }
        return this;
    }
    
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    
//    public Set<Employee> getEmployeeSet() {
//        return employeeSet;
//    }
//
//    public Map<Long, Employee> getEmployeeMap() {
//        return employeeMap;
//    }
//    
//    public int[] getDeptFloors() {
//        return deptFloors;
//    }
//    
//    public Department setDeptFloors(int ... deptFloors) {
//        this.deptFloors = deptFloors;
//        return this;
//    }

}