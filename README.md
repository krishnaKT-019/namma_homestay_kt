# Namma HomeStay - Host Portal

A simplified Android application designed for rural home-stay owners to manage their listings, daily menus, and traveler inquiries.

## 📸 App Preview
<p align="center">
  <img src="image_a2ea3a.jpg" width="30%" />
  <img src="image_name_2.jpg" width="30%" />
  <img src="image_name_3.jpg" width="30%" />
</p>
<p align="center">
  <img src="image_name_4.jpg" width="30%" />
  <img src="image_name_5.jpg" width="30%" />
  <img src="image_name_6.jpg" width="30%" />
</p>

## Features
- **Splash Screen**: Warm welcome with smooth animation.
- **Home Dashboard**: Quick access to all modules with summary cards.
- **My Home Profile**: Manage homestay details and a **Verification Checklist** (Cleanliness, Hygiene, etc.).
- **Daily Menu**: Update "Today's Menu" in less than a minute.
- **Inquiry Box**: View traveler messages and contact them directly via the dialer.
- **Local Guide**: Showcase nearby hidden gems (waterfalls, farms, etc.).

## Tech Stack
- **Kotlin & Jetpack Compose**: Modern UI development.
- **MVVM Architecture**: Separation of concerns.
- **Hilt**: Dependency injection.
- **Navigation Component**: Seamless screen transitions.
- **Firebase Firestore**: Real-time data storage (Implementation ready).
- **Coil**: Image loading.

## Setup Instructions

### 1. Firebase Configuration
To enable the real-time Firebase backend:
1. Create a project in the [Firebase Console](https://console.firebase.google.com/).
2. Add an Android App with package name `com.example.nammahomestay`.
3. Download `google-services.json` and place it in the `app/` directory.
4. Uncomment the `google-services` plugin in `app/build.gradle.kts`.
5. In `AppModule.kt`, switch the repository provider to `FirebaseHomestayRepository`.

### 2. Running the App
- Currently, the app uses `MockHomestayRepository` so it's fully functional for testing without Firebase.
- Simply build and run the project in Android Studio.

## Folder Structure
- `data/`: Repository implementations (Firebase & Mock).
- `domain/`: Business logic, models, and repository interfaces.
- `presentation/`: UI screens, ViewModels, and Composables.
- `navigation/`: Navigation graph and routes.
- `ui/theme/`: Custom warm/coastal color palette.
- `di/`: Hilt modules.
