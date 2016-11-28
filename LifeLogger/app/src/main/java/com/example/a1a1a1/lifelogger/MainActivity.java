package com.example.a1a1a1.lifelogger;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;


public class MainActivity extends Activity {



    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("취소하셨습니다. [설정] > [권한] 에서 권한을 다시 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();
        setContentView(R.layout.activity_main);


    }
    public void onClick_event(View v){
        Intent intent_event=new Intent(getApplicationContext(), EventActivity.class);
        startActivity(intent_event);
    }

    public void onClick_map(View v){
        Intent intent_map=new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent_map);
    }

    public void onClick_stats(View v){
        Intent intent_analysis=new Intent(getApplicationContext(), AnalysisActivity.class);
        startActivity(intent_analysis);
    }



}
