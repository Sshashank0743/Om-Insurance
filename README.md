# 🚗 Om-Insurance – Personal Vehicle Insurance Manager

A lightweight Android application to manage and track your personal vehicle insurance records — built for private use, with no dependency on cloud servers or online databases.

---

## 📱 App Purpose

**Om-Insurance** helps you manage your own vehicle insurance details easily, all from your mobile phone. No online login or hosting required — all data is stored locally.

---

## ✨ Key Features

- 🧾 **Add Vehicle Details** – Enter vehicle name, number, model, and insurance info
- ⏰ **Track Expiry Dates** – Quick overview of upcoming policy expirations
- 🖼️ **Upload Documents** – Option to save images of policy documents locally
- 🧑‍💼 **Simple UI** – Clean and minimal interface using native Android components
- 🔒 **Offline-Only** – All data is stored on the device (SQLite)

---

## 🔧 Technologies Used

- **Java** – Core application logic
- **XML** – UI layouts and design
- **SQLite** – Local data storage
- **Android SDK** – For building and running the app

---

## 📂 Project Structure
````
Om-Insurance/
├── app/
│ ├── java/com/om/insurance/
│ │ ├── MainActivity.java
│ │ ├── AddVehicleActivity.java
│ │ └── DatabaseHelper.java
│ └── res/layout/
│ ├── activity_main.xml
│ ├── activity_add_vehicle.xml
│ └── list_item.xml
└── README.md
````