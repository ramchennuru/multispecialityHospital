package com.example.hospital.controller;

import com.example.hospital.exceptionHandling.DiseaseNotInsertedException;
import com.example.hospital.service.DoctorLoginService;
import com.example.hospital.service.PayBillServiceImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class BaseController {
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        multispecialityhospitalstarts();
    }


    public static void multispecialityhospitalstarts() throws ClassNotFoundException, IOException{
        int opt;


        do
        {
            Scanner sc=new Scanner(System.in);
            logger.info("1.Patient\n 2.Doctor\n 3.Admin\n 4.PayBill");
            int option=sc.nextInt();
            switch(option)
            {
                case 1:
                    PatientController patientController=PatientController.getInstance();
                    patientController.checkPatient();
                    break;
                case 2:
                    DoctorLoginService doctorLoginService =DoctorLoginService.getInstance();
                    doctorLoginService.doctorLogin();
                    break;

                case 3:
                    AdminController adminController=AdminController.getInstance();
                    try {
                        adminController.login();
                    } catch (DiseaseNotInsertedException e) {

                        e.printStackTrace();
                    }
                    break;
                case 4:
                    PayBillServiceImpl payBillServiceImpl =PayBillServiceImpl.getInstance();
                    payBillServiceImpl.payBill();
                    break;
                default:
                    logger.info("ur entered wrong option please enter valid option");
                    multispecialityhospitalstarts();
                    break;
            }
            logger.info("1.exit/n 2.continue");
            opt=sc.nextInt();
        }
        while(opt==2);





    }
}
