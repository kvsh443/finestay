package lk.sliit.finestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lk.sliit.finestay.Model.Reservation;


public class Reservation_Manager extends AppCompatActivity {

//    TableLayout tableLayout;
    DatabaseReference databaseReference, reservationReference;
    ListView listView;
    Button buttonApprove, buttonDeny;
    ValueEventListener valueEventListener;
    CustomListAdapter customListAdapter;

    int daPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__manager);

        buttonApprove = findViewById(R.id.button6);
        buttonDeny = findViewById(R.id.button8);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        reservationReference = databaseReference.child("Reservation");
        final ArrayList<Reservation> reservationsList = new ArrayList<>();
        customListAdapter = new CustomListAdapter(Reservation_Manager.this, reservationsList);

        listView = findViewById(R.id.listView);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Reservation reservation = ds.getValue(Reservation.class);
                    reservationsList.add(reservation);
                }

                listView.setAdapter(customListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };
        reservationReference.addListenerForSingleValueEvent(valueEventListener);
//        tableLayout = findViewById(R.id.simple_table);
//        tableLayout.removeAllViewsInLayout();

//        FirebaseListOptions<Reservation> options = new FirebaseListOptions.Builder<Reservation>()
//                .setQuery(databaseReference, Reservation.class)
//                .setLayout(R.layout.activity_reservation__manager)
//                .build();
//
//        adapter = (new FirebaseListAdapter<Reservation>(options) {
//            @Override
//            protected void populateView(@NonNull View v, @NonNull Reservation reservation , int position) {
//                ((TextView)v.findViewById(android.R.id.text1)).setText(reservation.getCustomerID());
//                ((TextView)v.findViewById(android.R.id.text2)).setText(reservation.getStatus());
//                System.out.println(" tried here");
//            }
//        });
//
//
//        adapter.startListening();
//        listView.setAdapter(adapter);

//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                size = (int) dataSnapshot.getChildrenCount();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Reservation reservation = reservationsList.get(position);

                new AlertDialog.Builder(Reservation_Manager.this).setTitle("Delete Order"+reservation.getCustomerID()).setMessage("Do you want to delete selected record ?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reservationReference.child(reservation.getCustomerID()).removeValue();
                        reservationsList.remove(position);
                        customListAdapter.notifyDataSetChanged();
                        Toast.makeText(Reservation_Manager.this, "Reservation Dataset Deleted", Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                daPosition = position;

            }
        });

        buttonApprove.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Reservation reservation1 = reservationsList.get(daPosition);
                reservation1.setStatus("Approved");
                DatabaseReference updateReference = FirebaseDatabase.getInstance().getReference().child("Reservation");
                updateReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(reservation1.getCustomerID())){
                            DatabaseReference databaseWrite = FirebaseDatabase.getInstance().getReference().child("Reservation").child(reservation1.getCustomerID());
                            databaseWrite.setValue(reservation1);
                            customListAdapter.notifyDataSetChanged();
                            Toast.makeText(Reservation_Manager.this, "Order Approved Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Reservation_Manager.this, "No Source to Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        buttonDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Reservation reservation2 = reservationsList.get(daPosition);
                reservation2.setStatus("Denied");
                DatabaseReference updateReference = FirebaseDatabase.getInstance().getReference().child("Reservation");
                updateReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(reservation2.getCustomerID())){
                            DatabaseReference databaseWrite = FirebaseDatabase.getInstance().getReference().child("Reservation").child(reservation2.getCustomerID());
                            databaseWrite.setValue(reservation2);
                            customListAdapter.notifyDataSetChanged();
                            Toast.makeText(Reservation_Manager.this, "Order Denied Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Reservation_Manager.this, "No Source to Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
