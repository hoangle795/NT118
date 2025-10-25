package com.example.lab03_2;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvMssv, tvBirth, tvMajor, tvClass;
    Button btnEdit, btnBack;
    DatabaseHelper db;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.tvName);
        tvMssv = findViewById(R.id.tvMssv);
        tvBirth = findViewById(R.id.tvBirth);
        tvMajor = findViewById(R.id.tvMajor);
        tvClass = findViewById(R.id.tvClass);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);

        db = new DatabaseHelper(this);

        // Nhận dữ liệu từ Intent
        studentId = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");
        String mssv = getIntent().getStringExtra("mssv");
        String birth = getIntent().getStringExtra("birth");
        String major = getIntent().getStringExtra("major");
        String className = getIntent().getStringExtra("className");

        // Hiển thị thông tin
        tvName.setText("Họ tên: " + name);
        tvMssv.setText("MSSV: " + mssv);
        tvBirth.setText("Năm sinh: " + birth);
        tvMajor.setText("Ngành học: " + major);
        tvClass.setText("Lớp học: " + className);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nút sửa thông tin
        btnEdit.setOnClickListener(v -> {
            showEditDialog(name, mssv, birth, major, className);
        });
    }

    private void showEditDialog(String name, String mssv, String birth, String major, String className) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Sửa thông tin sinh viên");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 10);

        EditText etName = new EditText(this);
        etName.setHint("Họ tên");
        etName.setText(name);

        EditText etMssv = new EditText(this);
        etMssv.setHint("MSSV");
        etMssv.setText(mssv);

        EditText etBirth = new EditText(this);
        etBirth.setHint("Năm sinh");
        etBirth.setText(birth);

        EditText etMajor = new EditText(this);
        etMajor.setHint("Ngành học");
        etMajor.setText(major);

        EditText etClass = new EditText(this);
        etClass.setHint("Lớp học");
        etClass.setText(className);

        layout.addView(etName);
        layout.addView(etMssv);
        layout.addView(etBirth);
        layout.addView(etMajor);
        layout.addView(etClass);

        b.setView(layout);

        b.setPositiveButton("Lưu", (dialog, which) -> {
            Student s = new Student(studentId,
                    etName.getText().toString(),
                    etMssv.getText().toString(),
                    etBirth.getText().toString(),
                    etMajor.getText().toString(),
                    etClass.getText().toString());
            db.updateStudent(s);

            tvName.setText("Họ tên: " + s.getName());
            tvMssv.setText("MSSV: " + s.getMssv());
            tvBirth.setText("Năm sinh: " + s.getBirthYear());
            tvMajor.setText("Ngành học: " + s.getMajor());
            tvClass.setText("Lớp học: " + s.getClassName());

            Toast.makeText(this, "Đã cập nhật thông tin!", Toast.LENGTH_SHORT).show();
        });

        b.setNegativeButton("Hủy", null);
        b.show();
    }
}

