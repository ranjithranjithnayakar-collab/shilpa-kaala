<img width="379" height="787" alt="Screenshot 2026-05-15 101056" src="https://github.com/user-attachments/assets/fa577823-3d32-4b3d-ad62-b2a2035a1d2f" />
Shilpa-Kala (Self-Employment)

Shilpa-Kala is an Android-based e-commerce platform designed to empower
self-employed artisans and local creators. The app allows users to showcase,
manage, and sell their handmade products directly through a streamlined mobile
interface, bridging the gap between traditional craftsmanship and the digital
marketplace.


<img width="1901" height="979" alt="Screenshot 2026-05-08 092344" src="https://github.com/user-attachments/assets/af1519a9-b298-49c7-80cd-4c85ba4ee153" />

<img width="398" height="793" alt="Screenshot 2026-05-15 100922" src="https://github.com/user-attachments/assets/0964973b-602a-4759-87f9-2edd3d18a16e" />

This is a comprehensive README.md template based on the screenshots of your
project. It highlights the features, tech stack, and workflow of your
application.

Shilpa-Kala (Self-Employment)

Shilpa-Kala is an Android-based e-commerce platform designed to empower
self-employed artisans and local creators. The app allows users to showcase,
manage, and sell their handmade products directly through a streamlined mobile
interface, bridging the gap between traditional craftsmanship and the digital
marketplace.

🚀 Overview

The application is built using modern Android development practices, focusing on
a clean UI/UX and seamless integration with cloud services. It provides a
complete workflow from capturing a product photo to listing it live on the
marketplace.

✨ Features

  - Dynamic Home Marketplace: A visually appealing home screen featuring a
    curated list of sellers and a product feed with details like price,
    description, and "Buy Now" options.
  - In-App Camera Integration: A custom camera interface allows sellers to take
    high-quality photos of their products without leaving the app.
  - Product Preview & Localization: After capturing an image, sellers can
    preview it with automated tagging (e.g., "Handmade in Karnataka") to
    emphasize the product's origin.
  - Streamlined Upload Workflow: A multi-step form to input product names,
    pricing, and descriptions.
  - Cloud Integration: Real-time data storage and synchronization using Firebase
    Cloud Firestore.
  - Modern UI: Built entirely with Jetpack Compose, ensuring a responsive and
    declarative user interface.

🛠️ Tech Stack

  - Language: Kotlin
  - UI Framework: Jetpack Compose
  - Backend/Database: Firebase Cloud Firestore
  - Architecture: MVVM (Model-View-ViewModel) logic
  - Camera API: CameraX (for custom camera implementation)
  - Image Loading: Coil (implied for rendering Firestore image URIs)

📱 App Workflow

1.  Home Screen: Users browse products and view registered sellers.
2.  Capture: The seller clicks the "Camera" tab to photograph a new item.
3.  Preview: The captured image is displayed for review with "Handmade"
    branding.
4.  Details: The seller enters the Product Name, Price, and Description.
5.  Confirmation: A final summary is shown before the data is pushed to the
    cloud.
6.  Firestore Update: The product details and image reference are stored in the
    products collection.

🗄️ Database Structure (Firestore)

The app utilizes a flat collection structure for efficiency:

  - Collection: products
      - name: String (e.g., "NARASIMHA GOD STATU")
      - price: String/Number (e.g., "800")
      - description: String (e.g., "GREAT WOOD")
      - imageUri: String (Internal content URI or Firebase Storage URL)

📸 Screenshots

| Home Screen                              | Camera Interface                           | Product Upload                             |
| :--------------------------------------: | :----------------------------------------: | :----------------------------------------: |
| ![Home](https://via.placeholder.com/150) | ![Camera](https://via.placeholder.com/150) | ![Upload](https://via.placeholder.com/150) |
<img width="429" height="806" alt="Screenshot 2026-05-15 100951" src="https://github.com/user-attachments/assets/539d8f23-8369-4a49-b8ea-0c14f4ac1fb9" />
<img width="400" height="810" alt="Screenshot 2026-05-15 101012" src="https://github.com/user-attachments/assets/69ff6902-aa2d-4c51-8a8e-379f08f9f53a" />
<img width="379" height="787" alt="Screenshot 2026-05-15 101056" src="https://github.com/user-attachments/assets/75fb7240-6940-49a5-bfda-a938545e5f3d" />
<img width="1492" height="897" alt="Screenshot 2026-05-15 102028" src="https://github.com/user-attachments/assets/596406ae-4166-4e7e-9ac8-50b679b5bf05" />
<img width="398" height="800" alt="Screenshot 2026-05-15 101115" src="https://github.com/user-attachments/assets/da84932a-9837-4731-9f0d-0811fa3fc204" />
<img width="379" height="787" alt="Screenshot 2026-05-15 101056" src="https://github.com/user-attachments/assets/75fb7240-6940-49a5-bfda-a938545e5f3d" />




(Note: Replace placeholders with actual image links if hosting this on GitHub)

🛠️ Setup & Installation

1.  Clone the repository.
2.  Open the project in Android Studio (Ladybug/Koala or newer).
3.  Connect your Firebase project and download the google-services.json file.
4.  Place google-services.json in the /app directory.
5.  Sync Project with Gradle Files.
6.  Run the app on a physical device or emulator (Pixel 7 API 34 recommended).

🔮 Future Enhancements

  - User Authentication: Secure login for individual sellers and buyers.
  - Payment Gateway: Integration of UPI/Stripe for direct in-app purchases.
  - Chat System: Real-time communication between buyers and self-employed
    sellers.
  - Location Services: To show "Handmade" products based on the user's current
    region.

Developed by: [Ranjith Nayaka R/Team] Empowering local artisans through
technology.

