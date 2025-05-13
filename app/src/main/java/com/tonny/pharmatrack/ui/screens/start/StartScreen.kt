package com.tonny.pharmatrack.ui.screens.start

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.navigation.ROUT_HOME
import com.tonny.pharmatrack.navigation.ROUT_LOGIN

@Composable
fun StartScreen(navController: NavController){


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF062CAF)) // Blue background
    ) {
        // Pills/Medicine background (mocked with circles)
        MedicineBackground()

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bringing The",
                fontSize = 28.sp,
                color = Color.White
            )
            Text(
                text = "Best Medicine",
                fontSize = 28.sp,
                color = Color(0xFFFFC107),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "at Your Door",
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Navigate to next screen, e.g., "home" or "medicines"
                    navController.navigate(ROUT_HOME)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF57A90E))
            ) {
                Text(text = "Get Started", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Skip",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    // Navigate directly to home or login
                    navController.navigate(ROUT_HOME)
                }
            )
        }
    }
}

@Composable
fun MedicineBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val iconSize = 24.dp.toPx()
        for (i in 0..30) {
            val x = (0..size.width.toInt()).random().toFloat()
            val y = (0..size.height.toInt()).random().toFloat()
            drawCircle(
                color = Color.Blue.copy(alpha = 0.15f),
                radius = iconSize / 2,
                center = Offset(x, y)
            )
        }
    }
}






@Preview(showBackground = true)
@Composable
fun StartScreenPreview(){
    StartScreen(navController= rememberNavController())
}