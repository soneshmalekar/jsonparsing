package com.example.del.jsonparsing;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AQuery aq;
    ListView listView;
    ImageView image;
    EditText txtusername,txtpassword;
    Button logout;
    String url="http://192.168.30.223:8080/test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.image);
        listView=(ListView)findViewById(R.id.listview);

//        txtusername=(EditText)findViewById(R.id.txtusername);
        aq=new AQuery(this);
        aq.id(image).image("http://www.onlinekhabar.com/wp-content/uploads/2015/12/Dil-Nath-Giri.jpg");
        fetchdata();
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }
    public void fetchdata()
    {
        aq.ajax(url,JSONObject.class,new AjaxCallback<JSONObject>()
        {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(object);
                Log.i("datas are", object.toString());
                parsedata(object);
            }
        });
    }

    public void parsedata(JSONObject object)
        {
            ArrayList<CourseInfo> list=new ArrayList<CourseInfo>();
            try {
                JSONArray contactsarray=object.getJSONArray("contacts");
                for(int i=0;i<contactsarray.length();i++)
                    {
                            JSONObject contactinfoobj=contactsarray.getJSONObject(i);
                            CourseInfo info=new CourseInfo();
                            info.facultyname=contactinfoobj.getString("faculty");
                            JSONArray alllist=contactinfoobj.getJSONArray("classe");
                            for(int j=0;j<alllist.length();j++)
                                {
                                    info.list1=alllist.getString(j);


                                }
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            populatedata(list);
        }
    public void populatedata(ArrayList<CourseInfo> list)
    {
        listView.setAdapter(new UserListAdapterActivity(getBaseContext(),0,list));
        }



}
