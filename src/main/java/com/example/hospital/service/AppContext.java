package com.example.hospital.service;

import com.example.hospital.dto.Admin;
import com.example.hospital.dto.Doctor;
import com.example.hospital.dto.Patient;

public class AppContext {

    private AppContext()
    {

    }
    private static AppContext appContext = null;
    public static AppContext getInstance()
    {
        if(appContext==null)
        {
            appContext = new AppContext();
        }
        return appContext;

    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String className)
    {
        if(className.equals("Patient"))
            return (T)  new Patient();

        else if(className.equals("Admin"))
            return (T) new Admin();

        else
            return (T) new Doctor();
    }
}
