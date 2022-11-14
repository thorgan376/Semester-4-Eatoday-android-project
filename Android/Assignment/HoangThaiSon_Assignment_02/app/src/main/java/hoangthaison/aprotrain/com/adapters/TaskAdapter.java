package hoangthaison.aprotrain.com.adapters;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hoangthaison.aprotrain.com.R;
import hoangthaison.aprotrain.com.models.MyTask;

public class TaskAdapter extends BaseAdapter {
    private List<MyTask> tasks;

    public TaskAdapter (List<MyTask> allTask) {
        this.tasks = allTask;
    }
    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        }
        TextView tvItemTitle = view.findViewById(R.id.tv_item_title);
        TextView tvItemDescription = view.findViewById(R.id.tv_item_description);
        MyTask taskItem = tasks.get(i);

        tvItemTitle.setText(taskItem.getTaskTitle());
        tvItemDescription.setText(taskItem.getTaskContent());
        tvItemTitle.setTextColor(taskItem.isImportant() ?
                Color.parseColor("#FF0000") :
                Color.parseColor("#000000"));
        tvItemDescription.setTextColor(taskItem.isImportant() ?
                Color.parseColor("#FF0000") :
                Color.parseColor("#000000"));
        return view;
    }
}
