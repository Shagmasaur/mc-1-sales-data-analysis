package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalesDataAnalyzer
{
   public List<SalesRecord> readFile(String fileName) {
       List<SalesRecord> salesRecordList = new ArrayList<>();
       try{
           FileReader fileReader = new FileReader(fileName);
           BufferedReader bufferedReader = new BufferedReader(fileReader);
           String line = bufferedReader.readLine();
           while((line = bufferedReader.readLine()) != null){
               String[] strings = line.split(",");
               String date = strings[0];
               int customer_id = Integer.parseInt(strings[1]);
               int product_category = Integer.parseInt(strings[2]);
               String payment_method = strings[3];
               double amount = Double.parseDouble(strings[4]);
               double time_on_site = Double.parseDouble(strings[5]);
               int clicks_in_site = Integer.parseInt(strings[6]);

               salesRecordList.add(new SalesRecord(date,customer_id,product_category,payment_method,amount,time_on_site,clicks_in_site));
           }
       }catch(FileNotFoundException e){
           e.printStackTrace();
       }catch(IOException e){
           e.printStackTrace();
       }
       return salesRecordList;

   }

    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData)
    {
        salesData.sort(((o1, o2) -> (int)(o2.getAmount() - o1.getAmount())));

        return salesData;
    }
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData)
    {
        salesData.sort(((o1, o2) -> (int) (o2.getTime_on_site() - o1.getTime_on_site())));

        return salesData.get(0);
    }




}
