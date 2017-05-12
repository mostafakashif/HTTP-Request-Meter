/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.httprequestmeter.threads;

import com.httprequestmeter.controller.HTTPRequestMeterMain;
import com.httprequestmeter.defines.Defines;
import com.httprequestmeter.utils.Utility;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mostafa.kashif
 */
public class ExecutorThread implements Runnable {

    private int threadNumber;
    private int httpResponse;

    public ExecutorThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Executor_" + threadNumber);
        System.out.println( Thread.currentThread().getName()+" started");
       for (int i = 0; i < Defines.numberOfRequestsPerThread; i++) {
            long executionTime = 0;
            long startTime = 0;
            try {
                startTime = System.currentTimeMillis();
                httpResponse = Utility.sendGet(Defines.URL);
                executionTime = System.currentTimeMillis() - startTime;

                if (httpResponse == 200) {
                    Defines.successfullResponseTimeSet.add(executionTime);
                    Defines.successfullRequests.incrementAndGet();
                    Defines.totalRequests.incrementAndGet();
                  
                } else {
                    Defines.failledRequests.incrementAndGet();
                    Defines.totalRequests.incrementAndGet();
                }

            } catch (Exception ex) {
                executionTime = System.currentTimeMillis() - startTime;
                Defines.failledRequests.incrementAndGet();
                Defines.totalRequests.incrementAndGet();
                //Logger.getLogger(ExecutorThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR in "+Thread.currentThread().getName() + ex.getMessage());
            }
        }
       System.out.println( Thread.currentThread().getName()+" ended");
    }

}
