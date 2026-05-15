package com.example.shilpa_kala

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class PreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get URI from the intent
        val imageUriString = intent.getStringExtra("imageUri")

        if (imageUriString == null) {
            Toast.makeText(this, "Error: Image not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            PreviewScreen(imageUriString)
        }
    }
}

@Composable
fun PreviewScreen(imageUri: String) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // 1. SHOW THE CAPTURED IMAGE
        AsyncImage(
            model = imageUri,
            contentDescription = "Captured Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit // Shows full photo without cropping
        )

        // 2. TOP BAR (Back and Delete)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // BACK BUTTON
            IconButton(
                onClick = { (context as Activity).finish() },
                modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), androidx.compose.foundation.shape.CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }

            // DELETE BUTTON
            IconButton(
                onClick = {
                    try {
                        val uri = Uri.parse(imageUri)
                        context.contentResolver.delete(uri, null, null)
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                        (context as Activity).finish()
                    } catch (e: Exception) {
                        Log.e("Preview", "Delete Error: ${e.message}")
                        Toast.makeText(context, "Could not delete", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.background(Color.Red.copy(alpha = 0.7f), androidx.compose.foundation.shape.CircleShape)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
            }
        }

        // 3. BOTTOM CAPTION & NEXT BUTTON
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Handmade in Karnataka",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val intent = Intent(context, UploadActivity::class.java)
                    intent.putExtra("imageUri", imageUri)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green color
            ) {
                Icon(Icons.Default.Done, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("PROCEED TO UPLOAD", fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}