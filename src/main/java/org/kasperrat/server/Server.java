package org.kasperrat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    static String clientname;

    public static void main(String[] args) {


        try(ServerSocket ss = new ServerSocket(5461)){

            System.out.println("listening.. ");
            Socket s = ss.accept();

            PrintWriter pw = new PrintWriter(s.getOutputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner in = new Scanner(System.in);


            clientname = s.getInetAddress().getHostName();
            //pw.println("Welcome the server");
            //pw.flush();

            System.out.println("Connected to: " + clientname);

//            while(!(str = bf.readLine()).equals("bye")){
//
//                System.out.println(clientname + ": " + str);
//                String response = in.nextLine();
//                pw.println(response);
//            }

            //poc();

            String str;
            do{
                str = in.nextLine();
                pw.println(str);
                pw.flush();

                if(str.equals("bye")) break;

                System.out.println("Response: ");
                bf.lines().forEach(System.out::println);
                //System.out.println("Response: " + response);

            }while(true);

            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void poc(String cmd){

        try {
            Process p = new ProcessBuilder("ls").start();
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));

            bf.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
