package com.example.lab2_4;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName;
    CheckBox cbManager;
    Button btnAdd;
    ListView lvEmployees;

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
        lvEmployees = findViewById(R.id.lv_employees);

        employees = new ArrayList<>();
        adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployees.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String name = etName.getText().toString().trim();
                boolean isManager = cbManager.isChecked();

                if (id.isEmpty() || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                Employee emp = new Employee(id, name, isManager);
                employees.add(emp);
                adapter.notifyDataSetChanged();

                etId.setText("");
                etName.setText("");
                cbManager.setChecked(false);
            }
        });
    }
}
