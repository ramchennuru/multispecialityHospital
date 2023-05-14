package com.example.hospital.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineDao {

    public void insertIntoMedicine(String tabletName,int cost)
    {
        String query="insert into medicine_details(tablet_name,cost)values(?,?)";
        try(
                PreparedStatement ps=BaseDao.getConnection().prepareStatement(query);) {

            ps.setString(1,tabletName);
            ps.setInt(2,cost);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
