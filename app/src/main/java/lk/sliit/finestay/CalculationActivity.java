package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculationActivity extends AppCompatActivity {

    Button b5;
    Button b4;
    Button b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation_layout);

        b5 = findViewById(R.id.btnReserve);
        b4 = findViewById(R.id.btncheckIn);
        b6 = findViewById(R.id.btncheckout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        b5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculationActivity.this, conform_Reservation.class);
                startActivity(intent);
            }
        }));

        b4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculationActivity.this, Date_picker_activity.class);
                startActivity(intent);
            }
        }));

        b6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculationActivity.this, Date_picker_activity.class);
                startActivity(intent);
            }
        }));

    }
}
