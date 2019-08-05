package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class registration_page extends AppCompatActivity {

    Button b1;
    ImageButton b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        b1 = findViewById(R.id.register);

        b2 = findViewById(R.id.logo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration_page.this,profile.class);
                startActivity(intent);
            }
        }));
    }
}
