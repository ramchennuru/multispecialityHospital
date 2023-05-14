package com.example.hospital.controller;


import com.example.hospital.service.OldPatientServiceImpl;
import com.example.hospital.service.Validations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class OldPatientController {

    Scanner sc=new Scanner(System.in);
    private OldPatientController()
    {

    }
    private static OldPatientController oldpatientcontroller=null;
    public static OldPatientController getInstance()
    {
        if(oldpatientcontroller==null)
        {
            oldpatientcontroller=new OldPatientController();
        }
        return oldpatientcontroller;
    }
    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
    Validations validations=Validations.getInstance();
    public void  login()
    {


        OldPatientServiceImpl oldpatientservice=new OldPatientServiceImpl();
        oldpatientservice.login();



    }

}
