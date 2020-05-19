package com.`in`.sbnri

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.`in`.sbnri.entities.MainEntity
import com.`in`.sbnri.roomdatabase.RoomDatabase

class GenericRepo(application: Application) {

    private var db: RoomDatabase? = null
    private var allData: LiveData<List<MainEntity>>

    init {
        db= (application as MyApplication).db
        allData = db!!.daoSbnri()!!.getAllData()
    }
    fun getAllData(): LiveData<List<MainEntity>> {
        return allData
    }
    fun insert(data: List<MainEntity>) {
        SaveDataToDb(data).execute()
    }
    inner class SaveDataToDb internal constructor(private val data: List<MainEntity>) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            db!!.daoSbnri()!!.insertData(data)
            return null
        }


    }
 }
