package com.example.lab2_4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private Activity context;

    public EmployeeAdapter(Activity context, int layoutID, List<Employee> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, null, false);
        }

        Employee employee = getItem(position);

        TextView tvFullName = convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView tvPosition = convertView.findViewById(R.id.item_employee_tv_position);
        ImageView ivManager = convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent = convertView.findViewById(R.id.item_employee_ll_parent);

        if (employee != null) {
            tvFullName.setText(employee.getFullName());

            if (employee.isManager()) {
                ivManager.setVisibility(View.VISIBLE);
                tvPosition.setVisibility(View.GONE);
            } else {
                ivManager.setVisibility(View.GONE);
                tvPosition.setVisibility(View.VISIBLE);
            }
        }

        // Đổi màu nền cho 2 dòng liên tiếp
        if (position % 2 == 0) {
            llParent.setBackgroundResource(android.R.color.white);
        } else {
            llParent.setBackgroundResource(android.R.color.darker_gray);
        }

        return convertView;
    }
}
