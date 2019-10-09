package com.company.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) throws IOException {
	// write your code here
        Socket localhost = new Socket("localhost", 1337);
        OutputStream outputStream = localhost.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes("elo z clienta");
        outputStream.close();
    }
}
