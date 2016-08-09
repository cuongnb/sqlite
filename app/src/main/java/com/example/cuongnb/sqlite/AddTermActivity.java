package com.example.cuongnb.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cuongnb on 8/9/16.
 */
public class AddTermActivity extends Activity {
    Database database;
    EditText etTerm;
    EditText etDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_term_layout);

        database = new Database(AddTermActivity.this);
        etTerm = (EditText) findViewById(R.id.etTerm);
        etDef = (EditText) findViewById(R.id.etDef);
        Button btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Term term = new Term(etTerm.getText().toString(), etDef.getText().toString());
                database.insetVocab(term);

                startActivity(new Intent(AddTermActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
