package com.example.studentstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText totalStudentsEditText, groupAEditText, groupBEditText, groupCEditText, groupDEditText, groupFEditText;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalStudentsEditText = findViewById(R.id.totalStudentsEditText);
        groupAEditText = findViewById(R.id.groupAEditText);
        groupBEditText = findViewById(R.id.groupBEditText);
        groupCEditText = findViewById(R.id.groupCEditText);
        groupDEditText = findViewById(R.id.groupDEditText);
        groupFEditText = findViewById(R.id.groupFEditText);

        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePercentages();
            }
        });
    }

    private void calculatePercentages() {
        try {
            int totalStudents = Integer.parseInt(totalStudentsEditText.getText().toString());
            int groupA = Integer.parseInt(groupAEditText.getText().toString());
            int groupB = Integer.parseInt(groupBEditText.getText().toString());
            int groupC = Integer.parseInt(groupCEditText.getText().toString());
            int groupD = Integer.parseInt(groupDEditText.getText().toString());
            int groupF = Integer.parseInt(groupFEditText.getText().toString());

            if(totalStudents!=(groupA+groupB+groupC+groupD+groupF))
            {
                Toast.makeText(this, "Incorrect total number of students", Toast.LENGTH_SHORT).show();
            }
            else {
                double percentageA = (double) groupA / totalStudents * 100;
                double percentageB = (double) groupB / totalStudents * 100;
                double percentageC = (double) groupC / totalStudents * 100;
                double percentageD = (double) groupD / totalStudents * 100;
                double percentageF = (double) groupF / totalStudents * 100;

                // Prepare result message
                String resultMessage = String.format("Group A: %.2f%%\nGroup B: %.2f%%\nGroup C: %.2f%%\nGroup D: %.2f%%\nGroup F: %.2f%%",
                        percentageA, percentageB, percentageC, percentageD, percentageF);

                // Show results in a dialog with option to display chart
                showResultDialog(resultMessage, new float[]{(float) percentageA, (float) percentageB, (float) percentageC, (float) percentageD, (float) percentageF});

            }

        } catch (NumberFormatException e) {

        }
    }

    private void showResultDialog(String message, final float[] percentages) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Plot bar Chart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, BarChartActivity.class);
                intent.putExtra("percentages", percentages);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

