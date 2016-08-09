package com.example.cuongnb.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAddTerm;
    TextView tvListTerm;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(MainActivity.this);

        btnAddTerm = (Button) findViewById(R.id.btnAddTerm);
        tvListTerm = (TextView) findViewById(R.id.tvListTerm);

        btnAddTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTermActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Term> terms = database.getListTerm();
        String s = " ";
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            s += term.term + " -- " + term.def + "\n";
        }

//        ArrayList<String> strings = new ArrayList<String>();
//        strings.add("ssss");
//        strings.add("cuongnb");
//        String s = "";
//        for (int i = 0; i < strings.size(); i++) {
//            s += strings.get(i) + " \n";
//        }

        tvListTerm.setText(s);


    }
}
