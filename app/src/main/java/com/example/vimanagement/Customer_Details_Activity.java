package com.example.vimanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import java.util.Calendar;

public class Customer_Details_Activity extends AppCompatActivity {
    private EditText ownerName, mobileNumber, vehicleNumber, paymentAmount, pendingAmount, insurance_start, insuranceExpiry, licenseExpiry;
    private AppCompatButton saveButton, viewDetailsButton;
    private TextView countryCode;
    private SharedPref databaseHelper;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        // Initialize views
        ownerName = findViewById(R.id.owner_name);
        mobileNumber = findViewById(R.id.mobile_number);
        countryCode = findViewById(R.id.country_code);
        vehicleNumber = findViewById(R.id.vehicle_number);
        insurance_start = findViewById(R.id.insurance_start);
        insuranceExpiry = findViewById(R.id.insurance_expiry);
        licenseExpiry = findViewById(R.id.license_expiry);
        paymentAmount = findViewById(R.id.payment_amount);
        pendingAmount = findViewById(R.id.pending_amount);
        saveButton = findViewById(R.id.btn_Submit);
        viewDetailsButton = findViewById(R.id.btn_view_details);
        // Setup toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Vehicle Details");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // Initialize database helper
        databaseHelper = new SharedPref(this);
        // Set date picker dialogs for insurance dates
        insurance_start.setOnClickListener(v -> showDatePicker(insurance_start));
        insuranceExpiry.setOnClickListener(v -> showDatePicker(insuranceExpiry));
        licenseExpiry.setOnClickListener(v -> showDatePicker(licenseExpiry));
        // Set onClickListeners for buttons
        saveButton.setOnClickListener(v -> saveVehicleDetails());
        viewDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Customer_Details_Activity.this, Details_Activity.class);
            intent.putExtra("owner_name", ownerName.getText().toString().trim());
            intent.putExtra("mobile", mobileNumber.getText().toString().trim());
            intent.putExtra("vehicle_number", vehicleNumber.getText().toString().trim());
            intent.putExtra("insurance_start", insurance_start.getText().toString().trim());
            intent.putExtra("insurance_expiry", insuranceExpiry.getText().toString().trim());
            intent.putExtra("license_expiry", licenseExpiry.getText().toString().trim());
            intent.putExtra("payment_amount", paymentAmount.getText().toString().trim());
            intent.putExtra("pending_amount", pendingAmount.getText().toString().trim());
            startActivity(intent);
        });
    }

    private void showDatePicker(EditText targetField) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            targetField.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void saveVehicleDetails() {
        String name = ownerName.getText().toString().trim();
        String mobile = mobileNumber.getText().toString().trim();
        String number = vehicleNumber.getText().toString().trim();
        String start = insurance_start.getText().toString().trim();
        String expiry = insuranceExpiry.getText().toString().trim();
        String l_expiry = licenseExpiry.getText().toString().trim();
        String payment = paymentAmount.getText().toString().trim();
        String pending = pendingAmount.getText().toString().trim();
        if (name.isEmpty() || mobile.isEmpty() || number.isEmpty() || expiry.isEmpty() || l_expiry.isEmpty() || payment.isEmpty() || pending.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean inserted = databaseHelper.insertVehicle(name, mobile, number, start, expiry, l_expiry, payment, pending);
        if (inserted) {
            Toast.makeText(this, "Vehicle details saved", Toast.LENGTH_SHORT).show();
            ownerName.setText("");
            mobileNumber.setText("");
            vehicleNumber.setText("");
            insurance_start.setText("");
            insuranceExpiry.setText("");
            licenseExpiry.setText("");
            paymentAmount.setText("");
            pendingAmount.setText("");
        } else {
            Toast.makeText(this, "Failed to save vehicle details", Toast.LENGTH_SHORT).show();
        }
    }
}