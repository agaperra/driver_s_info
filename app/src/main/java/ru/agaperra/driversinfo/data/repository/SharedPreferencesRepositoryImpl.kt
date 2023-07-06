package ru.agaperra.driversinfo.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.agaperra.driversinfo.domain.repository.SharedPreferencesRepository
import ru.agaperra.driversinfo.domain.useCase.GetSharedPreferences
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    prefs: GetSharedPreferences
): SharedPreferencesRepository {

    companion object{
        const val NUMBER = "NUMBER"
        const val VRC = "VRC"
        const val DL = "DL"
        const val END = "END"
    }

    private var sPrefs: SharedPreferences = prefs.createSPrefs()

    override fun saveData(number: String?, key: String) {
        if (key == VRC){
            if (number.isNullOrEmpty()){
                sPrefs.edit(){
                    putString(NUMBER, null)
                    putString(key, null)
                }
            } else {
                sPrefs.edit(){
                    putString(key, number)
                }
            }
        } else {
            sPrefs.edit() {
                putString(key, number)
            }
        }
    }

    override fun saveEnd(end: Boolean) {
        sPrefs.edit() {
            putBoolean(END, end)
        }
    }


    override fun getAll(): ArrayList<String?> {
        val list = arrayListOf<String?>()
        list.add(sPrefs.getString(NUMBER, null))
        list.add(sPrefs.getString(VRC, null))
        list.add(sPrefs.getString(DL, null))
        return list
    }

    override fun getEnd(): Boolean =
        sPrefs.getBoolean(END, false)

    override fun saveAll(){
        sPrefs.edit() {
            putBoolean(END, false)
            putString(NUMBER, null)
            putString(VRC, null)
            putString(DL, null)
        }
    }
}