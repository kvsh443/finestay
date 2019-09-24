package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lk.sliit.finestay.Model.Property;

public class use_by_customer extends AppCompatActivity {

    Button b1,b2,b3 , pro , feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_by_customer);

        b1 = findViewById(R.id.profile);
        b2 = findViewById(R.id.mealmenu);
        b3 = findViewById(R.id.signout);
        pro = findViewById(R.id.property);
        feed = findViewById(R.id.feedbackX);

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(use_by_customer.this , PropertyActivity.class );
                startActivity(intent);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(use_by_customer.this , FeedBackView.class );
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(use_by_customer.this,profile.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(use_by_customer.this,meals_page.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(use_by_customer.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
