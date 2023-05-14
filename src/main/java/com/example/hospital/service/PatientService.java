package com.example.hospital.service;

import com.example.hospital.dto.Patient;

public interface PatientService {

    void addPatientDetails(Patient patientDetails, String ps, int id) throws ClassNotFoundException;
    public void getByAge();
}
