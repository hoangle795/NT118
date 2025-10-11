package com.example.lab2_3;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName;
    RadioGroup rgType;
    Button btnAdd;
    ListView lvEmployees;

    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        rgType = findViewById(R.id.rg_type);
        btnAdd = findViewById(R.id.btn_add);
        lvEmployees = findViewById(R.id.lv_employees);

        employees = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                employees);

        lvEmployees.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String name = etName.getText().toString().trim();

                if (id.isEmpty() || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = rgType.getCheckedRadioButtonId();
                Employee emp;
                if (selectedId == R.id.rd_fulltime) {
                    emp = new EmployeeFullTime(id, name);
                } else {
                    emp = new EmployeePartTime(id, name);
                }

                employees.add(emp);
                adapter.notifyDataSetChanged();

                etId.setText("");
                etName.setText("");
                rgType.clearCheck();
            }
        });
    }
}