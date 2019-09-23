package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CalculationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btncheckOut, btncheckIn,btnReserve;
    EditText editTextGuestNo;
    Intent intentDate, intentSendPayload;
    SimpleDateFormat simpleDateFormat;
    Date in,out = null;
    Calendar c1,c2;
    CheckBox checkBox;
    Activity activity ;
    Spinner spinnerPackages;
    Double packageValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation_layout);
        activity = this;
        checkBox = findViewById(R.id.checkBox);
        btnReserve = findViewById(R.id.btnReserve);
        btncheckOut = findViewById(R.id.btncheckOut);
        btncheckIn = findViewById(R.id.btncheckIn);
        editTextGuestNo = findViewById(R.id.editTextGuestNo);
        spinnerPackages =  findViewById(R.id.spinnerPackages);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.package_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPackages.setAdapter(adapter);
        spinnerPackages.setOnItemSelectedListener(this);
        intentDate = getIntent();
        intentSendPayload = new Intent(this,conform_Reservation.class);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        c1= Calendar.getInstance();
        c2= Calendar.getInstance();

        try {


            if (!(intentDate.getStringExtra("checkInDate")).isEmpty()) {
                btncheckIn.setText(intentDate.getStringExtra("checkInDate"));
                intentSendPayload.putExtra("inDate",btncheckIn.getText().toString());
            }
        }catch (Exception e){}

        try {

            if (!(intentDate.getStringExtra("checkOutDate")).isEmpty()) {
                btncheckOut.setText(intentDate.getStringExtra("checkOutDate"));
                intentSendPayload.putExtra("outDate",btncheckOut.getText().toString());
            }
        }catch (Exception e){}


        btnReserve.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(editTextGuestNo.toString().isEmpty())){
                    try{
                        in = simpleDateFormat.parse(btncheckIn.getText().toString());
                        out = simpleDateFormat.parse(btncheckOut.getText().toString());
                        c1.setTime(in);
                        c2.setTime(out);
                        long dayDiff = out.getTime() - in.getTime();
                        String noOfnights = String.valueOf((TimeUnit.DAYS.convert(dayDiff,TimeUnit.MILLISECONDS)));
                        if (0>TimeUnit.DAYS.convert(dayDiff,TimeUnit.MILLISECONDS)){
                            Toast.makeText(getApplicationContext(),"Invalid Date selection!",Toast.LENGTH_LONG).show();
                            if (Build.VERSION.SDK_INT >= 11) {
                                activity.recreate();
                            } else {
                                activity.finish();
                                activity.startActivity(activity.getIntent());
                            }
                        }else{
                            if (checkBox.isChecked()){
                                System.out.println("Check box Checked!");
                                intentSendPayload.putExtra("Meals",true);
                            }else
                            {
                                intentSendPayload.putExtra("Meals",false);
                            }
                            intentSendPayload.putExtra("NoOfGuests",editTextGuestNo.getText().toString());
                        }
                        intentSendPayload.putExtra("Initial_Value",packageValue.toString());
                        intentSendPayload.putExtra("NoOfNights",noOfnights);
                        System.out.println("No of days from "+c1.get(Calendar.DATE)+" to "+c2.get(Calendar.DATE)+": "+TimeUnit.DAYS.convert(dayDiff,TimeUnit.MILLISECONDS));
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }

                    startActivity(intentSendPayload);
                }else {
                    Toast.makeText(CalculationActivity.this, "Please Enter Number of Guests", Toast.LENGTH_SHORT).show();

                }
            }
        }));

        btncheckIn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculationActivity.this, Date_picker_activity.class);
                startActivity(intent);
                finish();

            }
        }));

        btncheckOut.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculationActivity.this, Date_picker_activity.class);
                startActivity(intent);
                finish();

            }
        }));





    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                packageValue = 5000.00;
                break;
            case 1:
                packageValue = 3000.00;
                break;

            case 2:
                packageValue = 2000.00;
                break;
            case 3:
                packageValue = 1000.00;
                break;
            default:
                packageValue = 5000.01;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
            packageValue = 5000.00;
    }
}
