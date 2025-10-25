package com.example.lab03_2;


import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private List<Student> list;
    private DatabaseHelper db;

    public StudentAdapter(Context context, List<Student> list, DatabaseHelper db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student s = list.get(position);
        holder.tvName.setText(s.getName());
        holder.tvMssv.setText("MSSV: " + s.getMssv());

        // ✅ Nhấn để xem chi tiết sinh viên
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, StudentDetailActivity.class);
            i.putExtra("id", s.getId());
            i.putExtra("name", s.getName());
            i.putExtra("mssv", s.getMssv());
            i.putExtra("birth", s.getBirthYear());
            i.putExtra("major", s.getMajor());
            i.putExtra("className", s.getClassName());
            context.startActivity(i);
        });

        // ✅ Giữ để xóa sinh viên
        holder.itemView.setOnLongClickListener(v -> {
            db.deleteStudent(s.getId());
            list.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xóa sinh viên!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMssv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMssv = itemView.findViewById(R.id.tvMssv);
        }
    }
}



