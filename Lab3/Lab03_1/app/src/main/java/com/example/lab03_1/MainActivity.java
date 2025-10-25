package com.example.lab03_1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Button btnAdd;
    ListView lvContacts;
    DatabaseHandler db;
    ArrayList<Contact> contactList;
    ArrayAdapter<String> adapter;
    ArrayList<String> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        lvContacts = findViewById(R.id.lvContacts);

        db = new DatabaseHandler(this);
        contactList = new ArrayList<>();
        displayList = new ArrayList<>();

        loadContacts();

        // Thêm contact
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addContact(new Contact(name, phone));
            Toast.makeText(this, "Đã thêm liên hệ!", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etPhone.setText("");
            loadContacts();
        });

        // Click để sửa contact
        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = contactList.get(position);
            showEditDialog(contact);
        });

        // Giữ lâu để xóa contact
        lvContacts.setOnItemLongClickListener((parent, view, position, id) -> {
            Contact contact = contactList.get(position);
            db.deleteContact(contact);
            Toast.makeText(this, "Đã xóa " + contact.getName(), Toast.LENGTH_SHORT).show();
            loadContacts();
            return true;
        });
    }

    private void loadContacts() {
        contactList.clear();
        displayList.clear();

        List<Contact> contacts = db.getAllContacts();
        contactList.addAll(contacts);

        for (Contact c : contacts) {
            displayList.add(c.getName() + " - " + c.getPhoneNumber());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        lvContacts.setAdapter(adapter);
    }

    private void showEditDialog(Contact contact) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Sửa liên hệ");

        final EditText inputName = new EditText(this);
        final EditText inputPhone = new EditText(this);
        inputName.setHint("Tên");
        inputPhone.setHint("Số điện thoại");
        inputName.setText(contact.getName());
        inputPhone.setText(contact.getPhoneNumber());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(inputName);
        layout.addView(inputPhone);
        layout.setPadding(50, 20, 50, 0);
        dialog.setView(layout);

        dialog.setPositiveButton("Lưu", (d, which) -> {
            contact.setName(inputName.getText().toString());
            contact.setPhoneNumber(inputPhone.getText().toString());
            db.updateContact(contact);
            Toast.makeText(this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
            loadContacts();
        });

        dialog.setNegativeButton("Hủy", null);
        dialog.show();
    }
}
