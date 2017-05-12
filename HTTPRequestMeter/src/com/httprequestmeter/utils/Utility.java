/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.httprequestmeter.utils;

import com.google.common.collect.TreeMultiset;
import com.httprequestmeter.controller.HTTPRequestMeterMain;
import com.httprequestmeter.defines.Defines;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author mostafa.kashif
 */
public class Utility {

    

    public static int sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(Defines.requestTimeout);
        con.setReadTimeout(Defines.requestTimeout);
        

        return con.getResponseCode();

    }

    public static double calculateAverageResponseTime(TreeMultiset treeMultiSet) {
        Iterator setIterator = treeMultiSet.iterator();
        long sum = 0;
        while (setIterator.hasNext()) {
            long responseTime = Long.valueOf(setIterator.next().toString());
            sum = sum + responseTime;
        }
        if (treeMultiSet.size() > 0 && sum > 0) {
            return sum / treeMultiSet.size();
        } else {
            return 0;
        }

    }
}
