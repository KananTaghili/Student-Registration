package com.example.myapplication;

import static com.example.myapplication.R.string.entered_information_cant_be_empty;
import static com.example.myapplication.R.string.not_same;
import static com.example.myapplication.R.string.wrong_username;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.myapplication.databinding.ActivityForgotPasswordBinding;
import com.example.myapplication.main.Data;
import com.example.myapplication.main.Teacher;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextView info;
    EditText new_Password_1, new_Password_2, username;
    SwitchCompat visibility_switch;
    Button button;
    String entered_new_Password_1, entered_new_Password_2, entered_username;
    Teacher teacher;

    public void init() {
        ActivityForgotPasswordBinding binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        username = binding.usernameForgot;
        new_Password_1 = binding.newPassword1;
        new_Password_2 = binding.newPassword2;
        visibility_switch = binding.switchForgot;
        info = binding.forgotInfo;
        button = binding.changerBtn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        new_Password_1.setVisibility(View.GONE);
        new_Password_2.setVisibility(View.GONE);
        visibility_switch.setVisibility(View.GONE);
        button.setEnabled(false);
    }

    private void visibilitySwitch() {
        visibility_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                new_Password_1.setInputType(InputType.TYPE_CLASS_TEXT);
                new_Password_2.setVisibility(View.GONE);
            } else {
                new_Password_1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new_Password_2.setVisibility(View.VISIBLE);
            }
        });
    }

    public void findClickForgot(View v) {
        entered_username = username.getText().toString().replaceAll("\\s+", "");
        for (int i = 0; i < Data.d.teacher.size(); i++) {
            teacher = Data.d.teacher.get(i);
            if (entered_username.equals(teacher.username)) {
                new_Password_1.setVisibility(View.VISIBLE);
                new_Password_2.setVisibility(View.VISIBLE);
                visibility_switch.setVisibility(View.VISIBLE);
                button.setEnabled(true);
                username.setEnabled(false);
                visibilitySwitch();
                return;
            }
        }
        info.setText(wrong_username);
    }

    public void changeClick(View v) {
        entered_new_Password_1 = new_Password_1.getText().toString().replaceAll("\\s+", "");
        entered_new_Password_2 = new_Password_2.getText().toString().replaceAll("\\s+", "");

        if (TextUtils.isEmpty(entered_new_Password_1) || TextUtils.isEmpty(entered_new_Password_2) && !visibility_switch.isChecked()) {
            info.setText(entered_information_cant_be_empty);
            return;
        }
        if (entered_new_Password_1.equals(entered_new_Password_2) || visibility_switch.isChecked()) {
            teacher.password = entered_new_Password_1;
            finish();
        } else
            info.setText(not_same);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}