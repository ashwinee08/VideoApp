package com.example.videoapp.utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetworkConnection {

    private final static int connectionTimeout =13000;
    public static String connectUsingHttpClient(URL url, String requestMethod, Map<String, String> parameters) throws IOException {
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        httpURLConnection.setReadTimeout(connectionTimeout);
        if(!parameters.isEmpty()){
            for(String key:parameters.keySet()){
                httpURLConnection.setRequestProperty(key,parameters.get(key));
            }
        }
        httpURLConnection.connect();
        if(httpURLConnection.getResponseCode()==200){
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String buffer=null;
            StringBuffer result=null;

            while((buffer=bufferedReader.readLine())!=null){
                result.append(buffer);
            }
           return result.toString();
        }else{
            return null;
        }
    }
}
