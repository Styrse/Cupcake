package app.entities.userRoles;

public class Admin extends Employee{
    public Admin(String firstname, String lastname, String email, String password, float balance, int employeeID) {
        super(firstname, lastname, email, password, balance, employeeID);
    }

    public Admin(String email, String password, float balance, String firstname) {
        super(email, password, balance, firstname);
    }


}
