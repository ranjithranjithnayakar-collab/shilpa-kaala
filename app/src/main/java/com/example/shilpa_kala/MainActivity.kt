package com.example.shilpa_kala

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.shilpa_kala.ui.theme.ShilpakalaTheme
import com.google.firebase.firestore.FirebaseFirestore

// 1. DATA CLASS DEFINITION
data class ProductModel(
    val imageUri: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = ""
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShilpakalaTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    // 2. STATE LIST
    val productList = remember { mutableStateListOf<ProductModel>() }

    // 3. FIREBASE REAL-TIME FETCHING (Updated to addSnapshotListener)
    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()

        // addSnapshotListener detects changes in Firebase and updates the UI automatically
        db.collection("products")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    productList.clear() // Clear list to prevent duplicates
                    for (document in snapshot.documents) {
                        val product = ProductModel(
                            imageUri = document.getString("imageUri") ?: "",
                            name = document.getString("name") ?: "",
                            price = document.getString("price") ?: "",
                            description = document.getString("description") ?: ""
                        )
                        productList.add(product)
                    }
                }
            }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        try {
                            val intent = Intent(context, CameraActivity::class.java)
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Camera Screen not found", Toast.LENGTH_SHORT).show()
                        }
                    },
                    icon = { Icon(Icons.Default.AddBox, contentDescription = null) },
                    label = { Text("Camera") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { Toast.makeText(context, "Orders", Toast.LENGTH_SHORT).show() },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("Orders") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show() },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFEF3C7))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // LOGO
            Surface(
                color = Color(0xFFFFEB3B),
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Shilpa Kala",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SEARCH
            OutlinedTextField(
                value = "",
                onValueChange = { },
                placeholder = { Text("Search Products") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // SELLERS ROW
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                repeat(6) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(65.dp).clip(CircleShape).background(Color.Magenta),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(35.dp))
                        }
                        Text("Seller", fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // PRODUCT LIST
            if (productList.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.Black)
                }
            }

            productList.forEach { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        if (item.imageUri.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(item.imageUri),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(8.dp))
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = item.name, fontSize = 20.sp, color = Color.Black)
                        Text(text = "₹${item.price}", fontSize = 18.sp, color = Color.Gray)
                        Text(text = item.description, fontSize = 14.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            IconButton(onClick = { }) { Icon(Icons.Default.FavoriteBorder, null) }
                            IconButton(onClick = { }) { Icon(Icons.Default.Share, null) }

                            Button(
                                onClick = { Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                            ) {
                                Text("Buy Now")
                            }
                        }
                    }
                }
            }
        }
    }
}