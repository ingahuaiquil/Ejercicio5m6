package cl.awakelab.ejercicio5m6.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cl.awakelab.ejercicio5m6.data.Repositorio
import cl.awakelab.ejercicio5m6.data.local.TerrenoDatabase
import cl.awakelab.ejercicio5m6.data.remote.Terreno
import cl.awakelab.ejercicio5m6.data.remote.TerrenoRetroFit
import kotlinx.coroutines.launch

class TerrenoVM(application: Application) : AndroidViewModel(application) {
    private val repositorio: Repositorio
    fun terrenosLiveData() = repositorio.obtenerTerrenos()

    init {
        val api = TerrenoRetroFit.getRetrofitClient()
        val terrenoBaseDato = TerrenoDatabase.getDataBase(application).getITerrenoDao()
        repositorio = Repositorio(api, terrenoBaseDato)
    }

    fun getAllTerrenos() = viewModelScope.launch {
        repositorio.cargarTerreno()

    }

    fun obtenerTerrenoLiveData(id: String) = repositorio.obtenerTerreno(id)
}