package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_meals_edit_delete extends AppCompatActivity {

    Button showa,showb,showc,showd,upa,upb,upc,upd,dela,delb,delc,deld;
    EditText ap1,rc1,no1,bv1,aprice,bprice,cprice,dprice;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Meal_Appetizer meal_appetizer;
    Meal_Rice meal_rice;
    Meal_Noodles meal_noodles;
    Meal_Beverages meal_beverages;
    String aptID;

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
        setContentView(R.layout.activity_admin_meals_edit_delete);

        Intent intent = getIntent();
        aptID = intent.getStringExtra("APTID");

        showa = findViewById(R.id.showa);
        showb = findViewById(R.id.showb);
        showc = findViewById(R.id.showc);
        showd = findViewById(R.id.showd);
        upa = findViewById(R.id.upa);
        upb = findViewById(R.id.upb);
        upc = findViewById(R.id.upc);
        upd = findViewById(R.id.upd);

        dela = findViewById(R.id.deletea);
        delb = findViewById(R.id.deleteb);
        delc = findViewById(R.id.deletec);

        ap1 = findViewById(R.id.a1);
        rc1 = findViewById(R.id.b1);
        no1 = findViewById(R.id.c1);
        bv1 = findViewById(R.id.d1);

        aprice = findViewById(R.id.aprice);
        bprice = findViewById(R.id.bprice);
        cprice = findViewById(R.id.cprice);
        dprice = findViewById(R.id.dprice);

        meal_appetizer = new Meal_Appetizer();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer").child(aptID);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    ap1.setText(dataSnapshot.child("appertizer_name").getValue().toString());
                    aprice.setText(dataSnapshot.child("aprice").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext()," No source to display ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        showa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        upa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(aptID)){
                            try {
                                meal_appetizer.setAppertizer_name(ap1.getText().toString().trim());
                                meal_appetizer.setAprice(Float.parseFloat(aprice.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer").child(aptID);
                                dbRef.setValue(meal_appetizer);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();

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

        dela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(aptID)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Appetizer").child(aptID);
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(admin_meals_edit_delete.this, admin_mealadd.class);
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









        meal_rice = new Meal_Rice();
        showb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("Meal_Rice").child("1");
                readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            rc1.setText(dataSnapshot.child("rice_name").getValue().toString());
                            bprice.setText(dataSnapshot.child("bprice").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext()," No source to display ",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        upb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Meal_Rice");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try {
                                meal_rice.setRice_name(rc1.getText().toString().trim());
                                meal_rice.setBprice(Float.parseFloat(bprice.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Rice").child("1");
                                dbRef.setValue(meal_rice);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();
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

        delb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Meal_Rice");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("3")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Rice").child("3");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully ",Toast.LENGTH_LONG).show();
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



        meal_noodles = new Meal_Noodles();
        showc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles").child("1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            no1.setText(dataSnapshot.child("noodle_name").getValue().toString());
                            cprice.setText(dataSnapshot.child("cprice").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext()," No source to display ",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        upc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try {
                                meal_noodles.setNoodle_name(no1.getText().toString().trim());
                                meal_noodles.setCprice(Float.parseFloat(cprice.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles").child("1");
                                dbRef.setValue(meal_noodles);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();
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

        delc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("3")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Noodles").child("3");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully ",Toast.LENGTH_LONG).show();
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



        meal_beverages = new Meal_Beverages();
        showd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Meal_Beverages").child("1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            bv1.setText(dataSnapshot.child("beverage_name").getValue().toString());
                            dprice.setText(dataSnapshot.child("dprice").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext()," No source to display ",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Meal_Beverages");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try {
                                meal_beverages.setBeverage_name(bv1.getText().toString().trim());
                                meal_beverages.setDprice(Float.parseFloat(dprice.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal_Beverages").child("1");
                                dbRef.setValue(meal_beverages);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();
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


