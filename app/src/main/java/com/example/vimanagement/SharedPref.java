package com.example.vimanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.vimanagement.Model.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class SharedPref extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "VehicleDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_VEHICLES = "vehicles";
    private static final String COLUMN_ID = "id";
    private static final String KEY_EMAIL = "keyemail";
    private static final String COLUMN_OWNER_NAME = "owner_name";
    private static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    private static final String COLUMN_COUNTRY_CODE = "country_code";
    private static final String COLUMN_VEHICLE_NUMBER = "vehicle_number";
    private static final String COLUMN_INSURANCE_START = "insurance_start";
    private static final String COLUMN_INSURANCE_EXPIRY = "insurance_expiry";
    private static final String COLUMN_LICENSE_EXPIRY = "license_expiry";
    private static final String COLUMN_PAYMENT_AMOUNT = "payment_amount";
    private static final String COLUMN_PENDING_AMOUNT = "pending_amount";
    private static SharedPref mInstance;
    private static Context mCtx;

    public SharedPref(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPref(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_VEHICLES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_OWNER_NAME + " TEXT, "
                + COLUMN_MOBILE_NUMBER + " TEXT, "
                + COLUMN_COUNTRY_CODE + " TEXT, "
                + COLUMN_VEHICLE_NUMBER + " TEXT, "
                + COLUMN_INSURANCE_START + " TEXT, "
                + COLUMN_INSURANCE_EXPIRY + " TEXT, "
                + COLUMN_LICENSE_EXPIRY + " TEXT, "
                + COLUMN_PAYMENT_AMOUNT + " TEXT, "
                + COLUMN_PENDING_AMOUNT + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_VEHICLES + " ADD COLUMN " + COLUMN_PAYMENT_AMOUNT + " TEXT");
        }
    }

    public boolean insertVehicle(String ownerName, String mobileNumber, String countryCode, String vehicleNumber, String insuranceStart, String insuranceExpiry, String licenseExpiry, String paymentAmount, String pendingAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OWNER_NAME, ownerName);
        values.put(COLUMN_MOBILE_NUMBER, mobileNumber);
        values.put(COLUMN_COUNTRY_CODE, countryCode);
        values.put(COLUMN_VEHICLE_NUMBER, vehicleNumber);
        values.put(COLUMN_INSURANCE_START, insuranceStart);
        values.put(COLUMN_INSURANCE_EXPIRY, insuranceExpiry);
        values.put(COLUMN_LICENSE_EXPIRY, licenseExpiry);
        values.put(COLUMN_PAYMENT_AMOUNT, paymentAmount);
        values.put(COLUMN_PENDING_AMOUNT, pendingAmount);

        long result = db.insert(TABLE_VEHICLES, null, values);
        db.close();
        return result != -1; // Returns true if insertion is successful
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VEHICLES, null);

        if (cursor.moveToFirst()) {
            do {
                Vehicle vehicle = new Vehicle(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9)
                );
                vehicleList.add(vehicle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vehicleList;
    }

    public void deleteVehicle(String vehicleNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLES, COLUMN_VEHICLE_NUMBER + "=?", new String[]{vehicleNumber});
        db.close();
    }
}

