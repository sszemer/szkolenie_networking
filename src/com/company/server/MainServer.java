package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true){
            Socket acceptedSocket = serverSocket.accept();
            executorService.submit(new ServerThread(acceptedSocket));
        }
    }
}