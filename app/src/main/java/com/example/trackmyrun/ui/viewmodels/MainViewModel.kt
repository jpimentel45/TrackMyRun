package com.example.trackmyrun.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.trackmyrun.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

}