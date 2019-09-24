package lk.sliit.finestay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class FeedbackAdapter extends ArrayAdapter {

    ArrayList<Feedback> feedbacks;
    Context context;

    public FeedbackAdapter(Activity context, ArrayList<Feedback> feedbacklist) {
        super(context, R.layout.feedback_info, feedbacklist);
        this.feedbacks = feedbacklist;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.feedback_info, parent, false);
        TextView Uname = rowView.findViewById(R.id.username);
        TextView Feedback = rowView.findViewById(R.id.feedback);
        TextView Reply = rowView.findViewById(R.id.reply);

        Uname.setText(feedbacks.get(position).getUname());
        Feedback.setText(feedbacks.get(position).getFeedback() );
        if( feedbacks.get(position).getResponse().length() > 0 ){
            Reply.setText(feedbacks.get(position).getResponse());
        }
        return  rowView;
    }
}
