package com.example.a1a1a1.lifelogger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public class EventActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_main);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.text_title);
        final EditText etPrice = (EditText) findViewById(R.id.text_content);
        final Calendar cal=Calendar.getInstance();



        // Insert
        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();

                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DATE);

                dbManager.insert("insert into FOOD_LIST values(null,null,null,'" + year + "', '" + month +"', '" + day + "',  '" + name + "', '" + price + "');");

            }
        });
    }




    public void onClick_back(View v){
        finish();
    }
}
