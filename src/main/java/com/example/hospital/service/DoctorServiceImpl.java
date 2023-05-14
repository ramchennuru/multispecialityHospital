package com.example.hospital.service;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dao.DoctorDao;
import com.example.hospital.dto.Patient;
import com.example.hospital.exceptionHandling.NoPatientAssignedToThisDoctorException;
import com.example.hospital.exceptionHandling.TableNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class DoctorServiceImpl implements DoctorService{
    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private DoctorServiceImpl()
    {

    }
    private static DoctorServiceImpl doctorserviceimpl=null;
    public static DoctorServiceImpl getInstance()
    {
        if(doctorserviceimpl==null)
            doctorserviceimpl=new DoctorServiceImpl();
        return doctorserviceimpl;
    }
    DoctorDao doctordao=DoctorDao.getInstance();


    public void patientList(){

        Map<? extends Number, Patient> patients = doctordao.getAssignedPatientList();
        if(patients.isEmpty())
        {

            try
            {
                throw new NoPatientAssignedToThisDoctorException();
            }
            catch(Exception e)
            {
                logger.info(e.toString());
            }
        }
        else
        {
            patients.forEach((key,value) ->logger.info("id is: "+key +"\tname : "+value.getName()+"\tsymptoms : "+value.getSymptoms()+"\t phone no: "+value.getPhoneno()));
            if(DoctorDao.doctorName!=null && DoctorDao.phoneNo!=null)
            {

                List<List<String>> description = doctordao.getPatientId(DoctorDao.doctorName, DoctorDao.phoneNo);
                if(!description.isEmpty()) {
                    logger.info("this patient has already visited..details are .....");

                    description.forEach(obj ->  logger.info("prescription : "+obj.get(0)+"\t feedback :"+obj.get(1)) );
                }
            }
        }



    }


    public void prescription() throws IOException
    {

        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long did=DoctorLoginService.DOCTORID;
        logger.info("enter the  patient id");
        long pid=sc.nextLong();
        logger.info("enter the prescription for disease");
        StringJoiner presciption = new StringJoiner(",","{","}");
        String jj="";
        int finalBill = 0;
        while(true)
        {
            logger.info("enter name of tablet : ");
            String tablet = br.readLine();
            jj+="[ "+tablet+"-";

            int cost =  doctordao.getCostOfTablet(tablet);
            //ArrayList<Integer> c=doctordao.getCostOfTablet(tablet);
            if(cost>0) {

                logger.info("enter number of tablet : ");
                int  notablet = sc.nextInt();
                int billoftablet = notablet*cost;
                finalBill+=billoftablet;
                jj+=""+notablet+"-";

                logger.info("enter description of tablet : ");
                String description = br.readLine();
                jj+=""+description+"]";
                presciption.add(jj); //[dolo-9-threepillss per day]

                jj= "";
                logger.info("if u want to write more medicines please enter 1 : ");
                int opt = sc.nextInt();
                if(opt!=1) break;
            }else {
                try {
                    throw new TableNotFoundException();
                }catch(Exception e) {
                    logger.info(e.toString());
                }

            }



        }

        logger.info("enter the feedback");
        String feedback=br.readLine();


        doctordao.writePrescription(did,pid,presciption.toString(),feedback,finalBill);

        updateStatus(pid,did);
    }
    @Override
    public void updateStatus(long pid,  long did ) {

        doctordao.updatePatientStatus(pid,did);

    }

    public void updateStatus(long pid,  String status) {

        doctordao.updateStatus(pid,status);

    }
}
