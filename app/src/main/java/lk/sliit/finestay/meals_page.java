package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class meals_page extends AppCompatActivity {

    ImageView image1,image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_page);

        image1 = findViewById(R.id.breakfastmenu);
        image2 = findViewById(R.id.lunchmenu);

    }

    @Override
    protected void onResume() {
        super.onResume();
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(meals_page.this,breakfast_meals.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(meals_page.this,lunch_meals.class);
                startActivity(intent);
            }
        });
    }
}
