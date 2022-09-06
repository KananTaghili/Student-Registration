package com.example.myapplication;

import static com.example.myapplication.R.string.accept_changes;
import static com.example.myapplication.R.string.add_student;
import static com.example.myapplication.R.string.age_must_be_number;
import static com.example.myapplication.R.string.create;
import static com.example.myapplication.R.string.edit_student;
import static com.example.myapplication.R.string.entered_information_cant_be_empty;
import static com.example.myapplication.R.string.student_added;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityAddStudentBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.FileSave;
import com.example.myapplication.main.Student;
import com.example.myapplication.main.Teacher;

import java.util.InputMismatchException;

public class AddStudentActivity extends AppCompatActivity {
    Button button;
    EditText entered_name, entered_surname, entered_className, entered_age;
    TextView info, title;
    Teacher teacher;
    Student student;
    String name, surname, ageSt, className;

    public void init() {
        ActivityAddStudentBinding binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        entered_name = binding.nameAdd;
        entered_surname = binding.surnameAdd;
        entered_age = binding.ageAdd;
        entered_className = binding.classNameAdd;
        info = binding.addInfo;
        title = binding.addProcess;
        button = binding.createBtnAdd;
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
            case "add":
                title.setText(add_student);
                button.setText(create);
                break;
            case "edit":
                student = teacher.student.get(FindStudentActivity.index);
                title.setText(edit_student);
                button.setText(accept_changes);
                entered_name.setText(student.name);
                entered_surname.setText(student.surname);
                entered_age.setText(String.valueOf(student.age));
                entered_className.setText(student.className);
                break;
        }
    }

    public void createClickAdd(View v) {
        try {
            name = entered_name.getText().toString().replaceAll("\\s+", "");
            surname = entered_surname.getText().toString().replaceAll("\\s+", "");
            ageSt = entered_age.getText().toString().replaceAll("\\s+", "");
            className = entered_className.getText().toString().replaceAll("\\s+", "");

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(ageSt) || TextUtils.isEmpty(className))
                throw new IllegalArgumentException();
            Integer age = Integer.valueOf(ageSt);
            if (AccountActivity.menuName.equals("edit")) {
                student.setData(name, surname, age, className);
            } else if (AccountActivity.menuName.equals("add")) {
                Student newStudent = new Student();
                newStudent.setData(name, surname, age, className);
                teacher.student.add(newStudent);
                info.setText(student_added);
            }
            FileSave.saveFile();
            finish();
        } catch (InputMismatchException | NumberFormatException ex) {
            info.setText(age_must_be_number);
        } catch (IllegalArgumentException ex) {
            info.setText(entered_information_cant_be_empty);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

