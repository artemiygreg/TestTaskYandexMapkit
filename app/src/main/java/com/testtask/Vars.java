package com.testtask;

import com.android.volley.Request;

/**
 * Created by Artem on 10.06.2015.
 */
public class Vars {

    public static class Server {
       public static final int GET = Request.Method.GET;
       public static final String PROTOCOL_HTTP = "http://";
       public static final String HOST = "test.boloid.com";
       public static final int PORT = 9000;
       public static final String GET_TASKS = "/tasks";
   }
    public static class Json {
       public static final String TASKS = "tasks";
       public static final String ID = "ID";
       public static final String TITLE = "title";
       public static final String DATE = "date";
       public static final String TEXT = "text";
       public static final String LONG_TEXT = "longText";
       public static final String DURATION_LIMIT_TEXT = "durationLimitText";
       public static final String PRICE = "price";
       public static final String PRICES = "prices";
       public static final String LOCATION_TEXT = "locationText";
       public static final String LOCATION = "location";
       public static final String LATITUDE = "lat";
       public static final String LONGTITUDE = "lon";
       public static final String ZOOM_LEVEL = "zoomLevel";
       public static final String TRANSLATION = "translation";
       public static final String DESCRIPTION = "description";
   }
   public static class Parcel {
      public static final String TASK_OBJECT = "task_object";
   }
}
