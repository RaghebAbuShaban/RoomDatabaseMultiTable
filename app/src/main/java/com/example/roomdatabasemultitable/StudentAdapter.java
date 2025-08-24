package com.example.roomdatabasemultitable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) { this.students = students; }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student s = students.get(position);
        holder.tvName.setText(s.getName());
        holder.tvDept.setText(s.getDepartment());
        holder.tvCourseId.setText("Course ID: " + s.getCourseId());
        if (s.getDataOfBirth() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            holder.tvDOB.setText(sdf.format(s.getDataOfBirth()));
        }
        holder.ivPhoto.setImageBitmap(s.getPhoto());

    }

    @Override
    public int getItemCount() { return students.size(); }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvDept, tvDOB, tvCourseId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivStudentPhoto);
            tvName = itemView.findViewById(R.id.tvStudentName);
            tvDept = itemView.findViewById(R.id.tvStudentDept);
            tvDOB = itemView.findViewById(R.id.tvStudentDOB);
            tvCourseId = itemView.findViewById(R.id.tvCourseId);
        }
    }
}