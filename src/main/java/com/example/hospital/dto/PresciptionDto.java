package com.example.hospital.dto;

public class PresciptionDto {

    private int did;
    private int  pid;
    private String description;
    private String feedback;
    private int bill;
    private int billStatus;
    public int getDid() {
        return did;
    }
    public void setDid(int did) {
        this.did = did;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public int getBill() {
        return bill;
    }
    public void setBill(int bill) {
        this.bill = bill;
    }
    public int getBillStatus() {
        return billStatus;
    }
    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }
    public PresciptionDto() {
        super();
    }

    public PresciptionDto(int did, int pid, String description, String feedback, int bill, int billStatus) {
        super();
        this.did = did;
        this.pid = pid;
        this.description = description;
        this.feedback = feedback;
        this.bill = bill;
        this.billStatus = billStatus;
    }
}
