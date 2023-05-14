package com.example.hospital.controller;

import com.example.hospital.service.DoctorLoginService;
import com.example.hospital.service.DoctorServiceImpl;
import com.example.hospital.service.Validations;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class DoctorController {

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(DoctorController.class.getName());
    }
    DoctorController()
    {

    }
    private static  DoctorController doctorcontroller=null;
    public static DoctorController getInstance()
    {
        if(doctorcontroller==null)
        {
            doctorcontroller=new DoctorController();
        }
        return doctorcontroller;
    }
    Scanner sc=new Scanner(System.in);
    DoctorServiceImpl doctorService=DoctorServiceImpl.getInstance();
    Validations validobj = Validations.getInstance();


    public void updateStatus() {


        long id= DoctorLoginService.DOCTORID;
        logger.info("enter the doctor status");
        String status=sc.next();
        doctorService.updateStatus(id,status);
    }



    public void displayDoctorServices() throws IOException
    {	int num;
        do
        {
            logger.info("enter 1.getAssignedPatientList/n 2.writePrescription/n 3.updateStatus");
            Scanner scanner=new Scanner(System.in);
            int option=scanner.nextInt();
            DoctorServiceImpl doctorserviceimpl=DoctorServiceImpl.getInstance();
            switch(option)
            {
                case 1:

                    doctorserviceimpl.patientList();
                    break;

                case 2:
                    doctorserviceimpl.prescription();
                    break;
                case 3:
                    updateStatus();
                    break;
                default:
                    logger.info("you have entered incorrect option");
                    displayDoctorServices();
                    break;


            }
            logger.info("press 1.home2.exit");
            num=sc.nextInt();
        }while(num==1);
    }
}
