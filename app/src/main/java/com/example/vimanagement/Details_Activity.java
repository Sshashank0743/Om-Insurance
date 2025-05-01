package com.example.vimanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.vimanagement.Adapter.VehicleAdapter;
import com.example.vimanagement.Model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Details_Activity extends AppCompatActivity {

    private ListView vehicleListView;
    private SearchView searchView;
    private VehicleAdapter adapter;
    private SharedPref databaseHelper;
    private List<Vehicle> vehicleList;
    private List<Vehicle> filteredList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Setup toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Vehicle Details");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.search_view);
        vehicleListView = findViewById(R.id.vehicle_list);

        vehicleList = new ArrayList<>();
        filteredList = new ArrayList<>();

        databaseHelper = new SharedPref(this);
        adapter = new VehicleAdapter(this, filteredList);
        vehicleListView.setAdapter(adapter);

        // Load data from database
        loadVehicleData();

        // Check if new data needs to be added
        Intent intent = getIntent();
        if (intent.hasExtra("owner_name")) {
            String owner = intent.getStringExtra("owner_name");
            String mobile = intent.getStringExtra("mobile");
            String country = intent.getStringExtra("country");
            String vehicleNumber = intent.getStringExtra("vehicle_number");
            String insuranceStart = intent.getStringExtra("insurance_start");
            String expiryDate = intent.getStringExtra("insurance_expiry");
            String licenseExpiry = intent.getStringExtra("license_expiry");
            String payment = intent.getStringExtra("payment_amount");
            String pending = intent.getStringExtra("pending_amount");
            addVehicleToDatabase(owner, mobile, country, vehicleNumber, insuranceStart, expiryDate, licenseExpiry, payment, pending);
            loadVehicleData();
        }

        // Implement search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(vehicleList);
        } else {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleNumber().toLowerCase().contains(query.toLowerCase()) || vehicle.getLicenseExpiry().toLowerCase().contains(query)) {
                    filteredList.add(vehicle);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void addVehicleToDatabase(String owner, String mobile, String country, String vehicleNumber, String insuranceStart, String expiryDate,
                                      String licenseExpiry, String payment, String pending) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("owner", owner);
        values.put("mobile", mobile);
        values.put("country", country);
        values.put("vehicle_number", vehicleNumber);
        values.put("insurance_start", insuranceStart);
        values.put("insurance_expiry", expiryDate);
        values.put("license_expiry", licenseExpiry);
        values.put("payment_amount", payment);
        values.put("pending_amount", pending);
        db.insert("vehicles", null, values);
        db.close();
    }

    private void loadVehicleData() {
        vehicleList.clear();
        filteredList.clear();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM vehicles", null);

        if (cursor.moveToFirst()) {
            do {
                String owner = cursor.getString(1);
                String mobile = cursor.getString(2);
                String country = cursor.getString(3);
                String vehicleNumber = cursor.getString(4);
                String insuranceStart = cursor.getString(5);
                String expiryDate = cursor.getString(6);
                String licenseExpiry = cursor.getString(7);
                String payment = cursor.getString(8);
                String pending = cursor.getString(9);

                Vehicle vehicle = new Vehicle(owner, mobile, country, vehicleNumber, insuranceStart, expiryDate, licenseExpiry, payment, pending);
                vehicleList.add(vehicle);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Copy full list to filteredList initially
        filteredList.addAll(vehicleList);

        adapter.notifyDataSetChanged(); // Update UI
    }
}
