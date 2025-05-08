package com.tonny.pharmatrack.repository


import android.content.Context
import com.tonny.pharmatrack.data.MedicineDatabase
import com.tonny.pharmatrack.model.Medicine

class MedicineRepository(context: Context) {
    private val medicineDao = MedicineDatabase.getDatabase(context).medicineDao()

    suspend fun insertMedicine(medicine: Medicine) {
        medicineDao.insertMedicine(medicine)
    }

    fun getAllmedicine() = medicineDao.getAllMedicines()

    suspend fun deleteMedicine(medicine: Medicine) = medicineDao.deleteMedicine(medicine)
}