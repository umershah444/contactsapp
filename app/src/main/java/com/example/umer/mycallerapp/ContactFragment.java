package com.example.umer.mycallerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_contact, container, false);
        // Inflate the layout for this fragment

        DBHelper dbHelper=new DBHelper(getActivity(),"phoneBook",null,1);


        ListView listView=(ListView)view.findViewById(R.id.contactList);
        listView.setAdapter(new MyAdaptor(view.getContext(),R.layout.my_adaptor_layout,dbHelper.getData()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DBHelper dbHelper=new DBHelper(getActivity(),"phoneBook",null,1);

        Toast.makeText(getActivity(),"OnStart of Contact Fragment",Toast.LENGTH_SHORT).show();

        ListView listView=(ListView)view.findViewById(R.id.contactList);
        listView.setAdapter(new MyAdaptor(view.getContext(),R.layout.my_adaptor_layout,dbHelper.getData()));

    }
}
