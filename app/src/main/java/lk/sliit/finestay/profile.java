package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    Button btndelete,btnupdate,btnsignout;
    EditText txtfname,txtlname,txtmobile,txtemail,txtcountry;
    User user;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btndelete = findViewById(R.id.delete);
        btnupdate = findViewById(R.id.update);
        btnsignout = findViewById(R.id.signout);

        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("user");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtfname.setText(dataSnapshot.child("fname").toString());
                            txtlname.setText(dataSnapshot.child("lname").toString());
                            txtemail.setText(dataSnapshot.child("email").toString());
                            txtmobile.setText(dataSnapshot.child("mobile").toString());
                            txtcountry.setText(dataSnapshot.child("country").toString());
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to display ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }    )    ;
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("User");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("user")){
                            try{
                                user.setFname(txtfname.getText().toString().trim());
                                user.setLname(txtlname.getText().toString().trim());
                                user.setEmail(txtemail.getText().toString().trim());
                                user.setMobile(Integer.parseInt(txtmobile.getText().toString().trim()));
                                user.setCountry(txtcountry.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child("user");
                                dbRef.setValue(user);
                                //clearControls();
                                Toast.makeText(getApplicationContext(),"Data updated successfully ",Toast.LENGTH_LONG).show();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Invalid inputs",Toast.LENGTH_LONG).show();
                            }
                        }else
                            Toast.makeText(getApplicationContext(),"No source to update ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("User");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("user")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("user");
                            delRef.removeValue();
                            //clearControls();
                            Toast.makeText(getApplicationContext(),"Account deleted successfully ",Toast.LENGTH_LONG).show();


                        }else
                            Toast.makeText(getApplicationContext(),"Error Occured ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(profile.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
