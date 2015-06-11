package com.testtask.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.testtask.R;
import com.testtask.Vars;
import com.testtask.interfaces.JsonResponse;
import com.testtask.model.Task;
import com.testtask.server.Server;

import org.json.JSONObject;

import java.util.List;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.OnBalloonListener;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class MainActivity extends AppCompatActivity {
    private OverlayManager mOverlayManager;
    private Server server;
    private ProgressDialog progressDialog;
    private Overlay overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.processingRequest));

        MapView mapView = (MapView) findViewById(R.id.map);
        MapController mMapController = mapView.getMapController();
        mOverlayManager = mMapController.getOverlayManager();
        mMapController.setZoomCurrent(1);
        overlay = new Overlay(mMapController);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        server = new Server(this);
        getTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.actionUpdate) {
            overlay.clearOverlayItems();
            getTasks();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickUpdate(View view){
        overlay.clearOverlayItems();
        getTasks();
    }
    private void onDrawOverlayItems(List<Task> listTask){
        for (final Task task : listTask) {
            OverlayItem overlayItem = new OverlayItem(new GeoPoint(task.getLocation().getLat(),
                    task.getLocation().getLon()), getResources().getDrawable(R.drawable.ic_marker_blue));
            BalloonItem balloon = new BalloonItem(this, new GeoPoint(task.getLocation().getLat(), task.getLocation().getLon()));

            balloon.setText(task.getTitle());
            balloon.setOnBalloonListener(new OnBalloonListener() {
                @Override
                public void onBalloonViewClick(BalloonItem balloonItem, View view) {
                    Log.e("onBalloonViewClick", "click");
                    goToDetailDescription(task);
                }

                @Override
                public void onBalloonShow(BalloonItem balloonItem) {

                }

                @Override
                public void onBalloonHide(BalloonItem balloonItem) {

                }

                @Override
                public void onBalloonAnimationStart(BalloonItem balloonItem) {

                }

                @Override
                public void onBalloonAnimationEnd(BalloonItem balloonItem) {

                }
            });
            overlayItem.setBalloonItem(balloon);
            overlay.addOverlayItem(overlayItem);
            mOverlayManager.addOverlay(overlay);
        }
    }

    private void goToDetailDescription(Task task){
        Intent detailIntent = new Intent(this, DetailTaskActivity.class);
        detailIntent.putExtra(Vars.Parcel.TASK_OBJECT, task);
        startActivity(detailIntent);
    }
    private void getTasks(){
        server.getTasks(new JsonResponse() {
            @Override
            public void onRequestStart() {
                showProgressDialog();
            }

            @Override
            public void onSuccess(JSONObject jsonObject, List<Task> listTask) {
                onDrawOverlayItems(listTask);
                dismissProgressDialog();
            }

            @Override
            public void onError(String error) {
                dismissProgressDialog();
                if(error != null && !TextUtils.isEmpty(error)){
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void showProgressDialog(){
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    protected void dismissProgressDialog(){
        if(progressDialogIsShowing()){
            progressDialog.dismiss();
        }
    }
    protected boolean progressDialogIsShowing(){
        return progressDialog != null && progressDialog.isShowing();
    }

}
