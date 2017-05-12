/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.httprequestmeter.controller;

import com.httprequestmeter.defines.Defines;
import com.httprequestmeter.threads.ExecutorThread;
import com.httprequestmeter.utils.Utility;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mostafa.kashif
 */
public class Manager {

    private static ExecutorService executorsPool;

    public static void startExecutorThreads() {
        executorsPool = Executors.newFixedThreadPool(Defines.numberOfExecutorThreads);
        for (int i = 1; i <= Defines.numberOfExecutorThreads; i++) {
            ExecutorThread executorThread = new ExecutorThread(i);
            executorsPool.execute(executorThread);
            if (i == 1) {
                Defines.startTime = new Date();
            }
            Defines.firedThreadsNumbers++;
        }
    }

    public static void shutdownExecutorThreads() {
        try {
            executorsPool.shutdown();
            executorsPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            Defines.endTime = new Date();
        } catch (InterruptedException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter number of threads: ");
                Defines.numberOfExecutorThreads = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Please enter a valid number");
            }
        }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter number of requests per thread: ");
                Defines.numberOfRequestsPerThread = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Please enter a valid number");
            }
        }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter request timeout(milliseconds): ");
                Defines.requestTimeout = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Please enter a valid number");
            }
        }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter HTTP request URL: ");
                Defines.URL = scanner.nextLine();
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Please enter a valid URL");
            }
        }

    }

    public static void printOutput() {
        System.out.println("No. Of fired Threads: " + Defines.firedThreadsNumbers);
        System.out.println("StartTime: " + Defines.startTime);
        System.out.println("EndTime: " + Defines.endTime);
        Defines.totalTimeConsumed = Defines.endTime.getTime()-Defines.startTime.getTime();
        System.out.println("Total Time Consumed: " + Defines.totalTimeConsumed / 1000+" seconds");
        if (Defines.successfullResponseTimeSet.size() > 0 && Defines.successfullRequests.intValue() > 0) {
            
            System.out.println("Max Response Time: " + Defines.successfullResponseTimeSet.elementSet().last()+" milliseconds");
            System.out.println("Min Response Time: " + Defines.successfullResponseTimeSet.elementSet().first()+" milliseconds");
            System.out.println("Average Response Time: " + Utility.calculateAverageResponseTime(Defines.successfullResponseTimeSet)+" milliseconds");
        }
        else {
            System.out.println("Size of successfull requests equal zero,so max,min and average response time will not be calculated.");
        }

        
        System.out.println("Total No. of Successful requests: " + Defines.successfullRequests);
        System.out.println("Total No. of Failed requests: " + Defines.failledRequests);
        System.out.println("Rate: " + (Defines.totalRequests.intValue() / ((Defines.totalTimeConsumed / 1000) / 60))+" Request/Min");

    }
}
