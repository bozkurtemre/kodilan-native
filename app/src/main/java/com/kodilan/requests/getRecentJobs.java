package com.kodilan.requests;

import com.kodilan.models.Data;
import java.util.ArrayList;

public class getRecentJobs {

    public int current_page;
    public ArrayList<Data> data;
    public String first_page_url;
    public int from;
    public int last_page;
    public String last_page_url;
    public String next_page_url;
    public String path;
    public String per_page;
    public Object prev_page_url;
    public int to;
    public int total;

    public getRecentJobs(int current_page, ArrayList<Data> data, String first_page_url, int from, int last_page, String last_page_url, String next_page_url, String path, String per_page, Object prev_page_url, int to, int total) {
        this.current_page = current_page;
        this.data = data;
        this.first_page_url = first_page_url;
        this.from = from;
        this.last_page = last_page;
        this.last_page_url = last_page_url;
        this.next_page_url = next_page_url;
        this.path = path;
        this.per_page = per_page;
        this.prev_page_url = prev_page_url;
        this.to = to;
        this.total = total;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public int getLast_page() {
        return last_page;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public String getPath() {
        return path;
    }

    public String getPer_page() {
        return per_page;
    }

    public Object getPrev_page_url() {
        return prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public int getTotal() {
        return total;
    }
}
