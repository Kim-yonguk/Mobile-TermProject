package com.example.a1a1a1.lifelogger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

import static android.R.attr.name;

/**
 * Created by 1a1a1 on 2016-12-16.
 */

public class GoalActivity extends Activity {

    int cnt1 = 0;
    int cnt2 = 0;
    int cnt3 = 0;


    int count1=0;
    int count2=0;
    int count3=0;

    TextView textView1;

    TextView textView2;

    TextView textView3;

    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.goal_main);


        textView1 = (TextView) findViewById(R.id.Goal1_count);
        textView2 = (TextView) findViewById(R.id.Goal2_count);
        textView3 = (TextView) findViewById(R.id.Goal3_count);
        textView4 = (TextView) findViewById(R.id.Count_result);

        Button btnGoal1 = (Button) findViewById(R.id.btn_goal1);
        Button btnGoal2 = (Button) findViewById(R.id.btn_goal2);
        Button btnGoal3 = (Button) findViewById(R.id.btn_goal3);
        Button btnSave = (Button) findViewById(R.id.btn_save);
        Button btnResult = (Button) findViewById(R.id.btn_result);

        final DBManager dbManager = new DBManager(getApplicationContext(), "Counttt.db", null, 1);

        btnGoal1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cnt1++;
                textView1.setText("" + cnt1);

            }
        });
        btnGoal2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cnt2++;
                textView2.setText("" + cnt2);

            }
        });
        btnGoal3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cnt3++;
                textView3.setText("" + cnt3);

            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView4.setText(dbManager.PrintCount());
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                count1 += cnt3;
                count2 += cnt2;
                count3 += cnt3;


                dbManager.countinsert("insert into COUNT_LIST values('" + count1 + "', '" + count2 + "', '" + count3 + "');");

                cnt1=0;
                cnt2=0;
                cnt3=0;

            }

        });


    }
}




