/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.httprequestmeter.controller;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.httprequestmeter.defines.Defines;
import com.httprequestmeter.threads.ExecutorThread;
import com.httprequestmeter.utils.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mostafa.kashif
 */
public class HTTPRequestMeterMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Manager.readUserInput();
        Manager.startExecutorThreads();
        Manager.shutdownExecutorThreads();
        Manager.printOutput();
        
    }

}
