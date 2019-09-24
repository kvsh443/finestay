package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class feedback_update_delete extends AppCompatActivity {

    Button b1,b2;
    EditText t1,t2;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Feedback feedback;
    String feedbackID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_update_delete);

        b1 = findViewById(R.id.update);
        b2 = findViewById(R.id.delete);
        t1 = findViewById(R.id.usname);
        t2 = findViewById(R.id.usfeedback);

        Intent intent = getIntent();

        feedback = new Feedback();
        feedbackID = intent.getStringExtra("FeedbackID");

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(feedbackID);

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t1.setText(dataSnapshot.child("uname").getValue().toString());
                t2.setText(dataSnapshot.child("feedback").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(feedbackID )){
                            try {
                                feedback.setUname(t1.getText().toString().trim());
                                feedback.setFeedback(t2.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(feedbackID);
                                dbRef.setValue(feedback);


                                Toast.makeText(getApplicationContext(), "Data updated Successfully ", Toast.LENGTH_LONG).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid input ",Toast.LENGTH_LONG).show();
                            }
                        }else
                            Toast.makeText(getApplicationContext(),"No source to display ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(feedbackID )){
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(feedbackID);
                                    dbRef.removeValue();

                                    Toast.makeText(getApplicationContext(),"Data deleted successfully ",Toast.LENGTH_LONG).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"No source to delete ",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }


        });

    }
}
