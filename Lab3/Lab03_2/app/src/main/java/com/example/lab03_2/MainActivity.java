package com.example.lab03_2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.*;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName, etMssv, etBirth, etMajor, etClass;
    Button btnAdd;
    RecyclerView rvStudents;
    DatabaseHelper db;
    List<Student> list;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMssv = findViewById(R.id.etMssv);
        etBirth = findViewById(R.id.etBirth);
        etMajor = findViewById(R.id.etMajor);
        etClass = findViewById(R.id.etClass);
        btnAdd = findViewById(R.id.btnAdd);
        rvStudents = findViewById(R.id.rvStudents);

        db = new DatabaseHelper(this);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        loadStudents();

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String mssv = etMssv.getText().toString().trim();
            String birth = etBirth.getText().toString().trim();
            String major = etMajor.getText().toString().trim();
            String className = etClass.getText().toString().trim();

            if (name.isEmpty() || mssv.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addStudent(new Student(name, mssv, birth, major, className));
            Toast.makeText(this, "Đã thêm sinh viên!", Toast.LENGTH_SHORT).show();
            etName.setText(""); etMssv.setText("");
            etBirth.setText(""); etMajor.setText(""); etClass.setText("");
            loadStudents();
        });
    }

    private void loadStudents() {
        list = db.getAllStudents();
        adapter = new StudentAdapter(this, list, db);
        rvStudents.setAdapter(adapter);
    }
}

