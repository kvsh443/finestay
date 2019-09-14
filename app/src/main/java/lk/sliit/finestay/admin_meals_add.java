package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_meals_add extends AppCompatActivity {

    Button btneditmeal, btnaddmealb, btnaddmeall;
    EditText mealname,mealdes;
    Meal meal;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_meals_add);

        mealname =findViewById(R.id.txtmealname);
        mealdes = findViewById(R.id.txtmealdes);
        btnaddmealb =findViewById(R.id.add_breakfast);
        btneditmeal= findViewById(R.id.edit_meals);
        btnaddmeall = findViewById(R.id.add_lunch);

        meal = new Meal();

        btnaddmealb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal");
                try{

                    if(TextUtils.isEmpty(mealname.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter meal name",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(mealdes.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter meal description with includings",Toast.LENGTH_SHORT).show();

                    else{

                        meal.setMealname(mealname.getText().toString());
                        meal.setMealdescription(mealdes.getText().toString());

                        dbRef.push().setValue(meal);
                        // dbRef.child("u1").setValue(user);

                        Toast.makeText(getApplicationContext(),"Meal added Successfully ",Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(admin_meals_add.this,admin_meals_edit_delete.class);
                       // startActivity(intent);

                    }


                } catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnaddmeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Meal");
                try{

                    if(TextUtils.isEmpty(mealname.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter meal name",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(mealdes.getText().toString()))

                        Toast.makeText(getApplicationContext(),"Please enter meal description with includings",Toast.LENGTH_SHORT).show();

                    else{

                        meal.setMealname(mealname.getText().toString());
                        meal.setMealdescription(mealdes.getText().toString());

                        dbRef.push().setValue(meal);
                        // dbRef.child("u1").setValue(user);

                        Toast.makeText(getApplicationContext(),"Meal added Successfully ",Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(admin_meals_add.this,admin_meals_edit_delete.class);
                        // startActivity(intent);

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
        btneditmeal.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_meals_add.this,admin_meals_edit_delete.class);
                startActivity(intent);
            }
        }));
    }
}
