package com.boe.cn.progressbars;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.boe.cn.progressbars.waveview.WaveProgress;


public class Corrugated_BallProgress_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrugated__ball_progress_);
        final int blueDark = getResources().getColor(R.color.blue_dark);
        final int blueLight = getResources().getColor(R.color.blue_light);
        WaveProgress wave_health = findViewById(R.id.wave_health);
        initWaveProgress(wave_health,60f, blueDark, blueLight);
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
