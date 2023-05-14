package com.example.hospital.service;

import com.example.hospital.controller.BaseController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Validations {

    Scanner sc = new  Scanner(System.in);
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));

    private Validations()
    {

    }
    private static Validations validations=null;
    public static Validations getInstance()
    {
        if(validations==null)
        {
            validations=new Validations();
        }
        return validations;
    }
    public String validateName() {


        String name="";
        try {
            name = bf.readLine();
        } catch (IOException e)
        {
            logger.info(e.toString());

        }
        if (name.matches("[a-zA-Z]{3,20}")) {
            return name;
        }
        else
        {
            String s1="Entered" +name+" is invalid !!!! please enter valid name :";
            logger.info(s1);
            return validateName();
        }
    }
    public long validatePhoneNo() {

        logger.info("enter your  phoneno : ");
        long phoneno = sc .nextLong();
        String temp=""+phoneno;

        if (temp.matches("[6-9][\\d]{9}")) {
            return phoneno;
        }
        else
        {
            logger.info("Entered phoneno is invalid !!!! please enter valid phoneno :");
            return validatePhoneNo();
        }
    }
    public long validateId() {

        logger.info("enter your  id : ");
        long id = sc .nextLong();
        String temp=""+id;

        if (temp.matches("[\\d]{1,10}") && id>0) {
            return id;
        }
        else
        {
            logger.info("Entered id is invalid !!!! please enter valid id :");
            return validateId();
        }
    }
    public int validateAge() {

        logger.info("enter your  age : ");
        int age = sc .nextInt();
        String temp=""+age;

        if (temp.matches("[\\d]{1,3}")&& age>0) {
            return age;
        }
        else
        {
            logger.info("Entered age is invalid !!!! please enter valid age :");
            return validateAge();
        }
    }
    public String validateSymptoms() {

        logger.info("enter your  symptoms : ");
        String symptoms = sc.next();

        String reg="[a-zA-Z]";
        Pattern p=Pattern.compile(reg);
        if (p.matcher(symptoms) != null) {
            return symptoms;
        }
        else
        {
            logger.info("Entered symptoms is invalid !!!! please enter valid symptoms :");
            return validateSymptoms();
        }
    }
    public String validatePassword() {

        String pwd = sc .nextLine();
        if (pwd.matches("[a-zA-Z!@#$%^&*0-9]{3,20}")) {
            return pwd;
        }
        else
        {
            String s="Entered" +pwd+" is invalid !!!! please enter valid password :";
            logger.info(s);
            return validatePassword();
        }
    }

}
