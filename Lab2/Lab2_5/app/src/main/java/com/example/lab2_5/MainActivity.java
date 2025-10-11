package com.example.lab2_5;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Spinner spThumbnail;
    CheckBox cbPromo;
    Button btnAdd;
    GridView gvDishes;

    ArrayList<Dish> dishes;
    DishAdapter dishAdapter;

    ArrayList<Thumbnail> thumbnails;
    ThumbnailAdapter thumbnailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        spThumbnail = findViewById(R.id.sp_thumbnail);
        cbPromo = findViewById(R.id.cb_promo);
        btnAdd = findViewById(R.id.btn_add);
        gvDishes = findViewById(R.id.gv_dishes);

        // 1. Danh sách thumbnail cho Spinner
        thumbnails = new ArrayList<>();
        thumbnails.add(new Thumbnail(R.drawable.food1, "Món 1"));
        thumbnails.add(new Thumbnail(R.drawable.food2, "Món 2"));
        thumbnails.add(new Thumbnail(R.drawable.food3, "Món 3"));
        thumbnails.add(new Thumbnail(R.drawable.food4, "Món 4"));

        thumbnailAdapter = new ThumbnailAdapter(this, thumbnails);
        spThumbnail.setAdapter(thumbnailAdapter);

        // 2. Danh sách món ăn cho GridView
        dishes = new ArrayList<>();
        dishAdapter = new DishAdapter(this, R.layout.item_dish, dishes);
        gvDishes.setAdapter(dishAdapter);

        // 3. Xử lý nút Thêm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                boolean promo = cbPromo.isChecked();
                int pos = spThumbnail.getSelectedItemPosition();

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên món ăn!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Thumbnail selectedThumb = thumbnails.get(pos);
                Dish dish = new Dish(name, selectedThumb.getImageRes(), promo);
                dishes.add(dish);
                dishAdapter.notifyDataSetChanged();

                // Reset input
                etName.setText("");
                cbPromo.setChecked(false);
                spThumbnail.setSelection(0);
            }
        });
    }
}
