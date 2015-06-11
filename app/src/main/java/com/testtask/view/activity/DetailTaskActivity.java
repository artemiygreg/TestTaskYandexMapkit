package com.testtask.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testtask.R;
import com.testtask.Vars;
import com.testtask.model.Price;
import com.testtask.model.Task;
import com.testtask.service.DateService;

public class DetailTaskActivity extends AppCompatActivity {
    private TextView textViewText, textViewLongText, textViewDate, textViewLocation;
    private LinearLayout layoutPrices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_task_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        textViewText = (TextView) findViewById(R.id.textViewText);
        textViewLongText = (TextView) findViewById(R.id.textViewLongText);
        layoutPrices = (LinearLayout) findViewById(R.id.layoutPrices);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewLocation = (TextView) findViewById(R.id.textViewLocationText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Task task = getIntent().getParcelableExtra(Vars.Parcel.TASK_OBJECT);
        setTitle(task.getTitle());
        setData(task);
    }

    private void setData(Task task) {
        textViewText.setText(task.getText());
        textViewLongText.setText(task.getLongText());
        for(Price price : task.getPrices()){
            layoutPrices.addView(makeTextViewPrice(Integer.toString( price.getPrice())));
        }
        textViewDate.setText(DateService.convertTimestampToString(task.getDate()));
        textViewLocation.setText(task.getLocationText());
    }
    private TextView makeTextViewPrice(String textPrice){
        TextView textView = new TextView(this);
        textView.setText(textPrice);
        textView.setTextSize(14);
        textView.setPadding(5, 5, 5, 5);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(getResources().getColor(R.color.appColor));
        textView.setLayoutParams(params());

        return textView;
    }
    private LinearLayout.LayoutParams params(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(15, 5, 5, 5);

        return params;
    }
}
