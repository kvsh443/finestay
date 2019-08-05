package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedbackActivity extends AppCompatActivity {

    Button b1;
    Button b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

             b1 = findViewById(R.id.add_review);
             b5 = findViewById(R.id.admin);
         }

         @Override
         protected void onResume() {
             super.onResume();

             b1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent i1 = new Intent(FeedbackActivity.this,Activity2.class);
                     startActivity(i1);
                 }
             });

             b5.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent i1 = new Intent(FeedbackActivity.this,Admin_Feedback.class);
                     startActivity(i1);
                 }
             });
    }
}
