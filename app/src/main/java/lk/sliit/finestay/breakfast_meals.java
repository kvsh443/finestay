package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class breakfast_meals extends AppCompatActivity {

    ListView listView1,listView2,listView3,listView4;
    FirebaseDatabase database;
    DatabaseReference dbRef1,dbRef2,dbRef3,dbRef4;
    ArrayList<String> list1,list2,list3,list4;
    ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4;

    Meal_Appetizer meal_appetizer;
    Meal_Rice meal_rice;
    Meal_Noodles meal_noodles;
    Meal_Beverages meal_beverages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_meals);
        meal_appetizer = new Meal_Appetizer();
        meal_rice = new Meal_Rice();
        meal_noodles = new Meal_Noodles();
        meal_beverages = new Meal_Beverages();

        listView1 = (ListView)findViewById(R.id.listView1);
        listView2 = (ListView)findViewById(R.id.listView2);
        listView3 = (ListView)findViewById(R.id.listView3);
        listView4 = (ListView)findViewById(R.id.listView4);

        database = FirebaseDatabase.getInstance();
        dbRef1 = database.getReference("Meal_Appetizer");
        dbRef2 = database.getReference("Meal_Rice");
        dbRef3 = database.getReference("Meal_Noodles");
        dbRef4 = database.getReference("Meal_Beverages");

        list1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealInfoupdate, list1);
        adapter1 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealprice, list1);

        //Appetizers
        dbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    meal_appetizer = ds.getValue(Meal_Appetizer.class);
                    list1.add(meal_appetizer.getAppertizer_name().toString() + "  " + meal_appetizer.getAprice()  +"\n" );
                }
                listView1.setAdapter(adapter1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Rice
        list2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealInfoupdate, list2);
        adapter2 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealprice, list2);
         dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    meal_rice = ds.getValue(Meal_Rice.class);
                    list2.add(meal_rice.getRice_name().toString() + "  " + meal_rice.getBprice()  +"\n" );
                }
                listView2.setAdapter(adapter2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //noodles
        list3 = new ArrayList<>();
        adapter3 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealInfoupdate, list3);
        adapter3 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealprice, list3);
        dbRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    meal_noodles = ds.getValue(Meal_Noodles.class);
                    list3.add(meal_noodles.getNoodle_name().toString() + "  " + meal_noodles.getCprice()  +"\n" );
                }
                listView3.setAdapter(adapter3);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //beverages
        list4 = new ArrayList<>();
        adapter4 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealInfoupdate, list4);
        adapter4 = new ArrayAdapter<String>(this,R.layout.meal_info,R.id.mealprice, list4);
        dbRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    meal_beverages = ds.getValue(Meal_Beverages.class);
                    list4.add(meal_beverages.getBeverage_name().toString() + "  " + meal_beverages.getDprice()  +"\n" );
                }
                listView4.setAdapter(adapter4);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}