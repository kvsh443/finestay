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
    EditText txtlname,txtmobile,txtemail,txtcountry;
    TextView txtfname;
    User user;

    User user1;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);







        btndelete = findViewById(R.id.delete_acc);
        btnupdate = findViewById(R.id.update);
        btnsignout = findViewById(R.id.sign_out);
        txtfname = findViewById(R.id.fName2);
        txtlname = findViewById(R.id.lName2);
        txtemail = findViewById(R.id.email2);
        txtcountry = findViewById(R.id.editText8);
        txtmobile = findViewById(R.id.editText7);

        user = new User();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("5");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                       if (dataSnapshot.hasChildren()) {
                                                           Toast.makeText(getApplicationContext(), " display ", Toast.LENGTH_LONG).show();
                                                           txtfname.setText(dataSnapshot.child("fname").getValue().toString());
                                                           txtlname.setText(dataSnapshot.child("lname").getValue().toString());
                                                           txtemail.setText(dataSnapshot.child("email").getValue().toString());
                                                           txtmobile.setText(dataSnapshot.child("mobile").getValue().toString());
                                                           txtcountry.setText(dataSnapshot.child("country").getValue().toString());
                                                       } else
                                                           Toast.makeText(getApplicationContext(), "No source to display ", Toast.LENGTH_LONG).show();
                                                   }
                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError databaseError) {
                                                   }
                                               }
        );

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("User");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("5")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("User").child("5");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Account. deleted successfully ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(profile.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to delete ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("User");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("5")){
                            try {
                                user.setFname(txtfname.getText().toString().trim());
                                user.setLname(txtlname.getText().toString().trim());
                                user.setMobile(Integer.parseInt(txtmobile.getText().toString().trim()));
                                user.setCountry(txtcountry.getText().toString().trim());
                                user.setEmail(txtemail.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child("4");
                                dbRef.setValue(user);
                                //clearControls();
                                Toast.makeText(getApplicationContext(), "Account updated Successfully ", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(profile.this, use_by_customer.class);
                                startActivity(intent);
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid input ",Toast.LENGTH_LONG).show();
                            }
                        }else
                            Toast.makeText(getApplicationContext(),"No source to display ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}


