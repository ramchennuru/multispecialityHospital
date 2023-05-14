package com.example.hospital.dao;

import com.example.hospital.controller.BaseController;
import com.example.hospital.dto.Patient;
import com.example.hospital.service.AppContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class OldPatientDao {

    public static int PATIENTID=0;

    private static Logger logger = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(BaseController.class.getName());
    }
    private OldPatientDao()
    {

    }
    public static OldPatientDao oldpatientdao=null;
    public static OldPatientDao getInstance()
    {
        if(oldpatientdao==null)
        {
            oldpatientdao=new OldPatientDao();
        }
        return oldpatientdao;
    }
    public Patient getPatient(String name,long phoneno)
    {
        Patient p=null;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select * from patient where name=?");){

            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            @SuppressWarnings("unused")
            String ph=""+ phoneno;
            p=AppContext.getInstance().getObject("Patient");
            while(rs.next())
            {
                PATIENTID = rs.getInt(1);
                p.setName(name);
                p.setAge(rs.getInt(3));
                p.setSymptoms(rs.getString(4));
                p.setPhoneno(rs.getLong(5));
                return p;
            }


        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return p;
    }
}
