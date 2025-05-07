package com.tonny.pharmatrack.ui.screens.inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


data class Medicine(val name: String, val stock: Int, val price: Double)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(navController: NavController){


    var searchQuery by remember { mutableStateOf("") }

    val medicines = remember {
        listOf(
            Medicine("Paracetamol", 20, 1.99),
            Medicine("Ibuprofen", 5, 3.50),
            Medicine("Cetirizine", 2, 2.75),
            Medicine("Metformin", 50, 4.20),
            Medicine("Amoxicillin", 8, 5.99),
            Medicine("Aspirin", 0, 1.20),
            Medicine("Ibuprofen", 15, 2.90),
            Medicine("Antacid", 30, 2.40),
            Medicine("Cough Syrup", 12, 3.15),
            Medicine("Vitamin C", 25, 1.50),
            Medicine("Fluoxetine", 18, 4.80),
            Medicine("Penicillin", 10, 6.10),
            Medicine("Paracetamol Extra", 5, 2.60),
            Medicine("Prednisolone", 2, 7.00)
        )
    }

    val filtered = medicines.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PharmaTrack Inventory", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1565C0)) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Inventory") },
                    label = { Text("Inventory") },
                    selected = true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = {}
                )
            }
        },
        containerColor = Color(0xFFF3F7FF)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search medicines...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Text(
                "Available Medicines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            filtered.forEach { medicine ->
                MedicineCard(medicine)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine) {
    val cardColor = if (medicine.stock == 0) Color(0xFFFFEBEE) else Color.White
    val stockLabelColor = when {
        medicine.stock == 0 -> Color.Red
        medicine.stock < 10 -> Color(0xFFFF9800)
        else -> Color(0xFF4CAF50)
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF42A5F5), Color(0xFF1E88E5))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = medicine.name.first().toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(medicine.name, fontWeight = FontWeight.Bold)
                Text("Stock: ${medicine.stock}", style = MaterialTheme.typography.bodySmall)
                Text("Price: $${medicine.price}", style = MaterialTheme.typography.bodySmall)
            }

            if (medicine.stock < 10) {
                Text(
                    text = if (medicine.stock == 0) "Out" else "Low",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .background(stockLabelColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun InventoryScreenPreview(){
    InventoryScreen(navController= rememberNavController())
}