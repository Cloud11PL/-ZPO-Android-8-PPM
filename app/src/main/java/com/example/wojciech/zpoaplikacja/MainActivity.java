package com.example.wojciech.zpoaplikacja;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView calculatedResultsText;
    private EditText giveWeight;
    private EditText giveHeight;
    private Spinner giveSex;
    private Spinner givePPMType;
    private Button calculateButton;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachData();
    }

    private void attachData() {
        calculatedResultsText = findViewById(R.id.calculated_results_text);
        giveHeight = findViewById(R.id.heightEdit);
        giveWeight = findViewById(R.id.weightEdit);
        giveSex = findViewById(R.id.sex_spinner);
        givePPMType = findViewById(R.id.PPM_spinner);
        calculateButton = findViewById(R.id.calculate_button);
    }

    @SuppressLint("DefaultLocale")
    public void calculateData(View view) {
        PPM ppm = new PPM();
        String methodType = (String) givePPMType.getSelectedItem();
        try {
            double height = Double.valueOf(giveHeight.getText().toString());
            double weight = Double.valueOf(giveWeight.getText().toString());
            int sex = (giveSex.getSelectedItem().toString().equals("Male")) ? 0 : 1;
            //Male = 0
            //Female = 1
            double result;
            if (methodType.equals("Harris-Benedict Method")) {
                result = ppm.harrisBenedictMethod(sex, weight, height, getAge());
            } else {
                result = ppm.mifflinMethod(sex, weight, height, getAge());
            }
            calculatedResultsText.setText(String.format("%.2f",result));
        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Make sure your input is correct.", Toast.LENGTH_LONG).show();
        }
    }

    public void choose_birth_date(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setAge(calculateUserAge(cal));
    }

    public int calculateUserAge(Calendar cal) {
        Calendar birthDate = cal;
        Calendar currentDate = Calendar.getInstance();

        int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (birthDate.get(Calendar.DAY_OF_YEAR) > currentDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if (age == 0 && birthDate.get(Calendar.DAY_OF_YEAR) > currentDate.get(Calendar.DAY_OF_YEAR)) {
            age = -1;
        }
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            Toast.makeText(MainActivity.this, "Wrong date of birth! You can choose again.", Toast.LENGTH_LONG).show();
        }
    }

    public int getAge() {
        return age;
    }

}
