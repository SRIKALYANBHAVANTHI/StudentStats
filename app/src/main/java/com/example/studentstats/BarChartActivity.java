package com.example.studentstats;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        float[] percentages = getIntent().getFloatArrayExtra("percentages");

        BarChart barChart = findViewById(R.id.bar_chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < Objects.requireNonNull(percentages).length; i++) {
            entries.add(new BarEntry(i, percentages[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Percentage");
        dataSet.setColors(getBarColors());

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Set group names for X-axis
        final String[] groupNames = {"A", "B", "C", "D", "F"};
        IndexAxisValueFormatter xAxisFormatter = new IndexAxisValueFormatter(groupNames);
        barChart.getXAxis().setValueFormatter(xAxisFormatter);
        barChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);

        };

        // Set value formatter for Y-axis

        .invalidate(); // Refresh chart
    }

    private int[] getBarColors() {
        return new int[]{
                getResources().getColor(R.color.group_a_color),
                getResources().getColor(R.color.group_b_color),
                getResources().getColor(R.color.group_c_color),
                getResources().getColor(R.color.group_d_color),
                getResources().getColor(R.color.group_f_color)
        };
    }
}
