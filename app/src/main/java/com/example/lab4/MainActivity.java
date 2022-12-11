package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SpaceXService service;
    ListView listView;
    JSONArray data;


    private void displayInfo(String response) throws JSONException {
        ArrayList<String> list = new ArrayList<String>();
        data = new JSONArray(response);

        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = new JSONObject(data.get(i).toString());
            String ship = obj.getString("ship_name");
            list.add(ship);
        }

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent i = new Intent(MainActivity.this, DetailView.class);
                    i.putExtra(DetailView.dataKey, data.get(position).toString());
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);

        service = new SpaceXService(this);
        service.collectWeather(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    displayInfo(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // Handle error
        });
    }
}