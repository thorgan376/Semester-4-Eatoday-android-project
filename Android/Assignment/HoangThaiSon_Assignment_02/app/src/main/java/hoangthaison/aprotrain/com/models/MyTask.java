package hoangthaison.aprotrain.com.models;

import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class MyTask {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int taskId;
    private String taskTitle;
    private String taskContent;
    private boolean isImportant;

    public MyTask() {
        this.taskId = 0;
        this.taskTitle = "NULL";
        this.taskContent = "NULL";
        this.isImportant = false;
    }

    public MyTask(String taskTitle, String taskContent, boolean isImportant) {
        this.taskId = count.incrementAndGet();
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.isImportant = isImportant;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return TextUtils.isEmpty(taskTitle) ? "" : taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        if (TextUtils.isEmpty(taskTitle)) {
            return;
        }
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return TextUtils.isEmpty(taskContent) ? "" : taskContent;
    }

    public void setTaskContent(String taskContent) {
        if (TextUtils.isEmpty(taskContent)) {
            return;
        }
        this.taskContent = taskContent;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
