# Hasiru App - Firebase Backend & Integration Plan

This document outlines the components and integration steps required to connect the Hasiru app to **Google Firebase**.

## 1. Firebase Core Services

### Firebase Authentication
*   **Method**: Email/Password authentication.
*   **Features**: Handled by Firebase SDK (Sign up, Login, Password Reset emails).
*   **Integration**: Use `FirebaseAuth.getInstance()` to manage user sessions.

### Cloud Firestore (NoSQL Database)
*   **`users` collection**: 
    *   Document ID: `uid` (from Auth)
    *   Fields: `displayName`, `email`, `photoUrl`, `totalPlantations`, `createdAt`.
*   **`plants` collection**:
    *   Fields: `uid` (owner), `commonName`, `scientificName`, `latitude`, `longitude`, `status` (Alive/Dead/Unknown), `imageUrl`, `timestamp`.
*   **`growth_updates` sub-collection** (inside each plant document):
    *   Fields: `status`, `note`, `imageUrl`, `timestamp`.
*   **`badges` collection**:
    *   Fields: `name`, `iconKey`, `description`, `isAchieved`.

### Firebase Storage
*   **Purpose**: Storing high-resolution photos of plants and profile pictures.
*   **Structure**: `/users/{uid}/profile.jpg` and `/plants/{plantId}/growth_1.jpg`.

## 2. Android Integration Layers

### Firebase SDKs
*   Add the following to `app/build.gradle.kts`:
    *   `firebase-auth-ktx`: For user management.
    *   `firebase-firestore-ktx`: For plant and profile data.
    *   `firebase-storage-ktx`: For image uploads.

### Data Management
*   **Repositories**: 
    *   `AuthRepository`: Wraps `FirebaseAuth` for login/register logic.
    *   `PlantRepository`: Uses `FirebaseFirestore` to fetch/save plants.
*   **ViewModels**: 
    *   Observe Firestore `Snapshots` for real-time UI updates (e.g., when a plant status changes, the map updates automatically).

### Image Handling
*   **Coil**: Still used to load images from the `imageUrl` strings provided by Firebase Storage.

## 3. Screen-Specific Integration Points

Each screen in the app will need specific Firebase logic:

*   **Login & ForgotPassword Screens**:
    *   Connect `email` and `password` fields to `FirebaseAuth.signInWithEmailAndPassword()`.
    *   Use `FirebaseAuth.sendPasswordResetEmail()` for the password recovery flow.
*   **HomeScreen**:
    *   Query the `plants` collection for the "Latest Entries" list.
    *   Listen to real-time updates so new trees appear instantly.
*   **MapScreen**:
    *   Fetch all `plants` documents to plot markers (latitude/longitude) on the map.
*   **NewPlantScreen**:
    *   Upload the taken photo to **Firebase Storage** first.
    *   Save the resulting `downloadUrl` along with plant details (name, location) into a new **Firestore** document.
*   **PlantDetailScreen**:
    *   Fetch specific `plants/{id}` data and its `growth_updates` sub-collection.
    *   Allow users to post new updates (status change + optional photo).
*   **ProfileScreen**:
    *   Fetch user-specific stats (total plantations) from the `users` collection.
    *   Toggle `achieved` status for badges based on user progress.

## 4. Security Rules
*   **Firestore**: Restrict `write` access so only the owner of a plant can update its status.
*   **Storage**: Restrict `write` access to the user's own folder.

## 4. Next Steps
1.  Create a project in the [Firebase Console](https://console.firebase.google.com/).
2.  Register the Android app using package name `com.example.hasiru`.
3.  Download `google-services.json` and place it in the `app/` folder.
4.  Add Firebase BOM and dependencies to `build.gradle.kts`.
5.  Enable **Authentication (Email/Password)** and **Firestore** in the console.
