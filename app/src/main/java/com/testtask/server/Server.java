package com.testtask.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.testtask.Vars;
import com.testtask.interfaces.JsonResponse;
import com.testtask.json.Json;
import com.testtask.model.Task;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Artem on 10.06.2015.
 */
public class Server {
    private RequestQueue requestQueue;
    private Context context;

    public Server(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public void getTasks(final JsonResponse jsonResponse){
        String url = Vars.Server.PROTOCOL_HTTP + Vars.Server.HOST + ":" + Vars.Server.PORT + Vars.Server.GET_TASKS;
        jsonResponse.onRequestStart();
        JsonObjectRequest request = new JsonObjectRequest(Vars.Server.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("onResponse", jsonObject.toString());
                    List<Task> list = Json.makeTaskFromJson(jsonObject);
                    jsonResponse.onSuccess(jsonObject, list);
                } catch (Exception e) {
                    e.printStackTrace();
                    jsonResponse.onError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("onResponse", volleyError.getMessage());
                jsonResponse.onError(volleyError.getMessage());
            }
        });
        requestQueue.add(request);
    }
}
