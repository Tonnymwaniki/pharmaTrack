package com.tonny.pharmatrack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tonny.pharmatrack.data.UserDatabase
import com.tonny.pharmatrack.repository.UserRepository
import com.tonny.pharmatrack.ui.screens.about.AboutScreen
import com.tonny.pharmatrack.ui.screens.auth.LoginScreen
import com.tonny.pharmatrack.ui.screens.auth.RegisterScreen
import com.tonny.pharmatrack.ui.screens.dashboard.DashboardScreen
import com.tonny.pharmatrack.ui.screens.home.HomeScreen
import com.tonny.pharmatrack.ui.screens.orders.OrdersScreen
import com.tonny.pharmatrack.ui.screens.splash.SplashScreen
import com.tonny.pharmatrack.ui.screens.start.StartScreen
import com.tonny.pharmatrack.ui.screens.suppliers.SuppliersScreen
import com.tonny.pharmatrack.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_DASHBOARD ,


    ) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUT_ORDERS) {
            OrdersScreen(navController)
        }

        composable(ROUT_SUPPLIERS) {
            SuppliersScreen(navController)
        }

        composable(ROUT_START) {
            StartScreen(navController)
        }

        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }









        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }







        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController, {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            })
        }








    }
}

@Composable
fun SamanthaScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}
