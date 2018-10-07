package com.example.umer.mycallerapp;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_CONTACTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    List<MyContacts> ls=new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},2);
        getContacts();
        ListView listView=(ListView)view.findViewById(R.id.listview);
        listView.setAdapter(new MyAdaptor(view.getContext(),R.layout.my_adaptor_layout,ls));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /* Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ls.get(i).getPhone()));
                startActivity(intent);*/

               onTap(i);
            }
        });
        return view ;
    }


    void getContacts()
    {

        ContentResolver contentResolver=getContext().getContentResolver();
        Cursor cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
          MyContacts myContacts=new MyContacts();
          myContacts.setPhone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
          myContacts.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
          ls.add(myContacts);
            cursor.moveToNext();
        }
    }

    void onTapNew(int position)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(getLayoutInflater().inflate(R.layout.alert_dialog,null,false));
        builder.setTitle("Add Contact Dialog");
        builder.setMessage("Press Add if you want to add this contact.");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }















    void onTap(final int position)
    {
         AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        View view=getLayoutInflater().inflate(R.layout.alert_dialog,null,false);
        builder.setMessage("Tapped");
        builder.setTitle("Title");
        builder.setView(view);
        /*builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Yes Tapped", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "No tapped", Toast.LENGTH_SHORT).show();
            }
        });*/

        final AlertDialog alertDialog=builder.create();
        alertDialog.show();




        Button close=(Button) view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper=new DBHelper(getContext(),"phoneBook",null,1);
                MyContacts myContacts=ls.get(position);
                dbHelper.insertData(myContacts.getName(),myContacts.getPhone());
                alertDialog.hide();
            }
        });


    }

}
