package scratch.com.stackhunter.util.tostring.example;

import com.stackhunter.example.employee.Department;
import com.stackhunter.example.employee.Employee;
import com.stackhunter.example.employee.Manager;
import com.stackhunter.util.objectiterator.ObjectIterator;

public class ObjectIteratorExample {

    public static void main(String[] args) {
        Department department = new Department(5775, "Sales")
        .setEmployees(
                new Employee(111, "Bill", "Gates"), 
                new Employee(222, "Howard", "Schultz"), 
                new Manager(333, "Jeff", "Bezos", 75000));

        ObjectIterator iterator = new ObjectIterator("some department", department);
        
        while (iterator.next()) {
            System.out.println(iterator.getName() + "=" + iterator.getValueAsString());
        }
    }

}
