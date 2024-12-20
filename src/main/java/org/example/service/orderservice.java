package org.example.service;

import org.example.model.Order;
import org.example.util.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class orderservice {
    connexion conn;
    Connection con;
    public orderservice() throws SQLException, ClassNotFoundException {
        conn=new connexion();
        this.con = conn.getConnection();
    }
    public boolean getorder(int id) throws SQLException, ClassNotFoundException {
        String sql="select * from orders where idOrder=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeQuery();
        ResultSet rs=pst.getResultSet();
        if(rs.next()){
            return true;
        }
        return false;
    }
    public void orderinsert(Order order) throws SQLException, ClassNotFoundException {


        String sql = "insert into orders values(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,order.getIdOrder());
        pstmt.setString(2,order.getDateOrder());
        pstmt.setFloat(3,order.getAmount());
        pstmt.setInt(4,order.getCustomerId());
        pstmt.executeUpdate();}


}
