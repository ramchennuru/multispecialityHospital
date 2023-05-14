package com.example.hospital.dto;

public class Patient {

    long id;
    String name;
    int age;
    String symptoms;
    long phoneno;
    String status;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    public long getPhoneno() {
        return phoneno;
    }
    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Patient(long id, String name, int age, String symptoms, long phoneno, String status) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.phoneno = phoneno;
        this.status = status;
    }
    @Override
    public String toString() {
        return "PatientDao [id=" + id + ", name=" + name + ", age=" + age + ", symptoms=" + symptoms + ", phoneno="
                + phoneno + ", status=" + status + "]";
    }
    public Patient() {
        super();

    }
}
