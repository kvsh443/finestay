package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class meals extends AppCompatActivity {

    ImageView i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        i1 = findViewById(R.id.breakfastmenu);
        i2 = findViewById(R.id.lunchmenu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        i1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(meals.this,breakfast_meals.class);
                startActivity(intent);
            }
        }));
        i2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(meals.this,lunch_meals.class);
                startActivity(intent);
            }
        }));
    }
}
