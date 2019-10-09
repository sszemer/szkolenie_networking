package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1337);
        //od tąd
        Socket acceptedSocket = serverSocket.accept();
        InputStream inputStream = acceptedSocket.getInputStream();
        OutputStream outputStream = acceptedSocket.getOutputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine())!=null){
            System.out.println(line);
        }
        inputStream.close();
        outputStream.close();
        //do tąd
    }
}

//w domu otwierac w watkach nowe polaczenia