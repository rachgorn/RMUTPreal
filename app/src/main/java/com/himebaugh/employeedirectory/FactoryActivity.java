package com.himebaugh.employeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FactoryActivity extends Activity {

    private Button Button1;
    private Button Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        Button1 = (Button) findViewById(R.id.ButtonFac1);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO �������1
                Intent myIntent = new Intent(FactoryActivity.this,LoadfacMainActivity.class);
                startActivity(myIntent);

            }

        });


    }

}



