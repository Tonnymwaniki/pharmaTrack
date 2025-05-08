package com.tonny.pharmatrack.viewmodel


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tonny.pharmatrack.data.MedicineDatabase
import com.tonny.pharmatrack.model.Medicine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MedicineViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val MedicineDao = MedicineDatabase.getDatabase(app).medicineDao()

    val allMedicine: LiveData<List<Medicine>> = MedicineDao.getAllMedicines()

    fun addProduct(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newMedicine = Medicine(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            MedicineDao.insertMedicine(newMedicine)
        }
    }

    fun updateMedicine(updatedMedicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            MedicineDao.updateMedicine(updatedMedicine)
        }
    }

    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(medicine.imagePath)
            MedicineDao.deleteMedicine(medicine)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}