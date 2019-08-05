package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reservation__manager);
//    }
        setContentView(R.layout.activity_main);


        b1 = findViewById(R.id.login_redirect);
        b2 = findViewById(R.id.reservationlist_redirect);
        b5 = findViewById(R.id.conform_redirect);
        b6 = findViewById(R.id.property_ad_redirect);
        b7 = findViewById(R.id.property_redirect);
        b8 = findViewById(R.id.feedback_ad_redirect);
        b9 = findViewById(R.id.feedback_redirect);
        b4 = findViewById(R.id.date_picker_redirect);
        b3 = findViewById(R.id.calculation_redirect);


//        b1.setOnClickListener(this);
//        b2.setOnClickListener(this);
//        b3.setOnClickListener(this);
//        b4.setOnClickListener(this);
//        b5.setOnClickListener(this);
//        b6.setOnClickListener(this);
//        b7.setOnClickListener(this);
//        b8.setOnClickListener(this);
//        b9.setOnClickListener(this);

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

        b2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Reservation_Manager.class);
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

        b4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Date_picker_activity.class);
                startActivity(intent);
            }
        }));

        b5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,conform_Reservation.class);
                startActivity(intent);
            }
        }));


        b6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PropertyActivity.class);
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

//        b8.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,);
//                startActivity(intent);
//            }
//        }));

        b9.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity2.class);
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
