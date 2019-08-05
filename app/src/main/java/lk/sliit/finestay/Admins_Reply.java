package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admins_Reply extends AppCompatActivity {

    Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins__reply);

        b4 = findViewById(R.id.ad_cancel);
    }
    @Override
    protected void onResume() {
        super.onResume();

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admins_Reply.this, Admin_Feedback.class);
                startActivity(i1);
            }
        });

    }
}