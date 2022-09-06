package com.example.myapplication;

import static com.example.myapplication.R.string.not_same;
import static com.example.myapplication.R.string.wrong_password;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityDeleteTeacherBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.FileSave;
import com.example.myapplication.main.MyIntent;
import com.example.myapplication.main.Teacher;

public class DeleteTeacherActivity extends AppCompatActivity {
    EditText old_Password_1, old_Password_2;
    TextView info;
    String entered_Old_Password_1, entered_Old_Password_2;
    Teacher teacher;

    public void init() {
        ActivityDeleteTeacherBinding binding = ActivityDeleteTeacherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        old_Password_1 = binding.oldPassword1;
        old_Password_2 = binding.oldPassword2;
        info = binding.deleteInfo;

        teacher = Data.d.teacher.get(LoginActivity.index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void deleteClick(View v) {
        entered_Old_Password_1 = old_Password_1.getText().toString().replaceAll("\\s+", "");
        entered_Old_Password_2 = old_Password_2.getText().toString().replaceAll("\\s+", "");

        if (entered_Old_Password_1.equals(entered_Old_Password_2)) {
            if (entered_Old_Password_1.equals(teacher.password)) {
                Data.d.teacher.remove(LoginActivity.index);
                FileSave.saveFile();
                MyIntent.intentFinish_Top(this, MainActivity.class);
                return;
            }
            info.setText(wrong_password);
            return;
        }
        info.setText(not_same);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}