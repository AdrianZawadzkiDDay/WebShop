package shop.model;

import java.util.List;

public class User {
    private String email;
    private String password;
    private String userName;
    private List<String> roles;

    public User(String email, String password, String userName, List<String> roles) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.roles = roles;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
