package com.example.hospital.controller;


import com.example.hospital.exceptionHandling.DiseaseNotInsertedException;
import com.example.hospital.exceptionHandling.DoctorNotAddedExcpetion;
import com.example.hospital.service.AdminLoginService;
import com.example.hospital.service.AdminServiceImpl;
import com.example.hospital.service.MedicineServiceImpl;
import com.example.hospital.service.PatientServiceImpl;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminController {

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(AdminController.class.getName());
    }


    private AdminController()
    {

    }
    private static  AdminController admincontroller=null;
    public static AdminController getInstance()
    {
        if(admincontroller==null)
            admincontroller=new AdminController();
        return admincontroller;
    }
    public void login() throws DiseaseNotInsertedException {


        AdminLoginService adminLoginService=AdminLoginService.getInstance();
        adminLoginService.authenticate();
    }

    public void  adminFunctins() throws DiseaseNotInsertedException
    {
        int option;
        do {
            logger.info("1.Add Doctor\n 2.Delete Doctor\n3.DoctorDetails\n 4.PatientStatus\n5.add medicinesintoDb\n6.insertDiseases\n7.displayBasedOnAge");
            Scanner sc=new Scanner(System.in);
            int options=sc.nextInt();
            PatientServiceImpl ps=PatientServiceImpl.getInstance();
            AdminServiceImpl adminServiceImpl = AdminServiceImpl.getInstance();
            switch(options)
            {
                case 1:
                    try {
                        adminServiceImpl.addDoctor();
                    } catch (DoctorNotAddedExcpetion e)
                    {

                        logger.info(e.toString());
                    }
                    break;
                case 2:
                    adminServiceImpl.delete();
                    break;
                case 3:
                    adminServiceImpl.doctorDetails();
                    break;
                case 4:
                    adminServiceImpl.displayPatients();
                    break;
                case 5:
                    MedicineServiceImpl medicineservice=MedicineServiceImpl.getInstance();
                    medicineservice.insertMedicine();
                    break;
                case 6:
                    adminServiceImpl.insertDiseases();
                    break;
                case 7:
                    ps.getByAge();
                    break;
                default:
                    adminFunctins();
                    break;

            }
            logger.info("1.exit\n2.continue");
            option=sc.nextInt();
        }while(option==2);
    }






}