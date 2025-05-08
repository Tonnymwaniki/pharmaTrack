package com.tonny.pharmatrack.navigation


const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_CONTACT = "contact"

//auth
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"

const val ROUT_SPLASH = "Splash"
const val ROUT_ORDERS = "Orders"
const val ROUT_SUPPLIERS = "Suppliers"
const val ROUT_START = "Start"
const val ROUT_DASHBOARD = "Start"
const val ROUT_INVENTORY = "inventory"

const val ROUT_ADD_MEDICINE = "add_product"
const val ROUT_MEDICINE_LIST = "product_list"
const val ROUT_EDIT_MEDICINE = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editMedicineRoute(medicineId: Int) = "edit_medicine/$medicineId"

