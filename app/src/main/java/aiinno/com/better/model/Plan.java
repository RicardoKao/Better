package aiinno.com.better.model;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by lbk on 2016/10/30.
 */
public class Plan {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYourdeclear() {
        return yourdeclear;
    }

    public void setYourdeclear(String yourdeclear) {
        this.yourdeclear = yourdeclear;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getComplated_days() {
        return complated_days;
    }

    public void setComplated_days(int complated_days) {
        this.complated_days = complated_days;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    String name;
    String title;
    String yourdeclear;
    ArrayList<String> rules;
    int days;
    int complated_days;
    int fee;
    String img;
    String start;
    ArrayList<String> tags;
}
