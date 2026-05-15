package com.example.shilpa_kala

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore

class UploadActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUri = intent.getStringExtra("imageUri")

        setContent {
            UploadScreen(imageUri)
        }
    }
}

@Composable
fun UploadScreen(imageUri: String?) {

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Upload Product",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it),
                contentDescription = null,
                modifier = Modifier.height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                // ✅ Firebase Firestore
                val db = FirebaseFirestore.getInstance()

                val product = hashMapOf(
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "imageUri" to imageUri
                )

                db.collection("products")
                    .add(product)
                    .addOnSuccessListener {

                        Toast.makeText(
                            context,
                            "Saved to Firebase ✅",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(context, FinalActivity::class.java)

                        intent.putExtra("imageUri", imageUri)
                        intent.putExtra("name", name)
                        intent.putExtra("price", price)
                        intent.putExtra("description", description)

                        context.startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Failed ❌",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        ) {
            Text("Upload")
        }
    }
}