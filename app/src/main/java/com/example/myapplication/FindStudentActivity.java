package com.example.myapplication;

import static com.example.myapplication.R.string.edit_student;
import static com.example.myapplication.R.string.find_students;
import static com.example.myapplication.R.string.no_student_found;
import static com.example.myapplication.R.string.remove_student;
import static com.example.myapplication.R.string.show;
import static com.example.myapplication.R.string.show_all_students;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityFindStudentBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.Student;
import com.example.myapplication.main.StudentAdapter;
import com.example.myapplication.main.Teacher;

import java.util.ArrayList;

public class FindStudentActivity extends AppCompatActivity {
    EditText textFind;
    Button button;
    TextView info, title;
    Teacher teacher;
    String text;
    ArrayList<Student> student;
    public static int index;
    StudentAdapter StudentAdapter;
    RecyclerView recView;

    public void init() {
        ActivityFindStudentBinding binding = ActivityFindStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        info = binding.findInfo;
        title = binding.findStudentProcess;
        button = binding.findBtnFind;
        textFind = binding.textFind;
        recView = binding.recyclerView;

        recView.setLayoutManager(new LinearLayoutManager(this));

        teacher = Data.d.teacher.get(LoginActivity.index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        menuSwitch();
    }

    public void menuSwitch() {
        switch (AccountActivity.menuName) {
            case "show":
                title.setText(show_all_students);
                button.setText(show);
                textFind.setVisibility(View.GONE);
                text = " ";
                break;
            case "edit":
                title.setText(edit_student);
                break;
            case "remove":
                title.setText(remove_student);
                break;
            case "find":
                title.setText(find_students);
                break;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void findClickFind(View v) {
        info.setText("");
        if (!AccountActivity.menuName.equals("show"))
            text = textFind.getText().toString().replaceAll("\\s+", "");

        if (TextUtils.isEmpty(text)) {
            recView.setAdapter(null);
            return;
        }
        student = findStudent(text);
        if (student.size() == 0) {
            info.setText(no_student_found);
            recView.setAdapter(null);
            return;
        }
        StudentAdapter = new StudentAdapter(student);
        recView.setAdapter(StudentAdapter);
        //StudentAdapter.notifyDataSetChanged();
    }

    public ArrayList<Student> findStudent(String text) {
        ArrayList<Student> studentArray = new ArrayList<>();
        Student student;
        for (int i = 0; i < teacher.student.size(); i++) {
            student = teacher.student.get(i);
            if (student.name.toLowerCase().contains(text.toLowerCase())
                    || student.surname.toLowerCase().contains(text.toLowerCase())
                    || student.className.toLowerCase().contains(text.toLowerCase())
                    || student.id.contains(text)) {
                studentArray.add(teacher.student.get(i));
            }
        }
        return studentArray;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}