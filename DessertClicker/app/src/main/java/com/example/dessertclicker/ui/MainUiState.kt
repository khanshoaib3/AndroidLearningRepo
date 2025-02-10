package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class MainUiState(
    val currentDessert: Dessert = Datasource.dessertList[0],
    val totalDessertsSold: Int = 0,
    val totalRevenue: Int = 0,
)
