package com.example.hospital.dao;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dto.Patient;
import com.example.hospital.service.AppContext;
import com.example.hospital.service.DoctorLoginService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DoctorDao {

    public static String doctorName=null;
    public static  String phoneNo=null;

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private DoctorDao()
    {

    }
    private static DoctorDao doctordao=null;
    public static DoctorDao getInstance()
    {
        if(doctordao==null)
        {
            doctordao=new DoctorDao();
        }
        return doctordao;
    }
    public void updateStatus(long id, String status) {

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("update doctor set status=? where id=?");) {

            ps.setString(1, status);
            ps.setInt(2, (int)id);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }

    }
    public Map< ? extends Number, Patient> getAssignedPatientList()
    {
        Map<Integer,Patient> patients = new HashMap<>();


        long id= DoctorLoginService.DOCTORID;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select id,name,symptoms,phoneNo from patient where doctor_id=? and status=?");) {


            ps.setLong(1, id);
            ps.setString(2, "assigned");
            ResultSet rs=ps.executeQuery();

            while(rs.next())
            {
                Patient patient= AppContext.getInstance().getObject("Patient");
                patient.setName(rs.getString(2));
                patient.setSymptoms(rs.getString(3));
                patient.setPhoneno(rs.getLong(4));
                patients.put(rs.getInt(1), patient);
                doctorName=rs.getString(2);
                phoneNo=rs.getString(4);


            }

            return patients;

        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return patients;

    }
    public int getCostOfTablet(String tableName)
    {
        int tabletcost = 0;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select cost from medicine_details where tablet_name=?");) {


            ps.setString(1,tableName);

            ResultSet rs=ps.executeQuery();

            while(rs.next())
            {
                tabletcost = rs.getInt("cost");
            }


        }
        catch(Exception e) {
            logger.info(e.toString());
        }

        return tabletcost;

    }

    public void writePrescription(long did,long pid,String presciption,String feedback,int finalBill)
    {

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("insert into prescription values(?,?,?,?,?,?)");
        ) {

            ps.setLong(1, did);
            ps.setLong(2, pid);
            ps.setString(3, presciption);
            ps.setString(4, feedback);
            ps.setInt(5, finalBill);
            ps.setBoolean(6, false);
            ps.executeUpdate();
        }

        catch(Exception e) {
            logger.info(e.toString());
        }
    }
    //TURN OFF CAPS
    public void getPatientBills()
    {

    }



    public void updatePatientStatus(long pid,long did)
    {

        try (PreparedStatement ps=BaseDao.getConnection().prepareStatement("update patient set status=? where id=?");
        ){



            ps.setString(1, "consulted");
            ps.setInt(2, (int)pid);
            ps.executeUpdate();
            updateStatus(did,"available");




        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
    }
    public List<List<String>> getPatientId(String n, String p)
    {
        List<List<String>> description =  new ArrayList<>();

        int pid=0;
        try (PreparedStatement ps=BaseDao.getConnection().prepareStatement("select id from patient where status=? and name=? and phoneNo=?");
             PreparedStatement ps1=BaseDao.getConnection().prepareStatement("select prescription_disease,feedback from prescription where pid=?");){

            ps.setString(1,"consulted");
            ps.setString(2, n);
            ps.setString(3, p);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                pid=rs.getInt(1);
            }
            if(pid!=0)
            {
                ps1.setInt(1, pid);
                ResultSet rs1=ps1.executeQuery();
                while(rs1.next())
                {
                    List<String> temp = new ArrayList<>();
                    temp.add(rs1.getString(1));
                    temp.add(rs1.getString(2));
                    description.add(temp);
                }
                return description;
            }

        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return description ;

    }

}
