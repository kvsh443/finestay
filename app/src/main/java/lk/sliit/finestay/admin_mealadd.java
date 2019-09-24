package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_mealadd extends AppCompatActivity {
    Button b1,b2,b3,b4,viewadd;
    EditText ap1,rc1,no1,bv1,aprice,bprice,cprice,dprice;
    long a1=0,a2=0,a3=0,a4=0;
    DatabaseReference dbRef1,dbRef2,dbRef3,dbRef4;
    Meal_Appetizer meal_appertizer;
    Meal_Rice meal_rice;
    Meal_Noodles meal_noodles;
    Meal_Beverages meal_beverages;

    private void clearControls(){
        ap1.setText("");
        rc1.setText("");
        no1.setText("");
        bv1.setText("");
        aprice.setText("");
        bprice.setText("");
        cprice.setText("");
        dprice.setText("");
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mealadd);
        ap1 = findViewById(R.id.a1);
        rc1 = findViewById(R.id.b1);
        no1 = findViewById(R.id.c1);
        bv1 = findViewById(R.id.d1);
        aprice = findViewById(R.id.aprice);
        bprice = findViewById(R.id.bprice);
        cprice = findViewById(R.id.cprice);
        dprice = findViewById(R.id.dprice);

        b1 = findViewById(R.id.adda);
        b2 = findViewById(R.id.addb);
        b3 = findViewById(R.id.addc);
        b4 = findViewById(R.id.addd);

        viewadd = findViewById(R.id.viewadd);

        meal_appertizer = new Meal_Appetizer();
        dbRef1 = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer");
        dbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    a1=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   if(TextUtils.isEmpty(ap1.getText().toString()))
                       Toast.makeText(getApplicationContext(),"Please Enter appetizer name ",Toast.LENGTH_SHORT).show();
                   else if(TextUtils.isEmpty(aprice.getText().toString()))
                       Toast.makeText(getApplicationContext(),"Please appetizer price",Toast.LENGTH_SHORT).show();
                   else {
                       meal_appertizer.setAppertizer_name(ap1.getText().toString().trim());
                       meal_appertizer.setAprice(Integer.parseInt(aprice.getText().toString().trim()));

                       dbRef1.child(String.valueOf(a1+1)).setValue(meal_appertizer);

                   Toast.makeText(getApplicationContext(),"Appertizer Added Successfully ",Toast.LENGTH_SHORT).show();
               }
            }
               catch (Exception e){
                   Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
               }
            }
        });


        meal_rice = new Meal_Rice();
        dbRef2 = FirebaseDatabase.getInstance().getReference().child("Meal_Rice");
        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    a2=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(rc1.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Rice meal name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(bprice.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Meal price",Toast.LENGTH_SHORT).show();
                    else {
                        meal_rice.setRice_name(rc1.getText().toString().trim());
                        meal_rice.setBprice(Integer.parseInt(bprice.getText().toString().trim()));
                        //dbRef2.push().setValue(meal_rice);

                        dbRef2.child(String.valueOf(a2+1)).setValue(meal_rice);

                        Toast.makeText(getApplicationContext(),"Rice Meal Added Successfully ",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }
        });



        meal_noodles = new Meal_Noodles();
        dbRef3 = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles");
        dbRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    a3=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(no1.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Noodles meal name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(cprice.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Meal price",Toast.LENGTH_SHORT).show();
                    else {
                        meal_noodles.setNoodle_name(no1.getText().toString().trim());
                        meal_noodles.setCprice(Integer.parseInt(cprice.getText().toString().trim()));

                        //dbRef3.push().setValue(meal_noodles);

                        dbRef3.child(String.valueOf(a3+1)).setValue(meal_noodles);

                        Toast.makeText(getApplicationContext(),"Noodles Meal Added Successfully ",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }
        });



        meal_beverages = new Meal_Beverages();
        dbRef4 = FirebaseDatabase.getInstance().getReference().child("Meal_Beverages");
        dbRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    a4=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(bv1.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Beverage name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(dprice.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Beverage price",Toast.LENGTH_SHORT).show();
                    else {
                        meal_beverages.setBeverage_name(bv1.getText().toString().trim());
                        meal_beverages.setDprice(Integer.parseInt(dprice.getText().toString().trim()));

                        //dbRef4.push().setValue(meal_beverages);
                        dbRef4.child(String.valueOf(a4+1)).setValue(meal_beverages);

                        Toast.makeText(getApplicationContext(),"Beverage Added Successfully ",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
            viewadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(admin_mealadd.this,added_b_meals.class);
                    startActivity(intent);
                }
            });
    }
}