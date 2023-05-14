package com.example.hospital.service;

import com.example.hospital.exceptionHandling.DoctorNotAddedExcpetion;

public interface AdminService {

    void addDoctor() throws DoctorNotAddedExcpetion;

    void delete();

    void doctorDetails();
}
