package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class conform_Reservation extends AppCompatActivity {

    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conform_reserve);

        b2 = findViewById(R.id.buttonReserveConform);

    }

    @Override
    protected void onResume() {
        super.onResume();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(conform_Reservation.this, Reservation_Manager.class);
                startActivity(intent);
            }
        });
    }
}
