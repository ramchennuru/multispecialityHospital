package com.example.hospital.service;

import com.example.hospital.dao.PatientDao;
import com.example.hospital.dto.Patient;
import com.example.hospital.sorting.AscendingSortByAge;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PatientServiceImpl implements PatientService{

    static Logger logger = Logger.getLogger(PatientServiceImpl.class.getName());
    PatientDao patientDao=PatientDao.getInstance();
    @Override
    public void addPatientDetails(Patient patientDetails, String ps, int id) throws ClassNotFoundException {

        patientDao.addPatientDetails(patientDetails, ps, id);

    }
    private PatientServiceImpl()
    {

    }
    private static PatientServiceImpl patientserviceimpl=null;
    public static PatientServiceImpl getInstance()
    {
        if(patientserviceimpl==null)
        {
            patientserviceimpl=new PatientServiceImpl();
        }
        return patientserviceimpl;

    }
    @Override
    public void getByAge() {
        List<Patient> list=PatientDao.getInstance().getAllPatients();
        Collections.sort(list,new AscendingSortByAge());

        for(Patient p:list)
        {
            String ms=p.toString();
            logger.info(ms);
        }
    }

}
