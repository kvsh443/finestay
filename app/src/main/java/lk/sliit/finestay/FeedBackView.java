package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedBackView extends AppCompatActivity {

    Button b1;
    Button b5, b3;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<Feedback> feedbacklist;
    ArrayList<String> feedbackIDs;
    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback = new Feedback();

       listView = findViewById(R.id.listView);
        b1 = findViewById(R.id.add_review);



        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Feedback");


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
                    feedback = ds.getValue(Feedback.class);
                     Feedback fed = new Feedback( feedback.getUname() ,feedback.getFeedback() );
                    if( feedback.getResponse() != null ){
                        fed.setResponse(feedback.getResponse() );
                    }else{
                        fed.setResponse("");
                    }
                    feedbacklist.add(fed);
                    feedbackIDs.add( ds.getKey() );

                }
                FeedbackAdapter adapter = new FeedbackAdapter(FeedBackView.this , feedbacklist);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(FeedBackView.this, feedback_update_delete.class);
                        intent.putExtra( "FeedbackID" , feedbackIDs.get(i) );
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(FeedBackView.this,Activity2.class);
                startActivity(i1);
            }
        });


    }
}
