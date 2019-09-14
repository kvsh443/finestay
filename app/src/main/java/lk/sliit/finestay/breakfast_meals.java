package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class breakfast_meals extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_meals);

        b1 = findViewById(R.id.skip);
    }

    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(breakfast_meals.this,MainActivity.class);
                startActivity(intent);
            }
        }));

    }
}
