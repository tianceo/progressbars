package com.boe.cn.progressbars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListiner();
    }

    private void initListiner() {
       Button btn_gradient_ringprogress=findViewById(R.id.btn_gradient_ringprogress);
        btn_gradient_ringprogress.setOnClickListener(this);
       Button btn_gradient_horizontalprogress=findViewById(R.id.btn_gradient_horizontalprogress);
        btn_gradient_horizontalprogress.setOnClickListener(this);
       Button btn_Corrugated_ball_progress=findViewById(R.id.btn_Corrugated_ball_progress);
        btn_Corrugated_ball_progress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_gradient_ringprogress:
               startActivity(new Intent(MainActivity.this,Gradient_RingProgress_Activity.class));
                break;

            case R.id.btn_Corrugated_ball_progress:
                startActivity(new Intent(MainActivity.this,Gradient_horizontalProgress_Activity.class));
                break;
            case R.id.btn_gradient_horizontalprogress:
                startActivity(new Intent(MainActivity.this,Corrugated_BallProgress_Activity.class));
                break;
        }
    }
}
