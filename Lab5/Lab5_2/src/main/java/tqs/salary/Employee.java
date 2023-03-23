package tqs.salary;

public class Employee {
    private int id;
    private String user;
    private float salary;

    public Employee(int id, String user, float salary) {
        this.id = id;
        this.user = user;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

}
