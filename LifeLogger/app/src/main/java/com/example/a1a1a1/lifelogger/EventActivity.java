package com.example.a1a1a1.lifelogger;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public class EventActivity extends Activity {
    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(EventActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(EventActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };
    GoogleMap gmap;
    MarkerOptions marker;
    public GetGps GPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TedPermission(this)

                .setPermissionListener(permissionlistener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("취소하셨습니다. [설정] > [권한] 에서 권한을 다시 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();
        setContentView(R.layout.event_main);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);


        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.text_title);
        final EditText etPrice = (EditText) findViewById(R.id.text_content);
        final Calendar cal=Calendar.getInstance();

        Button btncamera = (Button) findViewById(R.id.btn_camera);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });


        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();

                GPS = new GetGps(EventActivity.this);

                GPS.getLocation();
                double lat = GPS.getLatitude();
                double lon = GPS.getLongitude();



                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DATE);
                int hour=cal.get(Calendar.HOUR_OF_DAY);
                int minute=cal.get(Calendar.MINUTE);
                int second=cal.get(Calendar.SECOND);


                dbManager.insert("insert into FOOD_LIST values(null,'" + lat + "','" + lon + "','" + year + "', '" + month +"', '" + day + "',  '" + name + "', '" + price + "');");

            }
        });
    }




    public void onClick_back(View v){
        finish();
    }
}
