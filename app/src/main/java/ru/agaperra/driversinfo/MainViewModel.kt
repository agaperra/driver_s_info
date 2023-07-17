package ru.agaperra.driversinfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.agaperra.driversinfo.data.dataOptions.DataOptions
import ru.agaperra.driversinfo.domain.model.DriversInfo
import ru.agaperra.driversinfo.domain.repository.DriversInfoRepository
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository
import ru.agaperra.driversinfo.ui.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val driversInfoRepository: DriversInfoRepository
) : ViewModel() {

    private val _showSkipDialog = MutableStateFlow(false)
    val showSkipDialog: StateFlow<Boolean> = _showSkipDialog.asStateFlow()

    private val _showUpdateDialog = MutableStateFlow(false)
    val showUpdateDialog: StateFlow<Boolean> = _showUpdateDialog.asStateFlow()

    private val _vehicleNumber = MutableStateFlow<String?>(null)
    private val vehicleNumber = _vehicleNumber.asStateFlow()

    private val _certificateNumber = MutableStateFlow<String?>(null)
    private val certificateNumber = _certificateNumber.asStateFlow()

    private val _licenseNumber = MutableStateFlow<String?>(null)
    private val licenseNumber = _licenseNumber.asStateFlow()
    fun onOpenSkipDialogClicked() {
        _showSkipDialog.value = true
    }

    fun onSkipDialogConfirm() {
        _showSkipDialog.value = false
    }

    fun onSkipDialogDismiss() {
        _showSkipDialog.value = false
    }

    fun onUpdateSkipDialogClicked() {
        _showUpdateDialog.value = true
    }

    fun onUpdateDialogConfirm() {
        _showUpdateDialog.value = false
    }

    fun onUpdateDialogDismiss() {
        _showUpdateDialog.value = false
    }

    fun saveDataOnScreen(route: String?, data: String?) {
        when (route) {
            Screen.VehicleScreen.route -> {
                saveVehicleNumber(data)
            }

            Screen.CertificateScreen.route -> {
                saveCertificateNumber(data)
            }

            Screen.LicenseScreen.route -> {
                saveLicenseNumber(data)
                saveDriversInfo()
                saveUserEndState(true)
            }

            Screen.InfoScreen.route -> {}
            null -> {}
        }
    }

    private fun getPatternList(route: String?): List<Regex> {
        return when (route) {
            Screen.VehicleScreen.route -> {
                DataOptions.patternForVehicleNumber
            }

            Screen.CertificateScreen.route -> {
                DataOptions.patternForCertificate
            }

            Screen.LicenseScreen.route -> {
                DataOptions.patternForLicense
            }

            Screen.InfoScreen.route -> {
                emptyList()
            }

            else -> {
                emptyList()
            }
        }
    }

    private fun getLatinList(route: String?): List<CharSequence?> {
        return when (route) {
            Screen.VehicleScreen.route -> {
                DataOptions.latinLettersForVehicleNumber
            }

            Screen.CertificateScreen.route -> {
                DataOptions.latinLettersListForCertificate
            }

            Screen.LicenseScreen.route -> {
                DataOptions.latinLettersListForLicense
            }

            Screen.InfoScreen.route -> {
                emptyList()
            }

            else -> {
                emptyList()
            }
        }
    }

    private fun getCyrillicList(route: String?): List<CharSequence> {
        return when (route) {
            Screen.VehicleScreen.route -> {
                DataOptions.cyrillicLettersForVehicleNumber
            }

            Screen.CertificateScreen.route -> {
                DataOptions.cyrillicLettersListForCertificate
            }

            Screen.LicenseScreen.route -> {
                DataOptions.cyrillicLettersListForLicense
            }

            Screen.InfoScreen.route -> {
                emptyList()
            }

            else -> {
                emptyList()
            }
        }
    }

    private fun saveUserEndState(endState: Boolean) {
        sharedPreferencesRepository.saveUserEndState(endState)
    }

    fun getUserEndState() = sharedPreferencesRepository.getUserEndState()

    private fun saveVehicleNumber(number: String?) {
        _vehicleNumber.value = number
    }

    private fun saveCertificateNumber(number: String?) {
        _certificateNumber.value = number
    }

    private fun saveLicenseNumber(number: String?) {
        _licenseNumber.value = number
    }

    private fun saveDriversInfo() {
        driversInfoRepository.saveDriversInfo(createDriversInfo())
    }

    fun getDriversInfo() =
        driversInfoRepository.getDriversInfo()


    fun deleteDriversInfo() {
        saveUserEndState(false)
        driversInfoRepository.deleteDriversInfo()
    }

    private fun createDriversInfo() = DriversInfo(
        vehicleNumber = vehicleNumber.value,
        certificateNumber = certificateNumber.value,
        licenseNumber = licenseNumber.value
    )

    fun checkString(
        route: String?,
        string: String,
        doOnSave: () -> Unit,
        doOnError: () -> Unit
    ) {
        var count = 0
        for (i in getPatternList(route).indices) {
            if (getPatternList(route)[i].matches(string)) {
                val array: Array<String> =
                    string.toCharArray().map { it.toString() }.toTypedArray()
                val newList = array.map {
                    if (DataOptions.ifLetterInCyrillicOrInLatin(
                            it,
                            getLatinList(route)
                        )
                    ) DataOptions.changeLatinForCyrillic(
                        it, getCyrillicList(route),  getLatinList(route)
                    ) else if (DataOptions.ifLetterInCyrillicOrInLatin(
                            it, getCyrillicList(route)
                        )
                    ) it else if (DataOptions.ifLetterInNumbers(it.toIntOrNull()) != null && DataOptions.ifLetterInNumbers(
                            it.toIntOrNull()
                        )!!
                    ) it else null
                }
                if (newList.contains(null)) {
                    continue
                } else {
                    newList.joinToString("")
                    saveDataOnScreen(route, newList.joinToString(""))
                    doOnSave()
                    break
                }
            } else {
                count++
            }
        }
        if (count ==  getPatternList(route).size) {
            doOnError()
        }
    }
}