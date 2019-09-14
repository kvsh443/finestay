package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9,b_meals,b_admin_meals;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reservation__manager);
//    }
        Toast.makeText(MainActivity.this,"Firebase success ",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);


        b1 = findViewById(R.id.login_redirect);
        b7 = findViewById(R.id.property);
        b9 = findViewById(R.id.feedback);
        b3 = findViewById(R.id.calculation);
        b_meals = findViewById(R.id.meal);
        b_admin_meals = findViewById(R.id.admin_meal);

    }
    @Override
    protected void onResume() {
        super.onResume();
        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,login_page.class);
                startActivity(intent);
            }
        }));

        b3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CalculationActivity.class);
                startActivity(intent);
            }
        }));

        b7.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Pro_UseActivity.class);
                startActivity(intent);
            }
        }));

        b9.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FeedbackActivity.class);
                startActivity(intent);
            }
        }));

        b_meals.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,meals.class);
                startActivity(intent);
            }
        }));
        b_admin_meals.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,admin_meals_add.class);
                startActivity(intent);
            }
        }));
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case  R.id.login_redirect:
//                intent = new Intent(MainActivity.this,login_page.class);
//                startActivity(intent);
//            case R.id.reservationlist_redirect:
//                intent = new Intent(MainActivity.this,Reservation_Manager.class);
//               startActivity(intent);
//
//            case R.id.calculation_redirect:
//                intent = new Intent(MainActivity.this,CalculationActivity.class);
//                startActivity(intent);
//
//            case R.id.conform_redirect:
//                intent = new Intent(MainActivity.this,conform_Reservation.class);
//                startActivity(intent);
//            case R.id.property_ad_redirect:
//                intent = new Intent(MainActivity.this,PropertyActivity.class);
//                startActivity(intent);
//            case R.id.property_redirect:
//                intent = new Intent(MainActivity.this,Pro_UseActivity.class);
//                startActivity(intent);
//            case R.id.feedback_ad_redirect:
//                intent = new Intent(MainActivity.this,conform_Reservation.class);
//                startActivity(intent);
//            case R.id.feedback_redirect:
//                intent = new Intent(MainActivity.this,Activity2.class);
//                startActivity(intent);
//            case R.id.date_picker_redirect:
//                intent = new Intent(MainActivity.this,Date_picker_activity.class);
//                startActivity(intent);


//        }
 //   }
}
