package com.example.hospital.service;

import com.example.hospital.dao.MedicineDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Logger;

public class MedicineServiceImpl {
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(MedicineServiceImpl.class.getName());
    }
    private MedicineServiceImpl()
    {

    }
    private static MedicineServiceImpl medicineserviceimpl=null;
    public static MedicineServiceImpl getInstance()
    {
        if(medicineserviceimpl==null)
            medicineserviceimpl=new MedicineServiceImpl();
        return medicineserviceimpl;
    }




    MedicineDao medicineDao = new MedicineDao();
    public void insertMedicine()
    {
        Scanner sc7=new Scanner(System.in);
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        logger.info("enter the medicine name");
        String tabletName="";
        try {
            tabletName=bf.readLine();
        } catch (IOException e) {

        }
        logger.info("enter the cost of tablet");
        int cost=sc7.nextInt();

        medicineDao.insertIntoMedicine(tabletName, cost);




    }
}
