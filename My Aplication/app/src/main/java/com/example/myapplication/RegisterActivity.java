package com.example.myapplication;

import static com.example.myapplication.R.string.are_you_sure;
import static com.example.myapplication.R.string.entered_information_cant_be_empty;
import static com.example.myapplication.R.string.invalid_password;
import static com.example.myapplication.R.string.minimum_5_characters;
import static com.example.myapplication.R.string.same_username;
import static com.example.myapplication.R.string.save;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityRegisterBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.FileSave;
import com.example.myapplication.main.MyIntent;
import com.example.myapplication.main.Teacher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText nameRegister, surnameRegister, usernameRegister, passwordRegister;
    TextView info;
    String name, surname, username, password;

    public void init() {
        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        nameRegister = binding.nameRegister;
        surnameRegister = binding.surnameRegister;
        usernameRegister = binding.usernameRegister;
        passwordRegister = binding.passwordRegister;
        info = binding.registerInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void createClick(View v) {
        name = nameRegister.getText().toString().replaceAll("\\s+", "");
        surname = surnameRegister.getText().toString().replaceAll("\\s+", "");
        username = usernameRegister.getText().toString().replaceAll("\\s+", "");
        password = passwordRegister.getText().toString().replaceAll("\\s+", "");

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            info.setText(entered_information_cant_be_empty);
            return;
        }
        if (username.length() < 5) {
            info.setText(minimum_5_characters);
            return;
        }
        if (!isValidPassword(password)) {
            info.setText(invalid_password);
            return;
        }
        Teacher teacher;
        for (int i = 0; i < Data.d.teacher.size(); i++) {
            teacher = Data.d.teacher.get(i);
            if (username.equals(teacher.username)) {
                info.setText(same_username);
                return;
            }
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(save);
        alert.setMessage(are_you_sure);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_menu);
        alert.setPositiveButton("Yes", (dialog, which) -> {
            Data.d.teacher.add(new Teacher(name, surname, username, password));
            FileSave.saveFile();
            MyIntent.intentFinish_Top(this, LoginActivity.class);
        });
        alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@!?*#$%^&/_+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null)
            return false;
        Matcher m = p.matcher(password);
        return m.matches();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}