package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Date_picker_2_Activity extends AppCompatActivity {

    TextView textViewDate;
    DatePicker datePicker;
    Calendar calendar;
    String date;
    Intent intent, intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_datepicker_layout);
        intent  = new Intent(this, CalculationActivity.class);
        intent2 = getIntent();
        textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText("Check Out Date");
        datePicker = findViewById(R.id.datePicker);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date = dayOfMonth+"/"+(month+1)+"/"+year;
                intent.putExtra("checkOutDate",date);
                intent.putExtra("checkInDate",intent2.getStringExtra("checkInDate"));

                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Date "+date,Toast.LENGTH_LONG).show();
                finish();

            }
        });


    }
}
