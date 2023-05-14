package com.example.hospital.service;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dao.AdminDao;
import com.example.hospital.dto.Doctor;
import com.example.hospital.dto.Patient;
import com.example.hospital.exceptionHandling.DiseaseNotInsertedException;
import com.example.hospital.exceptionHandling.DoctorNotAddedExcpetion;
import com.example.hospital.exceptionHandling.NoPatientFoundForThisStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminServiceImpl implements AdminService{

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private AdminServiceImpl()
    {

    }
    private static AdminServiceImpl adminserviceimpl=null;
    public static AdminServiceImpl getInstance()
    {
        if(adminserviceimpl==null)
        {
            adminserviceimpl=new AdminServiceImpl();
        }
        return adminserviceimpl;
    }

    Validations validobj=Validations.getInstance();
    AdminDao adminDao=AdminDao.getInstance();



    public void addDoctor() throws DoctorNotAddedExcpetion {

        logger.info("enter the name");
        String name= validobj.validateName();
        logger.info("enter the specialization");
        String specialization= validobj.validateName();
        logger.info("enter password for doctor : ");
        String password= validobj.validatePassword();
        long phoneno=validobj.validatePhoneNo();
        Doctor doctor=new Doctor(name,specialization,password,phoneno);
        boolean status=adminDao.saveDoctor(doctor);
        if(status)
        {
            logger.info("new doctor added");
        }
        else
        {
            throw new DoctorNotAddedExcpetion();
        }

    }

    public void delete() {

        long id=validobj.validateId();
        adminDao.delete(id);

    }

    public void doctorDetails() {


        List<Doctor> doctors = adminDao.doctorDetails();

        doctors.stream().forEach(obj->logger.info("doctor id:"+obj.getId()+"\t doctor name:"+obj.getName()+"\t\t2 specialization:"+obj.getSpecialization()+"\t phone no:"+obj.getPhoneno()));
    }

    public void displayPatients() {

        Scanner sc=new Scanner(System.in);
        logger.info("registered or assigned or  consulted");
        String status=sc.next();

        List<Patient> patients= adminDao.displayPatients(status);
        if(patients.isEmpty())
        {

            try {
                throw  new NoPatientFoundForThisStatusException(status);
            }
            catch(Exception e)
            {
                logger.info(e.toString());
            }

        }
        else
        {
            patients.stream().forEach(ob->logger.info("patient id:"+ob.getId()+"\t patient name:"+ob.getName()+"\t patient age:"+ob.getAge()+"\t symptoms:"+ob.getSymptoms()+"\t phone no:"+ob.getPhoneno()));
        }


    }
    public void insertDiseases( ) throws DiseaseNotInsertedException
    {
        logger.info("enter the departmentType");
        String depttype = "";
        String disease ="";
        try {
            depttype=bf.readLine();
            logger.info("enter the disease name");
            disease=bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean status=adminDao.insertDiseases(depttype, disease);
        if(status)
        {
            logger.info("medicine inserted");
        }
        else
        {
            throw new DiseaseNotInsertedException();
        }



    }
}
