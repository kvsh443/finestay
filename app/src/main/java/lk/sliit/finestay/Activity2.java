package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity2 extends AppCompatActivity {

    Button b1,b2;
    EditText txt1,txt2;
    long fb = 0;

    DatabaseReference dbRef;
    Feedback feedback;

    private void clearControls(){
        txt1.setText("");
        txt2.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        b1 = findViewById(R.id.cus_cancel);
        b2 = findViewById(R.id.submit_feedback);
        txt1 = findViewById(R.id.uname);
        txt2 = findViewById(R.id.ufeedback);


        feedback = new Feedback();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");


        dbRef.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    //fb = Integer.getInteger( dataSnapshot.getKey()) + 1 ;
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        fb = Integer.valueOf(ds.getKey())+1;
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(txt1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter User Name ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txt2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter feedback ", Toast.LENGTH_SHORT).show();
                    else {
                        feedback.setUname(txt1.getText().toString().trim());
                        feedback.setFeedback(txt2.getText().toString().trim());

                       // dbRef.push().setValue(feedback);
                        //dbRef.child("feedback1").setValue(feedback);
                        dbRef.child(String.valueOf(fb)).setValue(feedback);

                        Toast.makeText(getApplicationContext()," Feedback Successfuly Added",Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(Activity2.this, FeedBackView.class);
                        startActivity(i1);
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Activity2.this, FeedBackView.class);
                startActivity(i1);
            }
        });




    }
}

