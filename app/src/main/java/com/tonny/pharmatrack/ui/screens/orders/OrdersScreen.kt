package com.tonny.pharmatrack.ui.screens.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.navigation.ROUT_DASHBOARD
import com.tonny.pharmatrack.navigation.ROUT_HOME
import com.tonny.pharmatrack.navigation.ROUT_INVENTORY
import com.tonny.pharmatrack.ui.theme.newgrey


// --- DATA CLASS ---
data class PharmaOrder(
    val medicineName: String,
    val quantity: String,
    val status: String
)


@Composable
fun OrdersScreen(navController: NavController){
    var selectedTab by remember { mutableStateOf(1) }
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = newgrey) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0
                        navController.navigate(ROUT_HOME) },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1
                        navController.navigate(ROUT_INVENTORY) },
                    icon = { Icon(Icons.Filled.List, contentDescription = "Inventory") },
                    label = { Text("Inventory") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2
                        navController.navigate(ROUT_DASHBOARD)},
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> OrdersScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

// --- ORDERS SCREEN ---
@Composable
fun OrdersScreen() {
    val allOrders = listOf(
        PharmaOrder("Paracetamol", "2 Boxes", "Delivered"),
        PharmaOrder("Amoxicillin", "1 Pack", "Pending"),
        PharmaOrder("Ibuprofen", "5 Bottles", "Shipped"),
        PharmaOrder("Aspirin", "10 Tablets", "Delivered"),
        PharmaOrder("Cough Syrup", "3 Bottles", "Pending"),
        PharmaOrder("Antacid", "2 Packs", "Shipped"),
        PharmaOrder("Vitamin C", "1 Bottle", "Delivered"),
        PharmaOrder("Insulin", "5 Pens", "Pending"),

        PharmaOrder("Amoxilin", "3 Bottles", "Shipped"),
        PharmaOrder("Panadol", "6 Packs", "Pending"),
      PharmaOrder("Vitamin D", "1 Bottle", "Delivered"),
      PharmaOrder("Lexyolin", "10 Pens", "Pending")
    )

    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Pending", "Delivered", "Shipped")

    val filteredOrders = allOrders.filter {
        selectedFilter == "All" || it.status.equals(selectedFilter, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Filter Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable Order List
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (filteredOrders.isEmpty()) {
                Text("No orders found for \"$selectedFilter\"")
            } else {
                filteredOrders.forEach { order ->
                    OrderCard(order)
                }
            }
        }
    }
}


// --- ORDER CARD COMPONENT ---
@Composable
fun OrderCard(order: PharmaOrder) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = order.medicineName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Quantity: ${order.quantity}")
            Text(text = "Status: ${order.status}", color = getStatusColor(order.status))
        }
    }
}

// --- STATUS COLOR UTILITY ---
fun getStatusColor(status: String): Color {
    return when (status) {
        "Delivered" -> Color(0xFF2E7D32)
        "Pending" -> Color(0xFFF57C00)
        "Shipped" -> Color(0xFF1976D2)
        else -> Color.Gray
    }
}

// --- OTHER TABS ---
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Welcome to Pharma App", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineSmall)
    }
}




















@Preview(showBackground = true)
@Composable
fun OrdersScreenPreview(){
    OrdersScreen(navController= rememberNavController())
}