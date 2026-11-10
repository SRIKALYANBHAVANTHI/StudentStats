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
            int totalStudents = Integer.parseInt(totalStudentsEditText.getText().toString().trim());
            int groupA = Integer.parseInt(groupAEditText.getText().toString().trim());
            int groupB = Integer.parseInt(groupBEditText.getText().toString().trim());
            int groupC = Integer.parseInt(groupCEditText.getText().toString().trim());
            int groupD = Integer.parseInt(groupDEditText.getText().toString().trim());
            int groupF = Integer.parseInt(groupFEditText.getText().toString().trim());

            if (totalStudents <= 0) {
                showErrorDialog("Total students must be greater than zero.");
                return;
            }

            if (groupA < 0 || groupB < 0 || groupC < 0 || groupD < 0 || groupF < 0) {
                showErrorDialog("Grade-group counts cannot be negative.");
                return;
            }

            long groupTotal = (long) groupA + groupB + groupC + groupD + groupF;
            if (totalStudents != groupTotal) {
                showErrorDialog("The sum of groups A, B, C, D, and F must equal the total number of students.");
                return;
            }

            double percentageA = (double) groupA / totalStudents * 100;
            double percentageB = (double) groupB / totalStudents * 100;
            double percentageC = (double) groupC / totalStudents * 100;
            double percentageD = (double) groupD / totalStudents * 100;
            double percentageF = (double) groupF / totalStudents * 100;

            String resultMessage = String.format(
                    java.util.Locale.getDefault(),
                    "Group A: %.2f%%\nGroup B: %.2f%%\nGroup C: %.2f%%\nGroup D: %.2f%%\nGroup F: %.2f%%",
                    percentageA, percentageB, percentageC, percentageD, percentageF
            );

            showResultDialog(resultMessage, new float[]{
                    (float) percentageA,
                    (float) percentageB,
                    (float) percentageC,
                    (float) percentageD,
                    (float) percentageF
            });

        } catch (NumberFormatException e) {
            showErrorDialog("Enter a valid whole number in every field.");
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
        builder.create().show();
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
        builder.create().show();
    }
}
