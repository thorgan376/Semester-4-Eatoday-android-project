package hoangthaison.aprotrain.com.students.model;

import android.text.TextUtils;

public class Student {
    private String fullName;
    private String rollNumber;

    public Student() {
        this.fullName = "Chưa có dữ liệu";
        this.rollNumber = "Chưa có dữ liệu";
    }

    public Student(String fullName, String rollNumber) {
        this.fullName = fullName;
        this.rollNumber = rollNumber;
    }

    public String getFullName() {
        return TextUtils.isEmpty(fullName) ? "FullName is null" : fullName;
    }

    public void setFullName(String fullName) {
        if(TextUtils.isEmpty(fullName)) {
            return;
        }

        this.fullName = fullName;
    }

    public String getRollNumber() {
        return TextUtils.isEmpty(rollNumber) ? "RollNumber is null" : rollNumber;
    }

    public void setRollNumber(String rollNumber) {
    /*
     if (TextUtils.isEmpty(rollNumber)) {
         return;
     }
    */
        this.rollNumber = rollNumber;
    }
}
