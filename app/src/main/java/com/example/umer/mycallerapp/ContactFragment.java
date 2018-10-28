package com.example.umer.mycallerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

       // Toast.makeText(getActivity(),"OnStart of Contact Fragment",Toast.LENGTH_SHORT).show();

        ListView listView=(ListView)view.findViewById(R.id.contactList);
        listView.setAdapter(new MyAdaptor(view.getContext(),R.layout.my_adaptor_layout,dbHelper.getData()));
        sendDataToServer();
    }

    public void sendDataToServer()
    {
        try {
            JSONArray array = new JSONArray();
            for (int i = 0; i < 10; i++) {
               try {
                   JSONObject jsonObject = new JSONObject();
                   jsonObject.put("contactName", "abcd");
                   jsonObject.put("phone", i);
                   array.put(jsonObject);
               }catch (JSONException e)
               {

               }

            }

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userID","10");
            jsonObject.put("contactList",array);

            // String string=jsonObject.toString();

            // Toast.makeText(this, string, Toast.LENGTH_LONG).show();

            String url="http://belockchain.com/api/signin.php?user_name=umershah444&password=123456";
            JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject j1 = response.getJSONObject("user");
                        if(j1.equals(""))
                            Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), j1.getString("user_name"), Toast.LENGTH_LONG).show();
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                    error.printStackTrace();
                }
            });


            JsonObjectRequest jsonObjectRequest4 =new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
