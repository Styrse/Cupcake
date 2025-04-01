package app.entities.userRoles;

public class Employee extends User{
    private int employeeID;

    public Employee(String firstname, String lastname, String email, String password, float balance, int employeeID) {
        super(firstname, lastname, email, password, balance);
        this.employeeID = employeeID;
    }

    public Employee(String email, String password, float balance, String firstname, int employeeID) {
        super(email, password, balance, firstname);
        this.employeeID = employeeID;
    }

    public Employee(String email, String password, float balance, String firstname) {
        super(email, password, balance, firstname);
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
