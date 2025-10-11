package com.example.lab2_1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvSelection = findViewById(R.id.tv_selection);
        final ListView lvNames = findViewById(R.id.lv_names);

        final String[] names = new String[]{"Tèo", "Tý", "Bin", "Bo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_list_item,
                R.id.tv_item_name,
                names
        );

        lvNames.setAdapter(adapter);

        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = names[position];

                tvSelection.setText("position: " + position + ", value = " + selectedValue);
            }
        });
    }
}