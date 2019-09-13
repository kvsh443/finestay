package lk.sliit.finestay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PropertyActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText guests,rooms,baths,value;
    DatabaseReference dbRef;
    Property pro;
    Button ADD,VIEW,UPDATE,DELETE;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        guests = findViewById(R.id.editGuest);
        rooms = findViewById(R.id.editRoom);
        baths = findViewById(R.id.editBath);
        value = findViewById(R.id.editValue);

        ADD = findViewById(R.id.btnAdd);
        VIEW = findViewById(R.id.btnView);
        UPDATE = findViewById(R.id.btnUpdate);
        DELETE = findViewById(R.id.btnDelete);

        pro = new Property();

        radioGroup = findViewById(R.id.radioGroup);

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
                        Toast.makeText(getApplicationContext(),"Please enter number of guests ",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(rooms.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter number of rooms ",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(baths.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter number of baths ",Toast.LENGTH_SHORT).show();
                    else{

                        pro.setName(radioButton.getText().toString().trim());
                        pro.setGuests(Integer.parseInt(guests.getText().toString().trim()));
                        pro.setRooms(Integer.parseInt(rooms.getText().toString().trim()));
                        pro.setBaths(Integer.parseInt(baths.getText().toString().trim()));
                        pro.setValue(Integer.parseInt(value.getText().toString().trim()));

                        dbRef.push().setValue(pro);
                        dbRef.child("Pro1").setValue(pro);

                        Toast.makeText(getApplicationContext(),"Data added Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                }catch (NumberFormatException e){

                    Toast.makeText(getApplicationContext(),"Invalid amount of Value",Toast.LENGTH_SHORT).show();

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
                            radioButton.setText(dataSnapshot.child("name").getValue().toString());
                            guests.setText(dataSnapshot.child("guests").getValue().toString());
                            rooms.setText(dataSnapshot.child("rooms").getValue().toString());
                            baths.setText(dataSnapshot.child("baths").getValue().toString());
                            value.setText(dataSnapshot.child("value").getValue().toString());
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
                                pro.setRooms(Integer.parseInt(rooms.getText().toString().trim()));
                                pro.setBaths(Integer.parseInt(baths.getText().toString().trim()));
                                pro.setValue(Integer.parseInt(value.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Property").child("Pro1");
                                dbRef.setValue(pro);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Succefully",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e){

                                Toast.makeText(getApplicationContext(),"Invalid amount of Value",Toast.LENGTH_SHORT).show();

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
        rooms.setText("");
        baths.setText("");
        value.setText("");

    }
}
