package com.example.lab2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnAdd;
    TextView tvSelection;
    ListView lvNames;

    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        btnAdd = findViewById(R.id.btn_add);
        tvSelection = findViewById(R.id.tv_selection);
        lvNames = findViewById(R.id.lv_names);

        // 1. Tạo ArrayList
        names = new ArrayList<>();

        // 2. Tạo Adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                names);

        // 3. Gắn Adapter cho ListView
        lvNames.setAdapter(adapter);

        // 4. Xử lý nút Nhập
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString().trim();
                if (!input.isEmpty()) {
                    names.add(input);
                    adapter.notifyDataSetChanged(); // cập nhật lại listview
                    etInput.setText("");
                }
            }
        });

        // 5. Chọn item trong ListView
        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvSelection.setText("position " + position + " | value " + names.get(position));
            }
        });

        // 6. Xóa item khi nhấn giữ lâu
        lvNames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                names.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}