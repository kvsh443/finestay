package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        b1 = findViewById(R.id.update);
        b2 = findViewById(R.id.sign_out);
    }

    @Override
    protected void onResume() {
        super.onResume();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(profile.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
