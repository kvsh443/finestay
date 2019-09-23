package lk.sliit.finestay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import lk.sliit.finestay.Model.Reservation;

public class CustomListAdapter extends ArrayAdapter<Reservation> {
    private Context context;
    private List<Reservation> reservationsList = new ArrayList<>();

    public CustomListAdapter(@NonNull Context context, ArrayList<Reservation> list){
        super(context,0, list);
        this.context = context;
        this.reservationsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(this.context).inflate(R.layout.list_items,parent,false);
        }
        Reservation currentReservation = reservationsList.get(position);

        TextView textViewCustomerID = listItem.findViewById(R.id.textViewCustomerID);
        TextView textViewCheckInDate = listItem.findViewById(R.id.textViewCheckInDate);
        TextView textViewCheckOutDate = listItem.findViewById(R.id.textViewCheckOutDate);
        TextView textViewMeal = listItem.findViewById(R.id.textViewMeal2);
        TextView textViewTotal = listItem.findViewById(R.id.textViewTotal);
        TextView textViewStatus = listItem.findViewById(R.id.textViewStatus);
        TextView textViewGuests = listItem.findViewById(R.id.textViewGuests);

        textViewCustomerID.setText(currentReservation.getCustomerID());
        textViewCheckInDate.setText(currentReservation.getCheckIn());
        textViewCheckOutDate.setText(currentReservation.getCheckOut());
        textViewMeal.setText(currentReservation.getMeals());
        textViewTotal.setText(currentReservation.getTotal());
        textViewStatus.setText(currentReservation.getStatus());
        textViewGuests.setText(currentReservation.getGuestNo());

        return listItem;
    }
}
