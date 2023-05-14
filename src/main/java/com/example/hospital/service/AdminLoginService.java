package com.example.hospital.service;


import com.example.hospital.controller.AdminController;
import com.example.hospital.dao.BaseDao;
import com.example.hospital.exceptionHandling.DiseaseNotInsertedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminLoginService {

    static Logger logger = Logger.getLogger(AdminLoginService.class.getName());
    private AdminLoginService()
    {

    }
    private static AdminLoginService adminloginservice=null;
    public static AdminLoginService getInstance()
    {
        if(adminloginservice==null)
            adminloginservice=new AdminLoginService();
        return adminloginservice;
    }
    public void authenticate() throws DiseaseNotInsertedException {

        AdminController adminController = AdminController.getInstance();


        boolean flag=getPassword();
        if(flag)
        {
            adminController.adminFunctins();
        }

    }


    public boolean getPassword()
    {

        Scanner sc=new Scanner(System.in);
        logger.info("enter the username");
        String username=sc.nextLine();
        logger.info("enter the password");
        String password=sc.next();
        String databasePswd = "";

        try(PreparedStatement ps  = BaseDao.getConnection().prepareStatement("select password from admin where name=?");)
        {
            ps.setString(1,username);

            ResultSet res = ps.executeQuery();
            while(res.next())
            {
                databasePswd = res.getString("password");
            }

            if( databasePswd.equals(password))
            {
                logger.info("your details are verified");
                logger.info("you have been logged in");
                return true;
            }
            else
            {
                logger.info("your details are invalid ");
                getPassword();

            }



        }
        catch(Exception e){
            logger.info(e.toString());
        }

        return false;

    }
}
