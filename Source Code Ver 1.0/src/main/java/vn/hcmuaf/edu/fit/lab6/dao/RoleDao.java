package vn.hcmuaf.edu.fit.lab6.dao;

import vn.hcmuaf.edu.fit.lab6.beans.Role;
import vn.hcmuaf.edu.fit.lab6.db.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleDao {
    private static RoleDao instance;

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public RoleDao() {

    }

    public static RoleDao getInstance() {
        if (instance == null) {
            instance = new RoleDao();
        }
        return instance;
    }

    public Role getRoleByRoleId(int rid){
        String query ="select * from role where `role_id` = ?";
        try{
            conn = DBConnect.connect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,rid);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Role(
                        rs.getInt(1),
                        rs.getString(2));
            }
        }catch (Exception e){

        }
        return null;
    }
}
