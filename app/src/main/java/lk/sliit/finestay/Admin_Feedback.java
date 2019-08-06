package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Feedback extends AppCompatActivity {

    Button b3;
    Button b6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__feedback);

        b3 = findViewById(R.id.res1);
        b6 = findViewById(R.id.ad_back);
    }
    @Override
    protected void onResume() {
        super.onResume();

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admin_Feedback.this,Admins_Reply.class);
                startActivity(i1);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admin_Feedback.this,FeedbackActivity.class);
                startActivity(i1);
            }
        });

    }
}