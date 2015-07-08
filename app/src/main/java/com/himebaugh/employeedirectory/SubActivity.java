package com.himebaugh.employeedirectory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class SubActivity extends Activity {
    private Button Button1;
    ImageView imgLogo;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button1 = (Button) findViewById(R.id.ButtonSub1);
        Button btnToggleScale = (Button)findViewById(R.id.ButtonSub1);
        btnToggleScale.setOnClickListener(new View.OnClickListener() {
            float fromScale = 1, toScale = (float)1.3;
            @Override
            public void onClick(View view) {
                // TODO ???????1
                anim = new ScaleAnimation(fromScale
                        , toScale, fromScale, toScale
                        , Button1.getWidth() / 2
                        , Button1.getHeight() / 2);
                anim.setDuration(500);
                Button1.startAnimation(anim);
                float tempScale = fromScale;
                fromScale = toScale;
                toScale = tempScale;

                Intent myIntent = new Intent(SubActivity.this, FactoryActivity.class);
                startActivity(myIntent);
            }

        });

    }
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.show();
    }
}
