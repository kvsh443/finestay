package lk.sliit.finestay;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registration_page extends AppCompatActivity {

    Button btnregister;
    ImageButton b2;
    EditText txtfname,txtlname,txtemail,txtcountry,txtmobile,txtpwd,txtcpwd;
    DatabaseReference dbRef;
    User user;
    long usr =0;

    private void clearControls(){
        txtfname.setText("");
        txtfname.setText("");
        txtlname.setText("");
        txtemail.setText("");
        txtcountry.setText("");
        txtmobile.setText("");
        txtpwd.setText("");
        txtcpwd.setText("");
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        b2 = findViewById(R.id.logo);
        btnregister = findViewById(R.id.adminuse);
        txtfname = findViewById(R.id.txtfname);
        txtlname = findViewById(R.id.txtlname);
        txtemail = findViewById(R.id.txtemail);
        txtcountry = findViewById(R.id.txtcountry);
        txtmobile = findViewById(R.id.txtmobile);
        txtpwd = findViewById(R.id.pw);
        txtcpwd = findViewById(R.id.cpw);

        final String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
       txtemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(txtemail.getText().toString().matches(emailpattern)){
                    Toast.makeText(getApplicationContext(),"Valid email",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter valid email ", Toast.LENGTH_LONG).show();
                    txtemail.setError("Please Enter User Email ");
                    txtemail.requestFocus();
                }
            }
        });

        user = new User();
        dbRef = FirebaseDatabase.getInstance().getReference().child("User");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    usr=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            String temail = txtemail.getText().toString();
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(txtfname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter first name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtlname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter last name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtemail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtcountry.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter country",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtmobile.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter mobile number",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtpwd.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a password",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtcpwd.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter password again",Toast.LENGTH_SHORT).show();
                    else if(txtcpwd.equals(txtpwd))
                        Toast.makeText(getApplicationContext(),"Your password do not match with your confirm password",Toast.LENGTH_SHORT).show();
                    else{
                        user.setFname(txtfname.getText().toString().trim());
                        user.setLname(txtlname.getText().toString().trim());
                        user.setEmail(txtemail.getText().toString().trim());
                        user.setCountry(txtcountry.getText().toString().trim());
                        user.setMobile(Integer.parseInt(txtmobile.getText().toString().trim()));
                        user.setPassword(txtpwd.getText().toString().trim());

                        usr =4;
                        dbRef.child(String.valueOf(usr+1)).setValue(user);

                        Toast.makeText(getApplicationContext(),"Registered Successfully ",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(registration_page.this, use_by_customer.class);
                        startActivity(intent);
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
