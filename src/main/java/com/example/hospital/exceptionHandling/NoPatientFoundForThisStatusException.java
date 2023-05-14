package com.example.hospital.exceptionHandling;

public class NoPatientFoundForThisStatusException extends Exception{
    public NoPatientFoundForThisStatusException(String status) {
    }
}
