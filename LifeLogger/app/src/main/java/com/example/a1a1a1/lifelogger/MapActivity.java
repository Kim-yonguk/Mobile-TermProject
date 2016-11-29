package com.example.a1a1a1.lifelogger;

import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;
import java.lang.*;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public class MapActivity extends FragmentActivity {

    GoogleMap gmap;
    MarkerOptions marker;

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MapActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MapActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };
        LatLng location = new LatLng(37.566535, 126.977969);
        CameraPosition cp = new CameraPosition.Builder().target(location).zoom(10).build();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            new TedPermission(this)

                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                    .setDeniedMessage("취소하셨습니다. [설정] > [권한] 에서 권한을 다시 허용할 수 있어요.")
                    .setPermissions(Manifest.permission.READ_CONTACTS)
                    .check();
            setContentView(R.layout.map_main);



            final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);


            gmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

        }

        public void backaway(View v) {
            finish();
        }

    }
