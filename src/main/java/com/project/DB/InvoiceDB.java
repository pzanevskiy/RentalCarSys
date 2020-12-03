package com.project.DB;

import com.project.entities.Invoice;
import com.project.enums.InvoiceStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDB {

    public static List<Invoice> getAllInvoices(){
        List<Invoice> invoices = new ArrayList<>();
        String sql="SELECT * FROM invoices";
        try(Connection conn= DB.loadDB()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Invoice invoice = getInvoice(rs);
                invoices.add(invoice);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }
    public static ArrayList<Invoice> getInByUserId(int id){
        ArrayList<Invoice> invoices=new ArrayList<>();
        String sql="SELECT * FROM invoices WHERE user_id=?";
        try{
            try(Connection conn=DB.loadDB()) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Invoice invoice= getInvoice(resultSet);
                        invoices.add(invoice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static ArrayList<Invoice> getInByUserIdAndStatus(int id, InvoiceStatus status){
        ArrayList<Invoice> invoices=new ArrayList<>();
        String sql="SELECT * FROM invoices WHERE user_id=? AND status=?";
        try{
            try(Connection conn=DB.loadDB()) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    preparedStatement.setString(2,status.toString().toLowerCase());
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Invoice invoice= getInvoice(resultSet);
                        invoices.add(invoice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static Invoice getInvoiceById(int id) {
        Invoice invoice=null;
        String sql="SELECT * FROM invoices WHERE id=?";
        try(Connection conn= DB.loadDB()) {
            try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    invoice = getInvoice(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return invoice;
    }

    public static List<Invoice> getInvoicesByStatus(InvoiceStatus invoiceStatus, int userId){
        List<Invoice> invoices=new ArrayList<>();
        String sql="SELECT * FROM invoices WHERE status=? AND user_id=?";
        try(Connection conn= DB.loadDB()) {
            try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                preparedStatement.setString(1,invoiceStatus.toString().toLowerCase());
                preparedStatement.setInt(2,userId);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    Invoice invoice = getInvoice(resultSet);
                    invoices.add(invoice);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }

    public static int addInvoice(Invoice invoice){
        String sql="INSERT INTO invoices (id, order_id, user_id, price, message, status) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn= DB.loadDB()) {
            try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                preparedStatement.setInt(1,invoice.getId());
                preparedStatement.setInt(2,invoice.getOrder().getId());
                preparedStatement.setInt(3,invoice.getOrder().getUser().getId());
                preparedStatement.setInt(4,invoice.getPrice());
                preparedStatement.setString(5,invoice.getMessage());
                preparedStatement.setString(6,invoice.getInvoiceStatus().toString().toLowerCase());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }


    public static int removeInvoice(int id){
        String sql="DELETE FROM invoices WHERE id=?";
        try(Connection conn= DB.loadDB()) {
            try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                preparedStatement.setInt(1,id);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    public static int updateInvoiceStatus(InvoiceStatus status,int id){
        String sql="UPDATE invoices SET status=? WHERE id=?";
        try(Connection conn= DB.loadDB()) {
            try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                preparedStatement.setString(1,status.toString().toLowerCase());
                preparedStatement.setInt(2,id);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    private static Invoice getInvoice(ResultSet resultSet) throws SQLException{
        Invoice invoice=new Invoice();
        invoice.setId(resultSet.getInt(1));
        invoice.setOrder(OrderDB.getOrderById(resultSet.getInt(2)));
        invoice.setUserId(resultSet.getInt(3));
        invoice.setPrice(resultSet.getInt(4));
        invoice.setMessage(resultSet.getString(5));
        String status=resultSet.getString(6);

        switch (status){
            case "paid": { invoice.setInvoiceStatus(InvoiceStatus.PAID); break; }
            case "not_paid": { invoice.setInvoiceStatus(InvoiceStatus.NOT_PAID); break; }
            default: { break; }
        }

        return invoice;
    }
}
