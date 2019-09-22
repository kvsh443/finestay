package lk.sliit.finestay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PropertyActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "PropertyActivity";

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText guests,rooms,children;
    DatabaseReference dbRef;
    private TextView name,Tguests,Trooms,Tchild;
    private SensorManager sensorManager;
    private Sensor accelerometer;






    Button ADD,VIEW,UPDATE,DELETE;


    Property pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(PropertyActivity.this,accelerometer,sensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered accelerometer listener");


        guests = findViewById(R.id.editGuest);
        rooms = findViewById(R.id.editRoom);
        children = findViewById(R.id.editChild);


        ADD = findViewById(R.id.btnAdd);
        VIEW = findViewById(R.id.btnView);
        UPDATE = findViewById(R.id.btnUpdate);
        DELETE = findViewById(R.id.btnDelete);

        pro = new Property();

        radioGroup = findViewById(R.id.radioGroup);



        name = findViewById(R.id.txtName);
        Tguests = findViewById(R.id.txtGuest);
        Tchild = findViewById(R.id.txtChild);
        Trooms = findViewById(R.id.txtRoom);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               dbRef = FirebaseDatabase.getInstance().getReference().child("Property");

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                try {

                    if (TextUtils.isEmpty(radioButton.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please select a type of package ",Toast.LENGTH_SHORT).show();
                    else  if (TextUtils.isEmpty(guests.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter number of adults ",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(children.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter number of children",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(rooms.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter number of rooms ",Toast.LENGTH_SHORT).show();

                    else{



                        pro.setName(radioButton.getText().toString().trim());
                        pro.setGuests(Integer.parseInt(guests.getText().toString().trim()));
                        pro.setChildren(Integer.parseInt(children.getText().toString().trim()));
                        pro.setRooms(Integer.parseInt(rooms.getText().toString().trim()));



                        dbRef.push().setValue(pro);
                        dbRef.child("Pro1").setValue(pro);

                        Toast.makeText(getApplicationContext(),"Data added Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();


                        if(radioButton.equals(findViewById(R.id.btnPlat))) {

                            Intent intent = new Intent(PropertyActivity.this,Pro_UseActivity.class);
                            startActivity(intent);

                        }
                        else if (radioButton.equals(findViewById(R.id.btnGld))){

                            Intent intent = new Intent(PropertyActivity.this,Gold_Package.class);
                            startActivity(intent);
                        }
                        else if (radioButton.equals(findViewById(R.id.btnSil))){

                            Intent i = new Intent(PropertyActivity.this,Silver_Package.class);
                            startActivity(i);
                        }

                    }




                }catch (NumberFormatException e){

                    Toast.makeText(getApplicationContext(),"Invalid amount of adults",Toast.LENGTH_SHORT).show();

                }



            }
        });


        VIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Property").child("Pro1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChildren()){

                            String roomString = dataSnapshot.child("rooms").getValue().toString();
                            String nameString = dataSnapshot.child("name").getValue().toString();
                            String guestsString = dataSnapshot.child("guests").getValue().toString();
                            String childrenString = dataSnapshot.child("children").getValue().toString();


                            radioButton.setText(nameString);
                            guests.setText(guestsString);
                            children.setText(childrenString);
                            rooms.setText(roomString);


                        }
                        else
                            Toast.makeText(getApplicationContext(),"No details to display",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        UPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Property");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild("Pro1")){
                            try {
                                pro.setName(radioButton.getText().toString().trim());
                                pro.setGuests(Integer.parseInt(guests.getText().toString().trim()));
                                pro.setChildren(Integer.parseInt(children.getText().toString().trim()));
                                pro.setRooms(Integer.parseInt(rooms.getText().toString().trim()));



                                dbRef = FirebaseDatabase.getInstance().getReference().child("Property").child("Pro1");
                                dbRef.setValue(pro);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Succefully",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e){

                                Toast.makeText(getApplicationContext(),"Invalid number of adults",Toast.LENGTH_SHORT).show();

                            }


                        }

                        else
                            Toast.makeText(getApplicationContext(),"No details to Update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Property");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Pro1")){

                            dbRef = FirebaseDatabase.getInstance().getReference().child("Property").child("Pro1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted Successfully",Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(getApplicationContext(),"No details to Delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }



    public void checkButton(View v){

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this,"Selected Radio Button: "+radioButton.getText(),Toast.LENGTH_SHORT).show();



    }

    private void clearControls(){

        radioButton.setText("");
        guests.setText("");
        children.setText("");
        rooms.setText("");




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d(TAG, "onSensorChanged: X: " +sensorEvent.values[0] +"Y: " + sensorEvent.values[1] + "Z: "+ sensorEvent.values[2]);

    }
}
