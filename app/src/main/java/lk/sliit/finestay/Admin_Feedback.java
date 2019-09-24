package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Feedback extends AppCompatActivity {

    Button b3;
    Button b6;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<Feedback> feedbacklist;
    ArrayList<String> feedbackIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__feedback);

        listView = findViewById(R.id.adminlistview);
        b6 = findViewById(R.id.ad_back);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Feedback");
    }
    @Override
    protected void onResume() {
        super.onResume();


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admin_Feedback.this, FeedBackView.class);
                startActivity(i1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feedbacklist = new ArrayList<>();
                feedbackIDs = new ArrayList<>();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Feedback feedback = ds.getValue(Feedback.class);
                    feedbacklist.add( new Feedback( feedback.getUname() ,feedback.getFeedback() ) );
                    feedbackIDs.add( ds.getKey() );

                }
                AdminFeedbackAdapter adapter = new AdminFeedbackAdapter(Admin_Feedback.this , feedbacklist ,  feedbackIDs);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}