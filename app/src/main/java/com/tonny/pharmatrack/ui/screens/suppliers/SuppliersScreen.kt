package com.tonny.pharmatrack.ui.screens.suppliers

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.R
import com.tonny.pharmatrack.navigation.ROUT_HOME
import com.tonny.pharmatrack.ui.theme.newbrown1
import com.tonny.pharmatrack.ui.theme.newgrey







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuppliersScreen(navController: NavController){

    val mContext = LocalContext.current
    var selectedIndex by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Suppliers") },
                navigationIcon = {
                    IconButton(onClick = { /* Back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = newgrey,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = newgrey) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Search") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Check") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = Color.LightGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF5F6FA))
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = { Text("Search...") }
            )

            Image(
                painter = painterResource(R.drawable.cart1),
                contentDescription = "home",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Services available",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrescriptionCard("Dr. Max Carrel", "12.07.2024", Color(0xFFB490F0))
                    PrescriptionCard("Dr. Mike Brown", "10.07.2024", Color(0xFF6FD59D))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrescriptionCard("Dr. Anne Lee", "14.07.2024", Color(0xFFFFC107))
                    PrescriptionCard("Dr. Samuel Kim", "09.07.2024", Color(0xFF03A9F4))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Orders",
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderItem(
                pharmacyName = "Pharmacy ABC, Harry St. 10",
                amount = "167.90 $",
                status = "Processed",
                products = "2 products"
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderItem(
                pharmacyName = "ALPHA Farmacy, Tyler St. 24",
                amount = "156.20 $",
                status = "Delivered",
                products = "Paracetamol, Amoxicillin..."
            )

            Spacer(modifier = Modifier.height(24.dp))

            OrderItem(
                pharmacyName = "MedicPro, Baker St. 5",
                amount = "88.45 $",
                status = "Pending",
                products = "Ibuprofen, Vitamin D"
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderItem(
                pharmacyName = "HealWell Pharmacy, Queen St. 19",
                amount = "199.00 $",
                status = "In Transit",
                products = "Antibiotics, Cough Syrup..."
            )

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total amount", style = MaterialTheme.typography.titleMedium)
                Text("156.20 $", style = MaterialTheme.typography.titleMedium)
            }
        }
    }




}

@Composable
fun PrescriptionCard(name: String, date: String, bgColor: Color) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .background(bgColor, shape = RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Text(name, color = Color.White, fontWeight = FontWeight.Bold)
        Text(date, color = Color.White.copy(alpha = 0.8f))
    }
}

@Composable
fun OrderItem(pharmacyName: String, amount: String, status: String, products: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(status, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            Text(pharmacyName, fontWeight = FontWeight.Bold)
            Text(products, color = Color.DarkGray)
            Text(amount, color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SuppliersScreenPreview(){
    SuppliersScreen(navController= rememberNavController())
}

