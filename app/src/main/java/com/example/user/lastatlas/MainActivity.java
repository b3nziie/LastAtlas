package com.example.user.lastatlas;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.resources.IAFloorPlan;
import com.indooratlas.android.sdk.resources.IALatLng;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    IALocationManager mLocationManager;
    IAFloorPlan mFloorPlan;

    IALocationListener mLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            TextView textViewLocation = (TextView) findViewById(R.id.textViewLocation);
            //zoomin TouchImageView imageIndoor = (TouchImageView) findViewById(R.id.imageView2);
            ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
            ImageView imageLocation = (ImageView)findViewById(R.id.imageLocation);
            DecimalFormat df = new DecimalFormat("#.000000");
            Float xla = Float.parseFloat(String.valueOf(df.format(iaLocation.getLatitude()).substring(3,9)));
            Float ylo = Float.parseFloat(String.valueOf(df.format(iaLocation.getLongitude()).substring(4,10)));
            textViewLocation.setText(String.valueOf(iaLocation.getLatitude()+","+iaLocation.getLongitude())+"="+xla+","+ylo   );
            imageLocation.setX(((xla-785679)*20)+530);
            imageLocation.setY(((ylo-698563)*20)+900);

        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

    };
    public void onClickButton(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.lab_flask);
        builder.setTitle("How to anything !!");
        builder.setMessage("first?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Edit",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Edit done.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void onClickButton2(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.labtube);
        builder.setTitle("How to what !!");
        builder.setMessage("second?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Edit",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Edit done.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationManager = IALocationManager.create(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(),mLocationListener);

    }

    @Override
    protected void onPause() {

        mLocationManager.removeLocationUpdates(mLocationListener);
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mLocationManager.destroy();
        super.onDestroy();

    }
}
