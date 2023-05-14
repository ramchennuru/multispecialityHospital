package com.example.hospital.service;

import com.example.hospital.controller.DoctorController;
import com.example.hospital.dao.BaseDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class DoctorLoginService {


    public static int DOCTORID;
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(DoctorLoginService.class.getName());
    }
    Validations validobj  =Validations.getInstance();
    private DoctorLoginService()
    {

    }
    private static DoctorLoginService doctorloginservice=null;
    public static DoctorLoginService getInstance()
    {
        if(doctorloginservice==null)
            doctorloginservice=new DoctorLoginService();
        return doctorloginservice;
    }
    public void doctorLogin()
    {

        int id =(int) validobj.validateId();
        logger.info("enter your password : ");
        String pswd =  "";
        try {
            pswd = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String password = "";
        try(PreparedStatement ps  = BaseDao.getConnection().prepareStatement("select doctor_password from doctor where id=?");)
        {
            ps.setInt(1,id);

            ResultSet res = ps.executeQuery();
            while(res.next())
            {
                password = res.getString("doctor_password");
            }

            if(pswd.equals(password))

            {
                logger.info("your details are verified");
                logger.info("you have been logged in");
                DOCTORID = id;
                DoctorController doctorController =DoctorController.getInstance();
                doctorController.displayDoctorServices();
            }
            else
            {
                logger.info("your credentials aree in valid");
                doctorLogin();

            }



        }
        catch(Exception e) {logger.info(e.toString());}


    }

}
