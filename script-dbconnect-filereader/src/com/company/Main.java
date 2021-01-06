package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import static com.company.PropertiesClass.getProp;

public class Main extends Properties {
    public static void main(String[] args) throws IOException {


        //Archive AUTHENTICATION
        Properties prop = getProp();

        String user = prop.getProperty("user");
        String url = prop.getProperty("url");
        String password = prop.getProperty("password");
        String jdbc = prop.getProperty("jdbc");


        //DATABASE CONNECTION && STATEMENT
        try{
            Class.forName(jdbc);

            Connection connec = DriverManager.getConnection(url, user, password);
            Statement statement = connec.createStatement();
            Statement statementTwo = connec.createStatement();
            Statement statementThree = connec.createStatement();

            //Connection Feedback
            if (connec != null){System.out.println("Connection Established!");}
            else{System.out.println("Connection Failed! Please try again");}

            //Archive WITH Queries
            //Scanner ler = new Scanner(System.in);
            File arquivo = new File("");


            try{
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                int lines = 0;
                while (reader.readLine() != null){
                    lines++;
                }

                FileReader txt = new FileReader(arquivo);
                BufferedReader lerTxt = new BufferedReader(txt);
                String line = lerTxt.readLine();
                String vet[] = new String[lines];
                int i;
                if (line == null){
                    System.out.print("Archive result is NULL");
                }

                else if(line != null){
                    for(i = 0; i < lines; i++){
                        vet[i] = line;


                        //DB Management - QUERIES
                        String queryTr = "select * from  where  = " +vet[i]+";";
                        ResultSet qryTr = statementThree.executeQuery(queryTr);
                        qryTr.next();
                        String table = qryTr.getString("tbname");
                        String name = qryTr.getString("name");
                        int value = qryTr.getInt("maxreserved");

                        //System.out.println(table);
                        //System.out.println(name);
                        //System.out.println(value);


                        String queryOne = "SELECT " + name + " FROM " + table + ";";
                        ResultSet qryOne = statement.executeQuery(queryOne);
                        qryOne.next();
                        int cur = qryOne.getInt(name);
                        //System.out.println(cur);

                        String queryTwoMax = "MAX("+name+") as maxi";
                        //System.out.println(queryTwoMax);
                        String queryTwo = "SELECT " + queryTwoMax +  " FROM "+ table +";";
                        ResultSet qryTwo = statementTwo.executeQuery(queryTwo);
                        qryTwo.next();
                        int max = qryTwo.getInt("maxi");
                        //System.out.println(max);


                        while(cur < max){
                            System.out.println(cur);
                            qryOne.next();
                            cur = qryOne.getInt(name);
                        }

                        System.out.println("- **Present Query Result: "+cur);// Current Query AFTER LOOP
                        ;
                        System.out.println("Next Line   -  Input Int: 1 ");
                        System.out.println("Close Archive  -  Input Int: 2 ");

                        Scanner sc = new Scanner(System.in);
                        int lineSelec = sc.nextInt();

                        if(lineSelec == 1){
                            line = lerTxt.readLine();
                            continue;
                        }

                        else if(lineSelec == 2){
                            txt.close();
                            System.out.print("Archive Closed!");
                            break;
                        }
                        else{
                            System.out.println("Wrong Command!");
                            lineSelec = sc.nextInt();
                        }
                    }
                }
                txt.close();
                System.out.println("No more Queries");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            connec.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}