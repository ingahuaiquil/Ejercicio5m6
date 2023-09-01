package cl.awakelab.ejercicio5m6.data

import androidx.lifecycle.LiveData
import cl.awakelab.ejercicio5m6.data.local.TerrenoDao
import cl.awakelab.ejercicio5m6.data.local.TerrenoEntity
import cl.awakelab.ejercicio5m6.data.remote.Terreno
import cl.awakelab.ejercicio5m6.data.remote.TerrenoAPI

class Repositorio(private val terrenoAPI: TerrenoAPI, private val terrenoDao: TerrenoDao) {
    fun obtenerTerrenos(): LiveData<List<TerrenoEntity>> = terrenoDao.obtenerTerrenos()
    suspend fun cargarTerreno() {
        val respuesta = terrenoAPI.getData()

        if (respuesta.isSuccessful) {
            val resp = respuesta.body()
            resp?.let { terrenos ->
                val terrenosEntity = terrenos.map { it.transformar() }
                terrenoDao.insertarTerrenos(terrenosEntity)
            }

        }

    }
    fun obtenerTerreno(id: String): LiveData<TerrenoEntity> = terrenoDao.obtenerTerreno(id)
}

fun Terreno.transformar(): TerrenoEntity =
    TerrenoEntity(this.id, this.precio, this.tipo, this.imagen)

