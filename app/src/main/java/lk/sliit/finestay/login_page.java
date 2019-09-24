package lk.sliit.finestay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login_page extends AppCompatActivity {
    Button b1,b2 ;
    EditText uemail,password;
    User user;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        uemail = findViewById(R.id.email);
        password = findViewById(R.id.pw);
        b1 = findViewById(R.id.registration_redirect);
        b2 = findViewById(R.id.login);

        user = new User();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("5");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            Toast.makeText(getApplicationContext(), " WELCOME TO CUSTOMER LOGIN ", Toast.LENGTH_LONG).show();                            String temail = (dataSnapshot.child("email").getValue().toString());
                            String tpwd = (dataSnapshot.child("password").getValue().toString());

                            String email = uemail.getText().toString();
                            String pwd = password.getText().toString();

                            if (!temail.equals(email)) {
                                Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_SHORT).show();
                            }
                            else if (!tpwd.equals(pwd)) {
                                Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Login Details are correct", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(login_page.this, use_by_customer.class);
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "No source to display ", Toast.LENGTH_LONG).show();
                       }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                String email = uemail.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    uemail.setError("Please Enter User Email ");
                    uemail.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please Enter User Password ");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login_page.this,"Fields are empty !",Toast.LENGTH_LONG).show();
                }
                else {
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this, registration_page.class);
                startActivity(intent);
            }
        });
    }
}
