package com.boe.cn.progressbars;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.boe.cn.progressbars.waveview.CircleStatisticsView;

/**
 *
 */
public class RingStatics_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics__ring_progress);
        CircleStatisticsView circleStatisticsView=findViewById(R.id.crlview);
        circleStatisticsView.initSrc(new float[]{20f,30f,40f}, new String[]{"#ff80FF",
                "#ffFF00", "#6A5ACD"},new CircleStatisticsView.OnItemClickListener() {

            @Override
            public void click(int position) {
                // TODO Auto-generated method stub

            }
        });
        circleStatisticsView.setItem(new float[]{23,36,156});
        circleStatisticsView.setColors(new String[]{"#ff80FF",
				"#ffFF00", "#6A5ACD"});
        circleStatisticsView.notifyDraw();

    }
}
