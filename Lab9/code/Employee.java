package code;

public class Employee {
    public String name;
    public int salary;

    public Employee(String n, int s) {
        name = n;
        salary = s;
    }

    @Override
    public String toString() {
        return "Emp " + name + "(" + salary + ")";
    }

}
