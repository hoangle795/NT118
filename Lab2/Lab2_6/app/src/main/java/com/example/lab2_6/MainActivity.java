package com.example.lab2_6;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName;
    CheckBox cbManager;
    Button btnAdd;
    RecyclerView rvEmployees;

    ArrayList<Employee> employees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        cbManager = findViewById(R.id.cb_manager);
        btnAdd = findViewById(R.id.btn_add);
        rvEmployees = findViewById(R.id.rv_employees);

        employees = new ArrayList<>();
        adapter = new EmployeeAdapter(employees);

        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String name = etName.getText().toString().trim();
                boolean manager = cbManager.isChecked();

                if (id.isEmpty() || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                employees.add(new Employee(id, name, manager));
                adapter.notifyItemInserted(employees.size() - 1);

                etId.setText("");
                etName.setText("");
                cbManager.setChecked(false);
            }
        });
    }
}
