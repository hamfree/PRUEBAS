package com.stackhunter.example.employee;


public class Manager extends Employee {

    private double budget;
    
    public Manager() {
    }

    public Manager(long id, String firstName, String lastName, double budget) {
        super(id, firstName, lastName);
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }
    
    public Manager setBudget(double budget) {
        this.budget = budget;
        return this;
    }

}