package app.entities.userRoles;

public abstract class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private float balance;

    public User(String firstname, String lastname, String email, String password, float balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public User(String email, String password, float balance, String firstname) {
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.firstname = firstname;
    }

    public User(String firstname, String email, float balance) {
        this.firstname = firstname;
        this.email = email;
        this.balance = balance;
    }

    public User(String email, float balance) {
        this.email = email;
        this.balance = balance;
    }

    public User(String firstname, String email, String password) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance() {
        return balance;
    }

    public float setBalance(float balance) {
        return this.balance = balance;
    }

    public String getRole() {
        return this.getClass().getSimpleName();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
