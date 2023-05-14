package com.example.hospital.service;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dao.BaseDao;
import com.example.hospital.dto.Doctor;
import com.example.hospital.dto.Patient;
import com.example.hospital.exceptionHandling.NoDoctorAvailableException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class ReceptionistServiceImpl implements ReceptionistService{

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private ReceptionistServiceImpl()
    {

    }
    private static ReceptionistServiceImpl receptionistserviceimpl=null;
    public static ReceptionistServiceImpl getInstance()
    {
        if(receptionistserviceimpl==null)
        {
            receptionistserviceimpl=new ReceptionistServiceImpl();
        }
        return receptionistserviceimpl;
    }
    public void assignDoctor(Patient patientDetails, int id) {


        try(PreparedStatement ps= BaseDao.getConnection().prepareStatement("select * from doctor where specialization=? and status=?");
            PreparedStatement ps1=BaseDao.getConnection().prepareStatement("update doctor set status=? where id=? ");
            PreparedStatement ps2=BaseDao.getConnection().prepareStatement("update patient set doctor_id=?,status=?,id=? where name=? and (status=? or status=?)");
            PreparedStatement ps3=BaseDao.getConnection().prepareStatement("select departmentType from departments where dieseaseName=?")) {


            ps3.setString(1, patientDetails.getSymptoms());
            ResultSet rs=ps3.executeQuery();
            String specification="";
            while(rs.next())
            {
                specification=rs.getString(1);
            }
            ps.setString(2,"available");
            ps.setString(1,specification);
            ResultSet rs1=ps.executeQuery();
            Doctor doctor=null;
            while(rs1.next())
            {
                doctor=AppContext.getInstance().getObject("Doctor");
                doctor.setId(rs1.getLong(1));
                doctor.setName(rs1.getString(3));
                doctor.setSpecialization(rs1.getString(4));
                doctor.setPhoneno(rs1.getLong(5));
                doctor.setStatus(rs1.getString(6));
                logger.info(doctor.toString());

                break;
            }

            if(doctor==null)
            {
                logger.info("no doctor is available");
                throw new NoDoctorAvailableException();
            }
            else
            {
                ps1.setString(1,"unavailable");
                ps1.setLong(2,doctor.getId());
                ps1.executeUpdate();


                ps2.setLong(1, doctor.getId());
                ps2.setString(2, "assigned");
                ps2.setInt(3,id);
                ps2.setString(4,patientDetails.getName());
                ps2.setString(5, "registered");
                ps2.setString(6, "revisited");
                ps2.executeUpdate();
                logger.info("Doctor id "+doctor.getId()+"  "+"is assigned to"+" "+patientDetails.getName());

            }


        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }


    }
}
