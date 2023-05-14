package com.example.hospital.dto;

public class Doctor {

    private long id;
    private String name;
    private String specialization;
    private String password;
    private long phoneno;
    private String status;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public long getPhoneno() {
        return phoneno;
    }
    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }

    public Doctor(long id, String name, String specialization, long phoneno, String status) {
        super();
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phoneno = phoneno;
        this.status = status;
    }

    public Doctor(  String name, String specialization,String password, long phoneno ) {
        super();

        this.name = name;
        this.specialization = specialization;
        this.password = password;
        this.phoneno = phoneno;

    }
    @Override
    public String toString() {
        return "DoctorDao [id=" + id + ", name=" + name + ", specialization=" + specialization + ", phoneno=" + phoneno
                + ", status=" + status + "]";
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Doctor() {
        super();

    }

}
