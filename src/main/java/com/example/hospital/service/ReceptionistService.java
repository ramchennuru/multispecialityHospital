package com.example.hospital.service;

import com.example.hospital.dto.Patient;

public interface ReceptionistService {


    void assignDoctor(Patient patientDetails, int id);

}
