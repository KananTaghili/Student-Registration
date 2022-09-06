package com.example.myapplication.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AccountActivity;
import com.example.myapplication.AddStudentActivity;
import com.example.myapplication.FindStudentActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.databinding.RecylerRowBinding;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    ArrayList<Student> student;

    public StudentAdapter(ArrayList<Student> student) {
        this.student = student;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentHolder holder, @SuppressLint("RecyclerView") int position) {
        Teacher teacher = Data.d.teacher.get(LoginActivity.index);
        holder.binding.recyclerTextView.setText(student.get(position).toString());
        holder.itemView.setOnClickListener(v -> {
            for (int i = 0; i < teacher.student.size(); i++) {
                if (teacher.student.get(i).id.equals(student.get(position).id)) {
                    if (AccountActivity.menuName.equals("remove")) {
                        teacher.student.remove(i);
                        Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        FileSave.saveFile();
                        return;
                    } else if (AccountActivity.menuName.equals("edit")) {
                        FindStudentActivity.index = i;
                        MyIntent.intent(holder.itemView.getContext(), AddStudentActivity.class);
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecylerRowBinding binding = RecylerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StudentHolder(binding);
    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public static class StudentHolder extends RecyclerView.ViewHolder {
        private final RecylerRowBinding binding;

        public StudentHolder(RecylerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}