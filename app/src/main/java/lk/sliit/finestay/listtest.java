package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listtest extends AppCompatActivity {

    Button bt;

    private final String CHANNEL_ID = "ppp";
    private final int NOTIFICATION_ID =001;

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Meal_Appetizer meal_appetizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtest);

        bt = findViewById(R.id.btn);

        /*bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "jhsfds ";
                NotificationManagerCompat.Builder builder = new NotificationCompat.Builder(
                        listtest.this
                )
                        .setSmallIcon(R.drawable.ic_sms_noti)
                        .setContent(msg)
                        .setAutoCancel(true);

                Intent intent = new Intent(listtest.this,registration_page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("dsfsdsdf",msg);

                PendingIntent pendingIntent;
                pendingIntent = new PendingIntent.getActivity(listtest.this,
                        requestCode:0,)
                        //getActivity(listtest.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            }
        });*/

       /* meal_appetizer = new Meal_Appetizer();

        listView = (ListView)findViewById(R.id.listView1);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Meal_Appetizer");
        list = new ArrayList<>();

      ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1);




*/
        // adapter = new ArrayAdapter<String>(this,R.layout.meal_info_update,R.id.mealInfoupdate, list);

/*
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    meal_appetizer = ds.getValue(Meal_Appetizer.class);
                    list.add(meal_appetizer.getAppertizer_name().toString() +"  " +meal_appetizer.getAprice() + "\n"  );

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent updateDelete = new Intent(listtest.this,test_list_update.class);
               // Meal_Appetizer meal_appetizer = (Meal_Appetizer)adapterView.getItemAtPosition(i);
                //updateDelete.putExtra("appertizer_name")


            }
        });
*/

    }



}