package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration_page extends AppCompatActivity {

    Button btnregister;
    ImageButton b2;
    EditText txtfname,txtlname,txtemail,txtcountry,txtmobile,txtpwd;
    DatabaseReference dbRef;
    User user;

    private void clearControls(){
        txtfname.setText("");
        txtlname.setText("");
        txtemail.setText("");
        txtcountry.setText("");
        txtmobile.setText("");
        txtpwd.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        b2 = findViewById(R.id.logo);
        btnregister = findViewById(R.id.register);
        txtfname = findViewById(R.id.fname);
        txtlname = findViewById(R.id.lname);
        txtemail = findViewById(R.id.email);
        txtcountry = findViewById(R.id.country);
        txtmobile = findViewById(R.id.mobile);
        txtpwd = findViewById(R.id.pwd);

        user = new User();
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");


                try{

                    if(TextUtils.isEmpty(txtfname.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter first name",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtlname.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter last name",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtemail.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtcountry.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter country",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtmobile.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter mobile number",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtpwd.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter a password",Toast.LENGTH_SHORT).show();
                    else{

                        user.setFname(txtfname.getText().toString().trim());
                        user.setLname(txtlname.getText().toString().trim());
                        user.setEmail(txtemail.getText().toString().trim());
                        user.setCountry(txtcountry.getText().toString().trim());
                        user.setMobile(Integer.parseInt(txtmobile.getText().toString().trim()));
                        user.setPassword(txtpwd.getText().toString().trim());


                        dbRef.push().setValue(user);
                       // dbRef.child("u1").setValue(user);

                        Toast.makeText(getApplicationContext(),"Registered Successfully ",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(registration_page.this,profile.class);
                        startActivity(intent);

                    }


                } catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();


                }

            }
        });

    }


}
