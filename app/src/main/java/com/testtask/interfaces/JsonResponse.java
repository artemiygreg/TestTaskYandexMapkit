package com.testtask.interfaces;

import com.testtask.model.Task;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Artem on 10.06.2015.
 */
public interface JsonResponse {
    void onRequestStart();
    void onSuccess(JSONObject jsonObject, List<Task> listTask);
    void onError(String error);
}
