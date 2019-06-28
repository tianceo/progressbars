package com.boe.cn.progressbars;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.boe.cn.progressbars.waveview.WaveProgress;


public class Corrugated_BallProgress_Activity extends AppCompatActivity {
private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrugated__ball_progress_);
        final int blueDark = getResources().getColor(R.color.blue_dark);
        final int blueLight = getResources().getColor(R.color.blue_light);
        final WaveProgress wave_health = findViewById(R.id.wave_health);
        final TextView tv_value = findViewById(R.id.tv_value);
        initWaveProgress(wave_health,69f, blueDark, blueLight);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_value.setText(wave_health.getValue()+":::::"+wave_health.getMaxValue());
            }
        },1000);

    }
    /**
     * @param wave_health
     * @param current
     * @param blueDark
     * @param blueLight
     */
    private void initWaveProgress(WaveProgress wave_health, float current, int blueDark, int blueLight) {
        wave_health.setWaveColor(blueDark, blueLight);
        wave_health.setMaxValue(100f);
        wave_health.setValue(current);
    }
}
