package com.boe.cn.progressbars.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * created by  Tigers
 * at 2019/6/26
 * 圆环统计图
 * [modifiedTime]
 */
public class CircleStatisticsView  extends View {
    private float[] item;// 每一项的值
    private float total; // 总共的值
    private String[] colors; // 传过来的颜色
    private float[] itemsAngle;// 每一项所占的角度
    private float[] itemsBeginAngle;// 每一项的起始角度
    private float[] itemsRate;// 每一块占的比例
    private float radius = 140;// 半径
    private DecimalFormat fnum;// 格式化float
    private OnItemClickListener listener;
    private int downX,downY,tempX,tempY;
    private static final String[] DEFAULT_ITEMS_COLORS = { "#ff80FF",
            "#ffFF00", "#6A5ACD", "#20B2AA", "#00BFFF", "#CD5C5C", "#8B658B",
            "#CD853F", "#006400", "#FF4500", "#D8BFD8", "#4876FF", "#FF00FF",
            "#FF83FA", "#0000FF", "#363636", "#FFDAB9", "#90EE90", "#8B008B",
            "#00BFFF", "#00FF00", "#006400", "#00FFFF", "#668B8B", "#000080",
            "#008B8B" };//默认图片


    public CircleStatisticsView(Context context) {
        super(context);
        fnum = new DecimalFormat("##0.00");
    }

    public CircleStatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        fnum = new DecimalFormat("##0.00");
    }

    public CircleStatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fnum = new DecimalFormat("##0.00");
    }

    /**
     * 设置半径
     *
     * @return
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * 设置每一项的值
     *
     * @return
     */
    public void setItem(float[] item) {
        this.item = item;
        if (item != null && item.length > 0) {
            jsTotal();
            refreshItemsAngs();
            colors=DEFAULT_ITEMS_COLORS;
        }
    }

    /**
     *
     * 初始化所有
     *
     * @param item
     * @param colors
     * @param listener 事件未处理
     */
    public void initSrc(float[] item,String[] colors,OnItemClickListener listener){
        setItem(item);
        setColors(colors);
        notifyDraw();
        this.listener = listener;
    }

    /**
     * 设置每一项的颜色
     *
     * @param colors
     */
    public void setColors(String[] colors) {
        if(colors!=null&&colors.length>0){
            this.colors = colors;}
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float letftop = 0;
        float rightbottom = 2 * radius;
        float centerXY = radius;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (item == null || item.length == 0) {// 如果什么数据都没有
            paint.setTextSize(radius / 4);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.parseColor("#000000"));
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
            float offY = fontTotalHeight / 2 - fontMetrics.bottom;
            float newY = centerXY + offY;
            canvas.drawText("暂无", centerXY, newY, paint);
        } else {
            // 画扇形
            RectF oval = new RectF(letftop, letftop, rightbottom, rightbottom);
            for (int i = 0; i < item.length; i++) {
                paint.setColor(Color.parseColor(colors[i]));
                canvas.drawArc(oval, itemsBeginAngle[i], itemsAngle[i], true,
                        paint);
            }
            // 画内圆
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#ffffff"));
            canvas.drawCircle(centerXY, centerXY, radius*6 /7, paint);
            // 画字
            paint.setTextSize(radius / 4);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.parseColor("#000000"));
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
            float offY = fontTotalHeight / 2 - fontMetrics.bottom;
            float newY = centerXY + offY;
            canvas.drawText(fnum.format(total) + "", centerXY, newY, paint);
        }
    }

    /**
     * 计算总数
     */
    private void jsTotal() {
        total = 0;
        for (float i : item) {
            total += i;
        }
    }

    /**
     * 根据每个item的大小，获得item所占的角度和起始角度
     */
    private void refreshItemsAngs() {
        if (item != null && item.length > 0) {
            itemsRate = new float[item.length];// 每一项所占的比例
            itemsBeginAngle = new float[item.length];// 每一个角度临界点
            itemsAngle = new float[item.length];// 每一个角度临界点
            float beginAngle = 0;
            for (int i = 0; i < item.length; i++) {
                itemsRate[i] = (float) (item[i] * 1.0 / total * 1.0);
            }
            for (int i = 0; i < itemsRate.length; i++) {
                if (i == 1) {
                    beginAngle = 360 * itemsRate[i - 1];
                } else if (i > 1) {
                    beginAngle = 360 * itemsRate[i - 1] + beginAngle;
                }
                itemsBeginAngle[i] = beginAngle;
                itemsAngle[i] = 360 * itemsRate[i];
            }

        }

    }

    /**
     * 通知开始绘画
     */
    public void notifyDraw() {
        invalidate();
    }

    /**
     * 控件可获得的空间
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float widthHeight = 2 * (radius);
        setMeasuredDimension((int) widthHeight, (int) widthHeight);
    }


    public interface OnItemClickListener{
        void click(int position);
    }
}