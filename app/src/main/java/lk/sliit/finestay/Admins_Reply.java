package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admins_Reply extends AppCompatActivity {

    TextView username , feedback;
    EditText reply;
    Button b4 , submit;
    DatabaseReference dbRef;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins__reply);

        b4 = findViewById(R.id.ad_cancel);
        username = findViewById(R.id.username);
        feedback = findViewById(R.id.feedback);
        reply = findViewById(R.id.reply);
        submit = findViewById(R.id.submit);

        Intent intent = getIntent();
        Feedback getfeedback = (Feedback) intent.getSerializableExtra("FeedbackData");
        ID = intent.getStringExtra("FeedbackID");

        username.setText( getfeedback.getUname());
        feedback.setText( getfeedback.getFeedback() );

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (reply.getText().toString().length() > 0) {
                    DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
                    upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(ID)) {
                                try {
                                    Feedback fb = new Feedback();
                                    fb.setUname(username.getText().toString().trim());
                                    fb.setFeedback(feedback.getText().toString().trim());
                                    fb.setResponse(reply.getText().toString().trim());

                                    dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(ID);
                                    dbRef.setValue(fb);

                                    Intent intent = new Intent( Admins_Reply.this , Admin_Feedback.class);
                                    finish();
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(getApplicationContext(), "Invalid input ", Toast.LENGTH_LONG).show();
                                }
                            } else
                                Toast.makeText(getApplicationContext(), "No source to display ", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Please add a valid response", Toast.LENGTH_LONG).show();
                }
            }
        });

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