package com.example.videoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.videoapp.interfaces.MainMethodCallback;
import com.example.videoapp.utility.AsyncTaskForMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMethodCallback {

    private ListView listView;
    private final String path="\n" +
            "https://api.vimeo.com/categories\n" +
            "\n";
    private String items[];
    private TextView textView;
    private final String requestMethod="GET";
    private final String param="Authorization";
    private final String paramValue="bearer b2918607b4301812914f5a5141c4d7da";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHold();
    }

    public void onButtonPress(View view){

        AsyncTaskForMain asyncTaskForMain=new AsyncTaskForMain(this);
        asyncTaskForMain.execute(path,requestMethod,param,paramValue);
    }

    @Override
    public void onResultReturn(String result){
        if(result!=null){
            getTheRequiredPart(result);
            listView.setVisibility(View.VISIBLE);
            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
            listView.setAdapter(adapter);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void getTheRequiredPart(String result){
        List<String> nameList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray_1=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray_1.length();i++){
                JSONObject obj=jsonArray_1.getJSONObject(i);
                nameList.add(obj.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!nameList.isEmpty()) {
            items = nameList.toArray(new String[0]);
        }
    }

    private void getHold(){
        listView=(ListView)findViewById(R.id.list_view);
        textView=(TextView)findViewById(R.id.not_available);
    }
}
