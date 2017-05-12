/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.httprequestmeter.defines;

import com.google.common.collect.TreeMultiset;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mostafa.kashif
 */
public class Defines {
    public static TreeMultiset<Long> successfullResponseTimeSet = TreeMultiset.create();
    public static int numberOfExecutorThreads;
    public static int numberOfRequestsPerThread;
    public static int requestTimeout;
    public static String URL;
    public static AtomicInteger successfullRequests = new AtomicInteger(0);
    public static AtomicInteger failledRequests = new AtomicInteger(0);
    public static AtomicInteger totalRequests = new AtomicInteger(0);
    public static int firedThreadsNumbers = 0;
    public static Date startTime;
    public static Date endTime;
    public static double totalTimeConsumed;
}
