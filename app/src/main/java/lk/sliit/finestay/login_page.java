package lk.sliit.finestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends AppCompatActivity {

    Button b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        b2 = findViewById(R.id.login);
        b3 = findViewById(R.id.registration_redirect);
    }

    @Override
    protected void onResume() {
        super.onResume();
        b3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this,registration_page.class);
                startActivity(intent);
            }
        }));
    }
}
