package se160205.dto;

/**
 *
 * @author Huynh Van Phuot
 */
public class Account {

    private int id;
    private String email;
    private String password;
    private String fullname;
    private int status;
    private String phone;
    private int role;

    public Account() {
    }

    public Account(String email, String password, String fullname, int status, String phone, int role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.status = status;
        this.phone = phone;
        this.role = role;
    }

    public Account(int id, String email, String password, String fullname, int status, String phone, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.status = status;
        this.phone = phone;
        this.role = role;
    }

    public Account(String email, String password, String fullname, String phone) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int accId) {
        this.id = accId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", fullname=" + fullname + ", status=" + status + ", phone=" + phone + ", role=" + role + '}';
    }

}
