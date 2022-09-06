package com.example.myapplication;

import static com.example.myapplication.R.string.entered_information_cant_be_empty;
import static com.example.myapplication.R.string.not_same;
import static com.example.myapplication.R.string.wrong_password;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityUpdateTeacherBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.FileSave;
import com.example.myapplication.main.Teacher;

public class UpdateTeacherActivity extends AppCompatActivity {
    TextView info;
    Button button;
    EditText new_Password_1, new_Password_2, old_Password;
    String entered_old_Password, entered_new_Password_1, entered_new_Password_2;
    Teacher teacher;

    public void init() {
        ActivityUpdateTeacherBinding binding = ActivityUpdateTeacherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        old_Password = binding.oldPasswordUpdate;
        new_Password_1 = binding.newPassword1Update;
        new_Password_2 = binding.newPassword2Update;
        info = binding.updateInfo;
        button = binding.changerBtnUpdate;
        teacher = Data.d.teacher.get(LoginActivity.index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        new_Password_1.setVisibility(View.GONE);
        new_Password_2.setVisibility(View.GONE);
        button.setEnabled(false);
    }

    public void findClickUpdate(View v) {
        entered_old_Password = old_Password.getText().toString().replaceAll("\\s+", "");
        if (entered_old_Password.equals(teacher.password)) {
            new_Password_1.setVisibility(View.VISIBLE);
            new_Password_2.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            old_Password.setEnabled(false);
            return;
        }
        info.setText(wrong_password);
    }

    public void changeClickUpdate(View v) {
        entered_new_Password_1 = new_Password_1.getText().toString().replaceAll("\\s+", "");
        entered_new_Password_2 = new_Password_2.getText().toString().replaceAll("\\s+", "");

        if (TextUtils.isEmpty(entered_new_Password_1) || TextUtils.isEmpty(entered_new_Password_2)) {
            info.setText(entered_information_cant_be_empty);
            return;
        }

        if (entered_new_Password_1.equals(entered_new_Password_2)) {
            teacher.password = entered_new_Password_1;
            FileSave.saveFile();
            finish();
            return;
        }
        info.setText(not_same);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}