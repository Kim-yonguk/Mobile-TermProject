package com.example.a1a1a1.lifelogger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public class AnalysisActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_main);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.text_title);
        final EditText etPrice = (EditText) findViewById(R.id.text_content);

        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.Doing_result);

        // Update
        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // update 테이블명 where 조건 set 값;
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.update("update FOOD_LIST set price = '" + price + "' where name = '" + name + "';");


                tvResult.setText( dbManager.PrintData() );
            }
        });




        // Delete
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // delete from 테이블명 where 조건;
                String name = etName.getText().toString();
                dbManager.delete("delete from FOOD_LIST where name = '" + name + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });

        // Select
        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tvResult.setText( dbManager.PrintData() );
            }
        });
    }

    public void backaway(View v){
        finish();
    }


}