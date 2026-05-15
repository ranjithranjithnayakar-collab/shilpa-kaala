Shilpa-Kala (Self-Employment)
Shilpa-Kala is an Android-based e-commerce platform designed to empower self-employed artisans and local creators. The app allows users to showcase, manage, and sell their handmade products directly through a streamlined mobile interface, bridging the gap between traditional craftsmanship and the digital marketplace.
🚀 Overview
The application is built using modern Android development practices, focusing on a clean UI/UX and seamless integration with cloud services. It provides a complete workflow from capturing a product photo to listing it live on the marketplace.
✨ Features
Dynamic Home Marketplace: A visually appealing home screen featuring a curated list of sellers and a product feed with details like price, description, and "Buy Now" options.
In-App Camera Integration: A custom camera interface allows sellers to take high-quality photos of their products without leaving the app.
Product Preview & Localization: After capturing an image, sellers can preview it with automated tagging (e.g., "Handmade in Karnataka") to emphasize the product's origin.
Streamlined Upload Workflow: A multi-step form to input product names, pricing, and descriptions.
Cloud Integration: Real-time data storage and synchronization using Firebase Cloud Firestore.
Modern UI: Built entirely with Jetpack Compose, ensuring a responsive and declarative user interface.
🛠️ Tech Stack
Language: Kotlin
UI Framework: Jetpack Compose
Backend/Database: Firebase Cloud Firestore
Architecture: MVVM (Model-View-ViewModel) logic
Camera API: CameraX (for custom camera implementation)
Image Loading: Coil (implied for rendering Firestore image URIs)
📱 App Workflow
Home Screen: Users browse products and view registered sellers.
Capture: The seller clicks the "Camera" tab to photograph a new item.
Preview: The captured image is displayed for review with "Handmade" branding.
Details: The seller enters the Product Name, Price, and Description.
Confirmation: A final summary is shown before the data is pushed to the cloud.
Firestore Update: The product details and image reference are stored in the products collection.
🗄️ Database Structure (Firestore)
The app utilizes a flat collection structure for efficiency:
Collection: products
name: String (e.g., "NARASIMHA GOD STATU")
price: String/Number (e.g., "800")
description: String (e.g., "GREAT WOOD")
imageUri: String (Internal content URI or Firebase Storage URL)
📸 Screenshots
Home Screen	Camera Interface	Product Upload
![alt text](https://via.placeholder.com/150)
![alt text](https://via.placeholder.com/150)
![alt text](https://via.placeholder.com/150)
(Note: Replace placeholders with actual image links if hosting this on GitHub)
🛠️ Setup & Installation
Clone the repository.
Open the project in Android Studio (Ladybug/Koala or newer).
Connect your Firebase project and download the google-services.json file.
Place google-services.json in the /app directory.
Sync Project with Gradle Files.
Run the app on a physical device or emulator (Pixel 7 API 34 recommended).
🔮 Future Enhancements
User Authentication: Secure login for individual sellers and buyers.
Payment Gateway: Integration of UPI/Stripe for direct in-app purchases.
Chat System: Real-time communication between buyers and self-employed sellers.
Location Services: To show "Handmade" products based on the user's current region.
Developed by: [Your Name/Team]
Empowering local artisans through technology.
