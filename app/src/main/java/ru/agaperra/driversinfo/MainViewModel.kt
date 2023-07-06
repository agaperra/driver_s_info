package ru.agaperra.driversinfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
): ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun onOpenDialogClicked() { _showDialog.value = true }
    fun onDialogConfirm() { _showDialog.value = false }

    fun onDialogDismiss() { _showDialog.value = false }


    fun saveData(number: String?, key: String) = sharedPreferencesRepository.saveData(number, key)
    fun saveEnd(end: Boolean) = sharedPreferencesRepository.saveEnd(end)
    fun getAll() = sharedPreferencesRepository.getAll()
    fun saveAll() = sharedPreferencesRepository.saveAll()
    fun getEnd() = sharedPreferencesRepository.getEnd()

    companion object {
        const val NUMBER = "NUMBER"
        const val VRC = "VRC"
        const val DL = "DL"
    }
}