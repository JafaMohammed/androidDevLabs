package com.mojaafar.registrationpage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

//1.implement an interface which allows us to handle on click events on our calendar picker dialog
//7. implement interface (AdapterView.OnItemSelectedListener)
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //2 declare an edittext variable that is the focus on the calendar dialog

    public EditText birthday;
    private int mYear;
    private int mMonth;
    private int mDay;

    private String mSpinnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //3 .connect the edit text variable you created with the one with the one specified in the layout for receiving the date value
        birthday = findViewById(R.id.birthday);
        //4. connect the edit text variable with an onclick listener
        birthday.setOnClickListener(this);
        //7.1 declare spinner variable and connect it to the spinner view in the layout
        Spinner phoneSpinner = findViewById(R.id.phone_spinner);

        //7.2 set an OnItemSelectedListener on the spinner object/variable created
        if (phoneSpinner!=null){
            phoneSpinner.setOnItemSelectedListener(this);
        }

        // 7.3 create an array adapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this , R.array.spinner_label, android.R.layout.simple_spinner_item);
        //specify the layout from the dropdown menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attach spinner to the adapter
        if (phoneSpinner!=null){
            phoneSpinner.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v) {
//5..get instance of current date
        //6. ensure focus is on the edit variable birthday

        if (v==birthday){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
                    //6.1 set date on edit text variable
                    birthday.setText(dayOfMonth + "-" +(month+1+"-"+year));
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //declare variable for holding the selected item on the spinner
        //use method getItemAtPosition() to get label selected

        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        //something to do  with item selected
        Toast myToast = Toast.makeText(this, "Selected phone as:" +mSpinnerLabel, Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast toast = Toast.makeText(this, "Nothing selected",  Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(View view) {
        Toast check = Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT );
        check.show();

    }

    public void createAccount(View view) {
        //compare passwords
        //hrow error exceptions
        //get data entred on edit text and save to db
        //add an intent and open main activity/login activity
        //throw a toast
        Toast toastSubmit = Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT);
        toastSubmit.show();
    }
}
