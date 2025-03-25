package app.entities.userRoles;

public class Admin extends Employee{
    public Admin(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password);
    }

    public Admin(String firstname, String email, String password) {
        super(firstname, email, password);
    }
}
