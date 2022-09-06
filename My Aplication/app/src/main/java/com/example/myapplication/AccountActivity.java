package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityAccountBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.MyIntent;
import com.example.myapplication.main.Teacher;

public class AccountActivity extends AppCompatActivity {
    Teacher teacher;
    TextView info;
    public static String menuName;

    public void init() {
        ActivityAccountBinding binding = ActivityAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        info = binding.userInfo;
        teacher = Data.d.teacher.get(LoginActivity.index);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        info.setText(teacher.name + " " + teacher.surname);
    }

    @SuppressLint("NonConstantResourceId")
    public void accountClick(View v) {
        switch (v.getId()) {
            case R.id.addStudent:
                menuName = "add";
                MyIntent.intent(this, AddStudentActivity.class);
                break;
            case R.id.removeStudent:
                menuName = "remove";
                MyIntent.intent(this, FindStudentActivity.class);
                break;
            case R.id.editStudent:
                menuName = "edit";
                MyIntent.intent(this, FindStudentActivity.class);
                break;
            case R.id.findStudents:
                menuName = "find";
                MyIntent.intent(this, FindStudentActivity.class);
                break;
            case R.id.showAllStudents:
                menuName = "show";
                MyIntent.intent(this, FindStudentActivity.class);
                break;
            case R.id.updatePassword:
                MyIntent.intent(this, UpdateTeacherActivity.class);
                break;
            case R.id.deleteAccount:
                MyIntent.intent(this, DeleteTeacherActivity.class);
                break;
            case R.id.exit:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}