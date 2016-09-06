package com.example.del.jsonparsing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapterActivity extends ArrayAdapter<CourseInfo> {
    Context context;

    public UserListAdapterActivity(Context context, int resource, ArrayList<CourseInfo> list) {
        super(context, resource, list);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final Contactinfo info=getItem(position);
      final CourseInfo info=getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_list_adapter, null);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView email=(TextView)view.findViewById(R.id.email);
        TextView address=(TextView)view.findViewById(R.id.address);
        name.setText(info.facultyname);
        email.setText(info.name);
        address.setText(info.section);

        return view;

    }


}
