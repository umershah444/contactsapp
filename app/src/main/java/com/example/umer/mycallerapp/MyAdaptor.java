package com.example.umer.mycallerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class MyAdaptor extends ArrayAdapter<MyContacts> {

    public MyAdaptor(@NonNull Context context, int resource, @NonNull List<MyContacts> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=convertView;
        if(view==null)
        {
            LayoutInflater layoutInflater=LayoutInflater.from(getContext());

            view= layoutInflater.inflate(R.layout.my_adaptor_layout,null);
        }

        TextView name=(TextView) view.findViewById(R.id.name);
        TextView number=(TextView) view.findViewById(R.id.phone);
        MyContacts myContacts=getItem(position);
        name.setText(myContacts.getName());
        number.setText(myContacts.getPhone());
        return view;
    }
}
