package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Silver_Package extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver__package);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("last");
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
    }


    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference.child("PackageValue").setValue(15000);

                Intent intent = new Intent(Silver_Package.this, CalculationActivity.class);
                startActivity(intent);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference.child("PackageValue").setValue(12000);

                Intent intent = new Intent(Silver_Package.this, CalculationActivity.class);
                startActivity(intent);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference.child("PackageValue").setValue(10000);

                Intent intent = new Intent(Silver_Package.this, CalculationActivity.class);
                startActivity(intent);

            }
        });

    }
}
