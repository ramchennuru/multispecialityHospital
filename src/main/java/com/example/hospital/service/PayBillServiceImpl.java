package com.example.hospital.service;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dao.PatientDao;
import com.example.hospital.dto.PresciptionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Logger;

public class PayBillServiceImpl {

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private PayBillServiceImpl()
    {

    }
    private static PayBillServiceImpl paybillserviceimpl=null;
    public static PayBillServiceImpl getInstance()
    {
        if(paybillserviceimpl==null)
            paybillserviceimpl=new PayBillServiceImpl();
        return paybillserviceimpl;
    }


    int bill = 0;
    public void payBill() throws IOException
    {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        logger.info("enter patient name");
        String name=bf.readLine();
        logger.info("enter the phone no");
        String phoneno=bf.readLine();
        long phone=Long.parseLong(phoneno);

        PatientDao patientDao = PatientDao.getInstance();

        LinkedList<PresciptionDto> list = patientDao.payBill(name,phone);

        Optional<PresciptionDto> pre = list.stream().filter(obj -> obj.getBillStatus()==0).findFirst();

        pre.stream().forEach( obj -> bill = obj.getBill()+200);

        String bil="your hospital bill is : "+bill;
        logger.info(bil);
        logger.info(("enter  1 to preced payment or enter- any other key to cancle"))
        ;			String opt = bf.readLine();
        if(opt.equals("1"))
        {
            patientDao.updateBillSatus(PatientDao.PATIENTID);
        }
        else
        {
            logger.info("your  payment is cancled");

        }

    }

}
