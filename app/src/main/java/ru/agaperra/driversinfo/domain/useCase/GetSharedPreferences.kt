package ru.agaperra.driversinfo.domain.useCase

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetSharedPreferences @Inject constructor(@ApplicationContext private val context: Context){

    fun createSPrefs(): SharedPreferences = context.getSharedPreferences( "app", Context.MODE_PRIVATE)

}