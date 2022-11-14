package hoangthaison.aprotrain.com.students.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hoangthaison.aprotrain.com.R;
import hoangthaison.aprotrain.com.students.model.Student;

public class StudentAdapter extends BaseAdapter {
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student_listview, viewGroup, false);
        }
        //Bind UI controls từ giao diện xml của item student vào code java đây
        TextView tvFullName = view.findViewById(R.id.tv_full_name_item_title);
        TextView tvRollNumber = view.findViewById(R.id.tv_student_code_item);
        //Tại student(i) lấy model data tương ứng
        Student student = students.get(i);
        //Bind data - gán data vào phần tử view của item trong list view;
        tvFullName.setText(student.getFullName());
        tvRollNumber.setText(student.getRollNumber());
        return view;
    }
}
