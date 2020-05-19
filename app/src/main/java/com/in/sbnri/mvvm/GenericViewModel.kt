package com.`in`.sbnri

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.`in`.sbnri.entities.MainEntity


class GenericViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: GenericRepo = GenericRepo(application)
    private var allData: LiveData<List<MainEntity>> = repository.getAllData()
    fun getAllListOfChatters(): LiveData<List<MainEntity>> {
        return allData
    }
    fun insertData(list:List<MainEntity>)
    {
        repository.insert(list)
    }

}
