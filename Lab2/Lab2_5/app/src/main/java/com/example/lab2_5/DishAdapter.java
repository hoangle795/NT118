package com.example.lab2_5;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context;
    private List<Dish> dishes;

    public DishAdapter(Activity context, int layoutID, List<Dish> dishes) {
        super(context, layoutID, dishes);
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dish, parent, false);
        }

        ImageView ivThumb = convertView.findViewById(R.id.item_dish_iv_thumb);
        TextView tvName = convertView.findViewById(R.id.item_dish_tv_name);
        ImageView ivStar = convertView.findViewById(R.id.item_dish_iv_star);

        Dish dish = dishes.get(position);
        ivThumb.setImageResource(dish.getThumbnail());
        tvName.setText(dish.getName());
        ivStar.setVisibility(dish.isPromo() ? View.VISIBLE : View.GONE);

        return convertView;
    }
}

