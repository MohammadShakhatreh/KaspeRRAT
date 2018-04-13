package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ServerSocket ss=null;
        try{
            ss=new ServerSocket(5461);
            System.out.println("listening.. ");
            Socket s = ss.accept();

            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner in = new Scanner(System.in);

            System.out.println("Connected with : " + s.getInetAddress().getHostName());


            String str=null;
            while(true){
                str = in.nextLine();
                pw.println(str);
                pw.flush();

                if(str.equals("bye")) {
                    s.close();
                    break;
                }
                System.out.println("Response : ");
                //bf.lines().forEach(System.out::println);
                String ret=".";
                while(true){
                    ret=bf.readLine();

                    if(ret.equals("`"))break;

                    System.out.println(ret);
                }
                System.out.println("OK!!!!!!!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
