package com.example.hospital.dao;

import com.example.hospital.dto.Doctor;
import com.example.hospital.dto.Patient;
import com.example.hospital.service.AppContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdminDao {
    Logger logger = Logger.getLogger(AdminDao.class.getName());
    private AdminDao()
    {

    }
    private static AdminDao admindao=null;
    public static AdminDao getInstance()
    {
        if(admindao==null)
        {
            admindao=new AdminDao();
        }
        return admindao;
    }

    public boolean saveDoctor(Doctor doctor)
    {
        boolean flag=false;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("insert into doctor values(?,?,?,?,?,?)");) {

            ps.setInt(1, 0);
            ps.setString(2,doctor.getPassword());
            ps.setString(3,doctor.getName());
            ps.setString(4, doctor.getSpecialization());
            ps.setLong(5,doctor.getPhoneno());
            ps.setString(6,"available");
            ps.executeUpdate();
            flag=true;



        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return flag;
    }


    public void delete(long id) {

        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("delete from doctor where id=?");) {
            ps.setLong(1, id);
            String s=(ps.executeUpdate()==1)?"successfully deleted":"no doctor is available with that id"+id;
            logger.info(s);

        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }

    }
    public static List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> doctorDetails() {


        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select * from doctor");) {

            ResultSet rs=ps.executeQuery();

            while(rs.next())
            {
                Doctor docto= AppContext.getInstance().getObject("Doctor");
                docto.setId(rs.getLong(1));
                docto.setName(rs.getString(3));
                docto.setSpecialization(rs.getString(4));
                docto.setPhoneno(rs.getLong(5));
                docto.setStatus(rs.getString(6));
                doctors.add(docto);



            }


        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return doctors;
    }

    public List<Patient> displayPatients(String status) {
        List<Patient> patients=new ArrayList<>();



        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("select * from patient where status=?");) {

            ps.setString(1,status);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Patient patient=AppContext.getInstance().getObject("Patient");
                patient.setId(rs.getLong(1));
                patient.setName(rs.getString(2));
                patient.setAge(rs.getInt(3));
                patient.setSymptoms(rs.getString(4));
                patient.setPhoneno(rs.getLong(5));
                patient.setStatus(rs.getString(6));

                patients.add(patient);

            }
            return patients;


        }
        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return patients;
    }



    public boolean insertDiseases(String depttype, String disease)
    {
        boolean flag=false;
        try(PreparedStatement ps=BaseDao.getConnection().prepareStatement("insert into departments(departmentType,dieseaseName) values(?,?)");) {
            ps.setString(1, depttype);
            ps.setString(2, disease);
            ps.executeUpdate();
            flag=true;

        }

        catch(Exception e)
        {
            logger.info(e.toString());
        }
        return flag;
    }

}
