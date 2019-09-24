package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class use_by_admin extends AppCompatActivity {

    Button b1,b2,b3 , b5 , res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_by_admin);

        b1 = findViewById(R.id.add_meals);
        b5 = findViewById(R.id.feedbackBtn);
        b3 = findViewById(R.id.added_meals);
        res = findViewById(R.id.adminProp);
    }


    @Override
    protected void onResume() {
        super.onResume();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(use_by_admin.this,admin_mealadd.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(use_by_admin.this,added_b_meals.class);
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(use_by_admin.this,Admin_Feedback.class);
                startActivity(i1);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(use_by_admin.this,  Reservation_Manager.class);
                startActivity(i1);
            }
        });
    }
}
