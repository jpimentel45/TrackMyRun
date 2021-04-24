package com.example.trackmyrun.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trackmyrun.R
import com.example.trackmyrun.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private val viewModel : StatisticsViewModel by viewModels()

}