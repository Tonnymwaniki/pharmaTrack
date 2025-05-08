package com.tonny.pharmatrack.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.tonny.pharmatrack.model.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicine")
    fun getAllMedicines(): LiveData<List<Medicine>>

    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)
}