package com.example.videoapp.utility;

import android.os.AsyncTask;

import com.example.videoapp.interfaces.MainMethodCallback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AsyncTaskForMain extends AsyncTask<String,Integer,String> {


    private MainMethodCallback mainMethodCallback;

    public AsyncTaskForMain(MainMethodCallback mainMethodCallback){
        this.mainMethodCallback=mainMethodCallback;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result=null;
        try {
            Map<String, String> map=new HashMap<>();
            map.put(strings[2],strings[3]);
            result=NetworkConnection.connectUsingHttpClient(new URL(strings[0]),strings[1],map);
        } catch (IOException e) {
            result=e.getLocalizedMessage();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mainMethodCallback.onResultReturn(result);
    }
}
