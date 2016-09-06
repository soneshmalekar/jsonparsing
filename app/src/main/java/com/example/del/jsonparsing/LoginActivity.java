package com.example.del.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends Activity {
    AQuery aq;
    EditText txtusername;
    Button login;
    String url="http://api.androidhive.info/contacts/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login);
        txtusername=(EditText)findViewById(R.id.txtusername);
        aq=new AQuery(this);
        fetchdata();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fetchdata();

            }
        });
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
        ArrayList<Contactinfo> list=new ArrayList<Contactinfo>();
        try {
            JSONArray contactsarray=object.getJSONArray("contacts");
            for(int i=0;i<contactsarray.length();i++)
            {
                JSONObject contactinfoobj=contactsarray.getJSONObject(i);
                Contactinfo info=new Contactinfo();
                info.id=contactinfoobj.getString("id");
                info.name=contactinfoobj.getString("name");
                info.email=contactinfoobj.getString("email");
                info.address=contactinfoobj.getString("address");
                list.add(info);
                        if(txtusername.getText().toString().equals(info.name))
                            {   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplication(), "sucees login", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplication(), "Invalid username", Toast.LENGTH_SHORT).show();
                            }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
