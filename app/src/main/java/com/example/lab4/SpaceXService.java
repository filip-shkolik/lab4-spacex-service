package com.example.lab4;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SpaceXService {
    public SpaceXService(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    private RequestQueue queue;
    private String url = "https://api.spacexdata.com/v3/ships";

    void collectWeather(Response.Listener<java.lang.String> listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        queue.add(stringRequest);
    }
}
