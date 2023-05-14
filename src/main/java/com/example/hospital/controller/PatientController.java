package com.example.hospital.controller;

import com.example.hospital.dao.BaseDao;
import com.example.hospital.dto.Patient;
import com.example.hospital.service.AppContext;
import com.example.hospital.service.PatientServiceImpl;
import com.example.hospital.service.Validations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class PatientController {


    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private PatientController()
    {

    }
    private static PatientController patientcontroller=null;
    public static PatientController getInstance()
    {
        if(patientcontroller==null)
            patientcontroller=new PatientController();
        return patientcontroller;
    }




    Scanner sc=new Scanner(System.in);
    PatientServiceImpl patientService=PatientServiceImpl.getInstance();

    Validations validateobj1=Validations.getInstance();

    public void captureSymptoms() throws ClassNotFoundException
    {
        logger.info("Hospital services are");
        Set<String> set=new HashSet<>();
        try(PreparedStatement ps= BaseDao.getConnection().prepareStatement("select departmentType from departments"))
        {
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                set.add(rs.getString(1));
            }
        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        set.stream().forEach(t->logger.info(t));
        logger.info("if you want to proceed?:yes or no");
        String b=sc.nextLine();
        if(b.equalsIgnoreCase("yes"))
        {
            patientService.addPatientDetails(getPatientDetails(),"registered",getSequence());
        }
        else
        {
            logger.info("thank you for visiting ");
        }

    }

    public int getSequence()
    {
        int value = 0;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select * from auto_increment_value");
            PreparedStatement ps1=BaseDao.getConnection().prepareStatement("update auto_increment_value set value=?"))
        {
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                value =  rs.getInt(1);

            }
            ps1.setInt(1, value+1);
            ps1.executeUpdate();
        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return value;
    }

    public Patient getPatientDetails()
    {
        Patient patient= AppContext.getInstance().getObject("Patient");
        logger.info("enter ur name");
        String name=validateobj1.validateName();
        int age =validateobj1.validateAge();
        String symptoms=validateobj1.validateSymptoms();

        long phoneno=validateobj1.validatePhoneNo();
        patient.setName(name);
        patient.setAge(age);
        patient.setSymptoms(symptoms);

        patient.setPhoneno(phoneno);


        return patient;

    }
    public void checkPatient()
    {
        logger.info("1.Old Patient\n 2.New Patient");
        try {
            Scanner scanner=new Scanner(System.in);
            int option=scanner.nextInt();
            switch(option)
            {
                case 1:
                    OldPatientController opc=OldPatientController.getInstance();
                    opc.login();
                    break;
                case 2:

                    captureSymptoms();
                    break;
                default:
                    logger.info("invalid option");
                    checkPatient();
                    break;
            }
        } catch (Exception e) {

            logger.info("u entered wrong input");
            checkPatient();
        }
    }

}
