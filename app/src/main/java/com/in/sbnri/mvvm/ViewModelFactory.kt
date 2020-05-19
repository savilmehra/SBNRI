package com.`in`.sbnri.mvvm


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.`in`.sbnri.GenericViewModel


class ViewModelFactory(val application: Application ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenericViewModel(application) as T
    }
}
