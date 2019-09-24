package lk.sliit.finestay;

import java.io.Serializable;

public class Feedback implements Serializable {

  String uname;
  String feedback;
  String response;

    public Feedback() {
    }

    public Feedback(String uname, String feedback ) {
        this.uname = uname;
        this.feedback = feedback;
    }

    public Feedback(String uname, String feedback, String response) {
        this.uname = uname;
        this.feedback = feedback;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
