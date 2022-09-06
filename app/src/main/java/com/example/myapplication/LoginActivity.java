package com.example.myapplication;

import static com.example.myapplication.R.string.entered_information_cant_be_empty;
import static com.example.myapplication.R.string.hide;
import static com.example.myapplication.R.string.show;
import static com.example.myapplication.R.string.wrong_password;
import static com.example.myapplication.R.string.wrong_username;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.MyIntent;
import com.example.myapplication.main.Teacher;

public class LoginActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    TextView info;
    CheckBox checkBox;
    String username, password;
    public static int index;

    public void init() {
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        usernameLogin = binding.usernameLogin;
        passwordLogin = binding.passwordLogin;
        info = binding.loginInfo;
        checkBox = binding.checkBox;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        checkbox();
    }

    public void checkbox() {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox.setText(hide);
                passwordLogin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                checkBox.setText(show);
                passwordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordLogin.setSelection(passwordLogin.getText().length());
        });
        usernameLogin.post(() -> usernameLogin.requestFocus());
    }

    public void forgotClick(View v) {
        MyIntent.intent(this, ForgotPasswordActivity.class);
    }

    public void enterClick(View v) {
        username = usernameLogin.getText().toString().replaceAll("\\s+", "");
        password = passwordLogin.getText().toString().replaceAll("\\s+", "");
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            info.setText(entered_information_cant_be_empty);
            return;
        }
        Teacher teacher;
        for (int i = 0; i < Data.d.teacher.size(); i++) {
            teacher = Data.d.teacher.get(i);
            if (username.equals(teacher.username)) {
                if (password.equals(teacher.password)) {
                    index = i;
                    MyIntent.intentFinish_Top(this, AccountActivity.class);
                    return;
                }
                info.setText(wrong_password);
                return;
            }
        }
        info.setText(wrong_username);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}