package scratch.com.stackhunter.util.tostring.example;

import com.stackhunter.example.employee.Department;
import com.stackhunter.example.employee.Employee;
import com.stackhunter.example.employee.Manager;

import com.stackhunter.util.tostring.StringGenerator;

public class StringGeneratorExample {

    public static void main(String[] args) {
        Department department = new Department(5775, "Sales")
        .setEmployees(
                new Employee(111, "Bill", "Gates"), 
                new Employee(222, "Howard", "Schultz"), 
                new Manager(333, "Jeff", "Bezos", 75000));

        System.out.println(StringGenerator.generate(department));
        System.out.println(StringGenerator.generate(new int[] { 111, 222, 333 }));
        System.out.println(StringGenerator.generate(true));
    }

}
