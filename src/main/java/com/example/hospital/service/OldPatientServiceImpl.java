package com.example.hospital.service;

import com.example.hospital.controller.BaseController;
import com.example.hospital.controller.OldPatientController;
import com.example.hospital.dao.OldPatientDao;
import com.example.hospital.dao.PatientDao;
import com.example.hospital.dto.Patient;

import java.util.logging.Logger;

public class OldPatientServiceImpl {

    public static int PATIENTID = 0;
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    Validations validobj =Validations.getInstance();
    OldPatientDao oldPatientDao =OldPatientDao.getInstance();

    public  void login( )
    {
        logger.info("enter patient name");
        String name=validobj.validateName();

        long phoneno=validobj.validatePhoneNo();
        String ph =""+phoneno;
        Patient obj = oldPatientDao.getPatient(name, phoneno);
        String phno = ""+obj.getPhoneno();
        if( phno.equals(ph))
        {

            getPresentSymptoms(obj);


        }
        else
        {
            logger.info("inavalid details");
            OldPatientController opc=OldPatientController.getInstance();
            opc.login();
        }
    }



    public void getPresentSymptoms(Patient p)
    {
        Validations vl=Validations.getInstance();

        String sysmpoms = vl.validateSymptoms();
        p.setSymptoms(sysmpoms);
        PatientDao patientDao =PatientDao.getInstance();

        patientDao.addPatientDetails(p,"revisited",OldPatientDao.PATIENTID);



    }
}
