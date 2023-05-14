package com.example.hospital.dto;

public class Admin {

    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String password;
    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + "]";
    }
    public Admin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public Admin() {
        super();

    }
}
