package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        b1 = findViewById(R.id.login);
        b2 = findViewById(R.id.registration_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();

        b2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this,registration_page.class);
                startActivity(intent);
            }
        }));
    }
}
