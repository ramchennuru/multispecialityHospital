package com.example.hospital.dao;

import com.example.hospital.controller.BaseController;
import com.example.hospital.controller.ReceptionistController;
import com.example.hospital.dto.Patient;
import com.example.hospital.dto.PresciptionDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PatientDao {

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private PatientDao()
    {

    }
    private static PatientDao patientDao=null;
    public static PatientDao getInstance()
    {
        if(patientDao==null)
            patientDao=new PatientDao();
        return patientDao;
    }


    public void addPatientDetails(Patient patientDetails, String patientStatus, int id) {

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("insert into patient values(?,?,?,?,?,?,?)");) {


            ps.setLong(1,0);
            ps.setString(2,patientDetails.getName());
            ps.setInt(3,patientDetails.getAge());
            ps.setString(4, patientDetails.getSymptoms());
            ps.setLong(5, patientDetails.getPhoneno());
            ps.setString(6,patientStatus);
            ps.setLong(7, 0);
            ps.executeUpdate();

            ReceptionistController receptionistController=new ReceptionistController();
            receptionistController.assignDoctor(patientDetails,id);

        }


        catch(Exception e) {

            e.printStackTrace();
        }

    }
    /*public int payBill(String name, long phone)  {

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select bill from prescription where pid=?");
                PreparedStatement ps2 = BaseDao.getConnection().prepareStatement("select id from patient where name=? and phoneNo=?")){


            ps2.setString(1,name);
            ps2.setLong(2, phone);
            ResultSet res = ps2.executeQuery();
            int pid = 0;
            while(res.next())
            {
                pid = res.getInt(1);
            }
            PATIENTID =pid;
            ps.setInt(1,pid);
            ResultSet res2 = ps.executeQuery();
            int bill = 0;
            while(res2.next())
            {
                bill = res2.getInt(1);

            }
            return bill;


        }
        catch(Exception ex)
        {
            logger.info(ex.toString());

        }
        return 0;

    }*/
    public LinkedList<PresciptionDto> payBill(String name, long phone)  {

        LinkedList<PresciptionDto> prescription  =new LinkedList< >();

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select * from prescription where pid=?");
            PreparedStatement ps2 = BaseDao.getConnection().prepareStatement("select id from patient where name=? and phoneNo=?")){


            ps2.setString(1,name);
            ps2.setLong(2, phone);
            ResultSet res = ps2.executeQuery();
            int pid = 0;
            while(res.next())
            {
                pid = res.getInt(1);
            }
            PATIENTID =pid;
            ps.setInt(1,pid);
            ResultSet res2 = ps.executeQuery();

            while(res2.next())
            {
                PresciptionDto obj = new PresciptionDto(res2.getInt(1),res2.getInt(2),res2.getString(3),res2.getString(4),res2.getInt(5),res2.getInt(6));
                prescription.add(obj);
            }
            /*
             * int bill = 0; while(res2.next()) { bill = res2.getInt(1);
             *
             * } return bill;
             */


        }
        catch(Exception ex)
        {
            logger.info(ex.toString());

        }
        return prescription;

    }

    public static int PATIENTID = 0;

    public void updateBillSatus(int pid)
    {
        try(PreparedStatement ps =BaseDao.getConnection().prepareStatement("update prescription set payment_status=1 where pid=?");)
        {
            ps.setLong(1,pid);
            ps.executeUpdate();
            logger.info("your payment is completed");
        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
    }
    public List<Patient> getAllPatients()
    {
        List<Patient> list=new ArrayList<>();

        try(PreparedStatement ps1 =BaseDao.getConnection().prepareStatement("select * from patient");)
        {

            ResultSet rs=ps1.executeQuery();
            while(rs.next())
            {
                Patient patient=new Patient();
                patient.setId(rs.getLong(1));
                patient.setName(rs.getString(2));
                patient.setAge(rs.getInt(3));
                patient.setSymptoms(rs.getString(4));
                patient.setPhoneno(rs.getLong(5));
                patient.setStatus(rs.getString(6));
                list.add(patient);

            }

        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return list;
    }
}
