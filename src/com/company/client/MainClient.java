package com.company.client;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
	// write your code here
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future q = executorService.submit(new ClientThread());
        Future w = executorService.submit(new ClientThread());
        q.get();
        w.get();
    }
}
