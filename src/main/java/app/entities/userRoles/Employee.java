package app.entities.userRoles;

public class Employee extends User{
    private int employeeID;

    public Employee(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password);
    }

    public Employee(String firstname, String email, String password) {
        super(firstname, email, password);
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
