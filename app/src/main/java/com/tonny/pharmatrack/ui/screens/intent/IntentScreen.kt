package com.tonny.pharmatrack.ui.screens.intent

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.navigation.ROUT_HOME
import com.tonny.pharmatrack.navigation.ROUT_SUPPLIERS
import com.tonny.pharmatrack.ui.theme.newbrown
import com.tonny.pharmatrack.ui.theme.newbrown1
import com.tonny.pharmatrack.ui.theme.newgrey
import com.tonny.pharmatrack.ui.theme.newgrey2
import com.tonny.pharmatrack.ui.theme.newgrey4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentScreen(navController: NavController){


    Column (modifier = Modifier.fillMaxSize()){

        val mContext = LocalContext.current

        //TopAppBar
        TopAppBar(
            title = { Text(text = "Intents") },
            colors = TopAppBarDefaults.topAppBarColors(
                navigationIconContentColor = newgrey,
                containerColor = newgrey4,
                titleContentColor = newbrown1,
                actionIconContentColor = newgrey2

            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(ROUT_HOME)
                }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "menu")
                }

            },
            actions = {
                IconButton(onClick = {
                    val shareIntent=Intent(Intent.ACTION_SEND)
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
                }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                }
                IconButton(onClick = {
                    navController.navigate(ROUT_SUPPLIERS)
                }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "settings")
                }

            }

        )

        //End of TopAppBar

        Spacer(modifier = Modifier.height(20.dp))

        //STK
        Button(onClick = {
            val simToolKitLaunchIntent =
                mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { mContext.startActivity(it) }


        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "MPESA")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Calls
        Button(onClick = {

            val callIntent=Intent(Intent.ACTION_DIAL)
            callIntent.data="tel:0757036970".toUri()
            mContext.startActivity(callIntent)

        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "CALL")
        }
        //End ot call
        Spacer(modifier = Modifier.height(20.dp))

        //email
        Button(onClick = {

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("chulabaraka12148@gmail.com"))
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
            mContext.startActivity(shareIntent)

        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "EMAIL")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //share
        Button(onClick = {

            val shareIntent=Intent(Intent.ACTION_SEND)
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
            mContext.startActivity(Intent.createChooser(shareIntent, "Share"))

        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "SHARE")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //SMS
        Button(onClick = {

            val smsIntent=Intent(Intent.ACTION_SENDTO)
            smsIntent.data="smsto:0757036970".toUri()
            smsIntent.putExtra("sms_body","Hello Chula,how was your day?")
            mContext.startActivity(smsIntent)

        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "SMS")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //CAMERA
        Button(onClick = {

            val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
                mContext.startActivity(cameraIntent)
            }else{
                println("Camera app is not available")
            }

        },
            colors = ButtonDefaults.buttonColors(newgrey4),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "CAMERA")
        }






    }


}

@Preview(showBackground = true)
@Composable
fun IntentScreenPreview(){
    IntentScreen(navController= rememberNavController())
}