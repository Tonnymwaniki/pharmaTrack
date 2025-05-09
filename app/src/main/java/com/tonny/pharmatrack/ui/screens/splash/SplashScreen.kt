package com.tonny.pharmatrack.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.R
import com.tonny.pharmatrack.navigation.ROUT_HOME
import com.tonny.pharmatrack.navigation.ROUT_LOGIN
import com.tonny.pharmatrack.navigation.ROUT_START
import com.tonny.pharmatrack.ui.theme.newbrown1
import com.tonny.pharmatrack.ui.theme.newgrey
import com.tonny.pharmatrack.ui.theme.newgrey2
import com.tonny.pharmatrack.ui.theme.newgrey4
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){


    //navigation

    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        delay(4000)
        navController.navigate(ROUT_HOME)

    }
    //end of navigation

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = newgrey4),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center




    ) {

        Image(
            painter = painterResource(R.drawable.cart3),
            contentDescription = "home",
            modifier = Modifier.size(300.dp),


        )
        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "24 PHARMATRACK.RU",
            fontSize = 30.sp,
            color = Color(0xFF74E708),
            fontWeight = FontWeight.Bold
        )
    }


}




@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController= rememberNavController())
}