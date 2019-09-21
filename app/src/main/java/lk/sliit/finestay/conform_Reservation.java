package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lk.sliit.finestay.Model.Reservation;

public class conform_Reservation extends AppCompatActivity {

    Button buttonReserveConform;
    TextView textViewNoNights, textViewEstimatePrice, textViewServiceFee, textViewTotal ;
    Intent intentGetPayloadData;
    Context context;
    DatabaseReference databaseReference;
    Reservation reservation;
    String meals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conform_reserve);

        buttonReserveConform = findViewById(R.id.buttonReserveConform);
        textViewNoNights = findViewById(R.id.textViewNoNights);
        textViewServiceFee = findViewById(R.id.textViewServiceFee);
        textViewEstimatePrice = findViewById(R.id.textViewEstimatePrice);
        textViewTotal = findViewById(R.id.textViewTotal);
        intentGetPayloadData = getIntent();
        context = getApplicationContext();
        reservation = new Reservation();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reservation");


        try {
            if (!(intentGetPayloadData.getStringExtra("NoOfNights")).isEmpty()) {
                textViewNoNights.setText("No of nights:"+intentGetPayloadData.getStringExtra("NoOfNights"));
                textViewEstimatePrice.setText(intentGetPayloadData.getStringExtra("Initial_Value")+" X "+ intentGetPayloadData.getStringExtra("NoOfNights"));
            }
        }catch (Exception e){}

        try {
            if (!(intentGetPayloadData.getExtras().getBoolean("meals"))) {

                textViewServiceFee.setText("600");
                meals = "no";

            }else
            {
                textViewServiceFee.setText("800");
                meals = "yes";
            }
        }catch (Exception e){}


            Double estimate = Double.parseDouble(intentGetPayloadData.getStringExtra("Initial_Value"));
            Double serviceCharge = Double.parseDouble(textViewServiceFee.getText().toString());
            Double noOFNights = Double.parseDouble(intentGetPayloadData.getStringExtra("NoOfNights"));

            Double total = (estimate * noOFNights) + serviceCharge;
            System.out.println(total+" "+serviceCharge+" ");
            textViewTotal.setText(total.toString());


    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonReserveConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(conform_Reservation.this).create();
                alertDialog.setTitle("Conform Reservation");
                alertDialog.setMessage("Are you sure to conform this reservation ?");
                alertDialog.setCancelable(true);
                alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Conform", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reservation.setCheckIn(intentGetPayloadData.getStringExtra("inDate"));
                        reservation.setCheckOut(intentGetPayloadData.getStringExtra("outDate"));
                        reservation.setMeals(meals);
                        reservation.setTotal(textViewTotal.getText().toString());
                        reservation.setCustomerID("3");
                        final Map<String, Object> myObjectAsDict = new HashMap<>();
                        myObjectAsDict.put("customerID",reservation.getCustomerID());
                        myObjectAsDict.put("checkIn", reservation.getCheckIn());
                        myObjectAsDict.put("checkOut",reservation.getCheckOut());
                        myObjectAsDict.put("meals", reservation.getMeals());
                        myObjectAsDict.put("total", reservation.getTotal());
                        myObjectAsDict.put("status", "pending");

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (!(dataSnapshot.child(reservation.getCustomerID()).exists())){
                                    databaseReference.child(reservation.getCustomerID()).updateChildren(myObjectAsDict).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(conform_Reservation.this, "Order Successfully placed!", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(conform_Reservation.this, "Order Failed to be placed", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                });}else {
                                    Toast.makeText(conform_Reservation.this, "An Order has been placed already.", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                alertDialog.setButton(Dialog.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(conform_Reservation.this,Reservation_Manager.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });
    }
}
