package org.example.service;

import org.example.model.Customer;
import org.example.util.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerservice {
    connexion conn;
    Connection con;
    public customerservice() throws SQLException, ClassNotFoundException {
        conn=new connexion();
        this.con=conn.getConnection();
    }
    public boolean getcustomer(int customerid) throws SQLException {
        String sql="select * from customer where idCustomer=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, customerid);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            Customer c=new Customer();
            c.setId(rs.getInt("idCustomer"));
            c.setName(rs.getString("nameCustomer"));
            c.setEmail(rs.getString("emailCustomer"));
            return true;
        }
        return false;

    }

}
