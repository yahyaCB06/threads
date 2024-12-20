package org.example;

import com.google.gson.Gson;
import org.example.model.Order;
import org.example.service.customerservice;
import org.example.service.orderservice;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException {

       Update update= new Update();

       update.start();
       System.out.println("test");
    }
    public static class Update extends Thread{

        List<Order> orders;
        @Override
        public void run(){
            while (true){
                try {
                    orders=takefrominput("input.txt");
                    output(orders);
                    viderinput("input.txt");
                    sleep(3600000);
                } catch (InterruptedException | IOException | SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
    public static void addtooutput(String output,Order order) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(output,true));
        try {
            String json = "{" +
                    "idOrder=" + order.getIdOrder() +
                    ", dateOrder='" + order.getDateOrder() + '\'' +
                    ", amount=" + order.getAmount() +
                    ", customerId=" + order.getCustomerId() +
                    '}';
            bw.write(json);
            bw.newLine(); // Add a newline for readability
        } finally {
            bw.flush();  // Ensure data is written
            bw.close();  // Close the writer
        }
    }
    public static void  viderinput(String input) throws IOException {
        FileWriter fw = new FileWriter(input);
    }
    public static List<Order> takefrominput(String input) throws IOException, SQLException, ClassNotFoundException {
        orderservice orderservice = new orderservice();
        List<Order> list = new ArrayList<Order>();
        String takejson="";
        BufferedReader takefile=new BufferedReader(new FileReader(input));
        while ((takejson=takefile.readLine())!=null){

            Gson gson = new Gson();
            Order order = gson.fromJson(takejson, Order.class);

            if(order!=null && !orderservice.getorder(order.getIdOrder())){

                System.out.println(order);
                list.add(order)
            ;}
        }
    return list;}
    public static void output(List<Order> list) throws SQLException, IOException, ClassNotFoundException {
     customerservice customerservice = new customerservice();
        orderservice orderservice = new orderservice();
        for(Order order:list){

           if(customerservice.getcustomer(order.getCustomerId())){


               addtooutput("output.txt",order);
               orderservice.orderinsert(order);

           }
           else{
               addtooutput("error.txt",order);
           }
        }
    }
}

