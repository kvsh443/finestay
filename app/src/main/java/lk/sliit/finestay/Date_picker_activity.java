package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static lk.sliit.finestay.R.layout.check_datepicker_layout;

public class Date_picker_activity extends AppCompatActivity {

    TextView textViewDate;
    DatePicker datePicker;
    Calendar calendar;
    String date, guests, rooms;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_datepicker_layout);
        intent = new Intent(this, Date_picker_2_Activity.class);
        textViewDate = findViewById(R.id.textViewDate);
        datePicker = findViewById(R.id.datePicker);
        textViewDate.setText("Check In Date");
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        try {
            if (!(intent.getStringExtra("guests")).isEmpty()) {
                guests = (intent.getStringExtra("guests"));

            } else {
                guests = String.valueOf(1);
            }
        } catch (Exception e) {
            guests = String.valueOf(1);
        }

        try {
            if (!(intent.getStringExtra("rooms").isEmpty())) {
                rooms = intent.getStringExtra("rooms");
            }
        } catch (Exception e) {
            rooms = String.valueOf(1);
        }

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month + 1) + "/" + year;
                intent.putExtra("checkInDate", date);
                intent.putExtra("guests",guests);
                intent.putExtra("rooms",rooms);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Date " + date, Toast.LENGTH_LONG).show();
                finish();

            }
        });


    }
}
