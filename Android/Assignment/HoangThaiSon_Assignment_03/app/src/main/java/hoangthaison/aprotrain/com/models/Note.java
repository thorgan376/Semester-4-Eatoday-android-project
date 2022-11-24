package hoangthaison.aprotrain.com.models;

import java.util.concurrent.atomic.AtomicInteger;
import android.text.TextUtils;

public class Note {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String note;
    private String time;

    public Note() {
        this.id = 0;
        this.note = "NULL";
        this.time = "Unknow time";
    }

    public Note(String note, String time) {
        this.id = count.incrementAndGet();
        this.note = note;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return TextUtils.isEmpty(note) ? "" : note;
    }

    public void setNote(String note) {
        if(TextUtils.isEmpty(note)) {
            return;
        }
        this.note = note;
    }

    public String getTime() {
        return TextUtils.isEmpty(time) ? "" : time;
    }

    public void setTime(String time) {
        if(TextUtils.isEmpty(time )) {
            return;
        }
        this.time = time;
    }
}
