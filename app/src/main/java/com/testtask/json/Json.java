package com.testtask.json;

import android.util.Log;

import com.google.gson.Gson;
import com.testtask.Vars;
import com.testtask.model.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 10.06.2015.
 */
public class Json {

    public Json() {

    }
    public static List<Task> makeTaskFromJson(JSONObject json){
        List<Task> list = new ArrayList<Task>();
        Gson gson = new Gson();
        try {
            for(int i = 0; i < json.getJSONArray(Vars.Json.TASKS).length(); i++){
                Task task = gson.fromJson(json.getJSONArray(Vars.Json.TASKS).getJSONObject(i).toString(), Task.class);
                list.add(task);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONException", e.getMessage());
        }
        return list;
    }
}
