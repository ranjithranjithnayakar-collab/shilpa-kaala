package com.example.shilpa_kala

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUri = intent.getStringExtra("imageUri")
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("description")

        setContent {
            FinalScreen(imageUri, name, price, description)
        }
    }
}

@Composable
fun FinalScreen(
    imageUri: String?,
    name: String?,
    price: String?,
    description: String?
) {
    val context = LocalContext.current
    // State to show a loading spinner while uploading
    var isUploading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // --- IMAGE PREVIEW ---
        imageUri?.let {
            val inputStream = context.contentResolver.openInputStream(android.net.Uri.parse(it))
            val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)

            bitmap?.let { bmp ->
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- TEXT DETAILS ---
        Text(text = "Product: $name", fontSize = 20.sp)
        Text(text = "Price: ₹$price", fontSize = 18.sp)
        Text(text = "Description: $description", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(30.dp))

        // --- 1. UPLOAD BUTTON (NEW) ---
        if (isUploading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Button(
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Green Color
                onClick = {
                    isUploading = true
                    val db = FirebaseFirestore.getInstance()

                    // Prepare data
                    val product = hashMapOf(
                        "imageUri" to (imageUri ?: ""),
                        "name" to (name ?: ""),
                        "price" to (price ?: ""),
                        "description" to (description ?: "")
                    )

                    // Upload to Firebase
                    db.collection("products")
                        .add(product)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Uploaded Successfully!", Toast.LENGTH_SHORT).show()

                            // GO BACK TO MAIN ACTIVITY (Home Page)
                            val intent = Intent(context, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(intent)

                            (context as android.app.Activity).finish()
                        }
                        .addOnFailureListener {
                            isUploading = false
                            Toast.makeText(context, "Upload Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            ) {
                Text("Confirm & Upload Product", color = Color.White, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // --- 2. BACK BUTTON (Kept for editing/going back) ---
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            onClick = {
                (context as android.app.Activity).finish()
            }
        ) {
            Text("Back to Home")
        }
    }
}