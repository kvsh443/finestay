package lk.sliit.finestay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class AdminFeedbackAdapter extends ArrayAdapter {

    ArrayList<Feedback> feedbacks;
    Context context;
    ArrayList<String> feedbackIDs;

    public AdminFeedbackAdapter(Activity context, ArrayList<Feedback> feedbacklist ,ArrayList<String> feedbackIDs) {
        super(context, R.layout.admin_feedback, feedbacklist);
        this.feedbacks = feedbacklist;
        this.context = context;
        this.feedbackIDs = feedbackIDs;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.admin_feedback , parent, false);
        TextView Uname = rowView.findViewById(R.id.username);
        TextView Feedback = rowView.findViewById(R.id.feedback);
        Button reply = rowView.findViewById(R.id.button);

        Uname.setText(feedbacks.get(position).getUname());
        Feedback.setText(feedbacks.get(position).getFeedback() );
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Admins_Reply.class);
                intent.putExtra( "FeedbackData" , feedbacks.get(position) );
                intent.putExtra( "FeedbackID" , feedbackIDs.get(position));
                context.startActivity(intent);
            }
        });
        return  rowView;
    }
}
