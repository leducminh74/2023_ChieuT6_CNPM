package vn.hcmuaf.edu.fit.lab6.dao;

import vn.hcmuaf.edu.fit.lab6.beans.AccountStatus;
import vn.hcmuaf.edu.fit.lab6.db.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountStatusDao {
    private static AccountStatusDao instance;

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AccountStatusDao() {

    }

    public static AccountStatusDao getInstance() {
        if (instance == null) {
            instance = new AccountStatusDao();
        }
        return instance;
    }

    public AccountStatus getStatusAccountByStatusAccountId(int said){
        String query ="select * from status_account where `sa_id` = ?";
        try{
            conn = DBConnect.connect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,said);
            rs = ps.executeQuery();
            while (rs.next()){
                return new AccountStatus(
                        rs.getInt(1),
                        rs.getString(2));
            }
        }catch (Exception e){

        }
        return null;
    }
}
