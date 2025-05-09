package com.example.vimanagement.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.example.vimanagement.Model.Vehicle;
import com.example.vimanagement.R;

import java.util.List;

public class VehicleAdapter extends BaseAdapter {
    private Context context;
    private List<Vehicle> vehicleList;

    public VehicleAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @Override
    public int getCount() {
        return vehicleList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.vehicle_item, parent, false);
        }

        TextView marqueeText =  convertView.findViewById(R.id.marquee_text);
        TextView ownerName = convertView.findViewById(R.id.owner_name);
        TextView mobile = convertView.findViewById(R.id.mobile);
        TextView vehicleNumber = convertView.findViewById(R.id.vehicle_number);
        TextView insuranceStart = convertView.findViewById(R.id.insurance_start);
        TextView insuranceExpiry = convertView.findViewById(R.id.insurance_expiry);
        TextView licenseExpiry = convertView.findViewById(R.id.lincense_expiry);
        TextView payment = convertView.findViewById(R.id.payment_amount);
        TextView pending = convertView.findViewById(R.id.pending_amount);

        // Ensure Marquee Effect Works
        marqueeText.setSelected(true);

        // Apply Gradient Only If Text Is Not Empty
        if (marqueeText.getText() != null && !marqueeText.getText().toString().isEmpty()) {
            Shader textShader = new LinearGradient(0, 0, marqueeText.getPaint().measureText(marqueeText.getText().toString()), marqueeText.getTextSize(),
                    new int[]{Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.RED},
                    null, Shader.TileMode.CLAMP);
            marqueeText.getPaint().setShader(textShader);
        }

        Vehicle vehicle = vehicleList.get(position);
        ownerName.setText(vehicle.getOwnerName());
        mobile.setText(vehicle.getMobileNumber());
        vehicleNumber.setText(vehicle.getVehicleNumber());
        payment.setText(vehicle.getPaymentAmount());
        pending.setText(vehicle.getPendingAmount());
        insuranceStart.setText(vehicle.getInsuranceStart());
        insuranceExpiry.setText(vehicle.getInsuranceExpiry());
        licenseExpiry.setText(vehicle.getLicenseExpiry());

        return convertView;
    }
}
