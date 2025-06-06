package com.tonny.pharmatrack.ui.screens.home


import android.text.style.BackgroundColorSpan
import androidx.compose.animation.AnimatedVisibility
import com.tonny.pharmatrack.R
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.navigation.ROUT_ADD_MEDICINE
import com.tonny.pharmatrack.navigation.ROUT_DASHBOARD
import com.tonny.pharmatrack.navigation.ROUT_INVENTORY
import com.tonny.pharmatrack.ui.theme.newbrown
import com.tonny.pharmatrack.ui.theme.newgrey


@Composable
fun HomeScreen(navController: NavController){

    var showMore by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = newgrey) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {
                        navController.navigate(ROUT_DASHBOARD)
                    },
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") },
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_INVENTORY)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_ADD_MEDICINE)
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Healthy Pharmacy", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search medicine...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0745A9)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(40.dp)) {
                    Text("Offer Ends Today", fontWeight = FontWeight.Bold)
                    Text("Up to 25% off on selected items", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Categories", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(23.dp)) {
                CategoryItem("Covid Essentials")
                CategoryItem("Cold & Cough")
                CategoryItem("Baby Needs")
            }

            Spacer(modifier = Modifier.height(16.dp))



            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Recommended Products Section
                Text("Product Recommend", fontWeight = FontWeight.Bold, fontSize = 23.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ProductCard("Paracetamol", "ksh 20.000", R.drawable.paracetamol )
                    ProductCard("Cough Syrup", "ksh 24.500", R.drawable.syrup)
                }

                AnimatedVisibility(visible = showMore) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            ProductCard("Vitamin C", "ksh 15.000", R.drawable.vitamin)
                            ProductCard("Antacid Tablets", "ksh 18.000", R.drawable.antacid)
                        }
                    }
                }

                TextButton(onClick = { showMore = !showMore }) {
                    Text(if (showMore) "Show Less" else "Show More")
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard("vitamin D", "ksh 21.000", R.drawable.vitamin )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCard("Amoxil ", "ksh 20.500", R.drawable.amoxil)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth ()
            ) {
                ProductCard("levoxyl", "ksh 22.000", R.drawable.levoxyl )
                Spacer(modifier = Modifier.height(10.dp))
                ProductCard("zestril ", "ksh 23.500", R.drawable.zestril)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard("Motrin", "ksh 16.000", R.drawable.motrin )
                Spacer(modifier = Modifier.height(10.dp))
                ProductCard("Aderal ", "ksh 19.500", R.drawable.aderal)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard("Norvasc", "ksh 16.000", R.drawable.norvasc )
                Spacer(modifier = Modifier.height(10.dp))
                ProductCard("Panadol ", "ksh 19.500", R.drawable.panadol)
            }
        }
    }
}

@Composable
fun ProductCard(name: String, price: String, imageResId: Int) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D5BEE)),
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(start=20.dp, end = 20.dp , top = 20.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = name,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(price, color = Color(0xFF0833CB), fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CategoryItem(name: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF064098)),
        modifier = Modifier
            .height(40.dp)
            .wrapContentWidth()
    ) {
        Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(name, fontSize = 12.sp, color = Color.White)
        }
    }


}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController= rememberNavController())
}