package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.xxmassdeveloper.mpchartexample.custom.DayAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.MyAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.XYMarkerView;
import java.util.ArrayList;
import java.util.List;

public class ShapeBarChartActivity extends FragmentActivity {
  BarChart mChart;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_barchart_shape);
    mChart = findViewById(R.id.barChart);
    init();
  }

  private void init() {
    mChart.setDrawBarShadow(true);
    mChart.setDrawValueAboveBar(true);
    mChart.getDescription().setEnabled(false);

    // if more than 60 entries are displayed in the chart, no values will be
    // drawn
    mChart.setMaxVisibleValueCount(60);

    // scaling can now only be done on x- and y-axis separately
    mChart.setPinchZoom(false);
    mChart.setDoubleTapToZoomEnabled(false);
    mChart.setScaleEnabled(false);
    mChart.setBackgroundColor(Color.WHITE);

    mChart.setDrawGridBackground(false);
    // mChart.setDrawYLabels(false);

    IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

    XAxis xAxis = mChart.getXAxis();
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setTextSize(16f);
    xAxis.setDrawGridLines(false);
    xAxis.setAxisLineColor(Color.WHITE);
    xAxis.setGranularity(1f); // only intervals of 1 day
    xAxis.setLabelCount(7);
    xAxis.setValueFormatter(xAxisFormatter);

    IAxisValueFormatter custom = new MyAxisValueFormatter();

    YAxis leftAxis = mChart.getAxisLeft();
    leftAxis.setLabelCount(7, false);
    leftAxis.setTextSize(16f);
    leftAxis.setAxisLineColor(Color.TRANSPARENT);
    leftAxis.setGridColor(0xfff3f3f3);
    leftAxis.enableGridDashedLine(20f, 20f, 0);
    leftAxis.setGridLineWidth(1f);
    leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
    leftAxis.setSpaceTop(15f);
    leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
    leftAxis.setSpaceBottom(0f);

    mChart.getAxisRight().setEnabled(false);


    Legend l = mChart.getLegend();
    //l.setEnabled(false);
    l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
    l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    l.setDrawInside(false);
    l.setForm(Legend.LegendForm.NONE);
    l.setFormSize(9f);
    l.setTextSize(11f);
    l.setXEntrySpace(4f);

    XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
    mv.setChartView(mChart); // For bounds control
    mChart.setMarker(mv); // Set the marker to the chart

    setData(6, 300);
  }

  private void setData(int count, float range) {

    float start = 1f;

    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

    for (int i = (int) start; i < start + count + 1; i++) {
      float mult = (range + 1);
      float val = (float) (Math.random() * mult + 300);
      yVals1.add(new BarEntry(i, val));
    }

    BarDataSet set1;

    if (mChart.getData() != null &&
        mChart.getData().getDataSetCount() > 0) {
      set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
      set1.setValues(yVals1);
      mChart.getData().notifyDataChanged();
      mChart.notifyDataSetChanged();
    } else {
      set1 = new BarDataSet(yVals1, "");

      set1.setDrawIcons(false);


      int startColor1 = 0xffffb868;
      //int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
      int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
      int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
      int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
      int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
      int endColor1 = 0xffff6d6c;
      //int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
      int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
      int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
      int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
      int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

      List<GradientColor> gradientColors = new ArrayList<>();
      gradientColors.add(new GradientColor(startColor1, endColor1));
      gradientColors.add(new GradientColor(startColor2, endColor2));
      gradientColors.add(new GradientColor(startColor3, endColor3));
      gradientColors.add(new GradientColor(startColor4, endColor4));
      gradientColors.add(new GradientColor(startColor5, endColor5));

      set1.setGradientColors(gradientColors);
      set1.setBarShadowColor(0xfff8f8f8);
      set1.setShadowShape(IBarDataSet.Shape.ROUND);
      set1.setBarShape(IBarDataSet.Shape.ROUND);
      set1.setHighLightColor(Color.TRANSPARENT);
      set1.setHighLightAlpha(0);
      set1.setDrawValues(false);

      ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
      dataSets.add(set1);

      BarData data = new BarData(dataSets);
      data.setValueTextSize(10f);
      data.setBarWidth(0.2f);

      mChart.setData(data);
    }
  }
}
