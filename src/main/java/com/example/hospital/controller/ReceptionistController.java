package com.example.hospital.controller;

import com.example.hospital.dto.Patient;
import com.example.hospital.service.ReceptionistService;
import com.example.hospital.service.ReceptionistServiceImpl;

public class ReceptionistController {

    public void assignDoctor(Patient patientDetails,int id) {

        ReceptionistService receptionistService=ReceptionistServiceImpl.getInstance();
        receptionistService.assignDoctor(patientDetails,id);

    }

}
