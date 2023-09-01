package cl.awakelab.ejercicio5m6.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.ejercicio5m6.R
import cl.awakelab.ejercicio5m6.data.local.TerrenoEntity
import cl.awakelab.ejercicio5m6.data.remote.Terreno
import cl.awakelab.ejercicio5m6.databinding.ItemTerrenoBinding
import coil.load

class AdapterTerreno: RecyclerView.Adapter<AdapterTerreno.ItemTerrenoViewHolder>(){
    lateinit var binding: ItemTerrenoBinding
    private val listItemTerrenos = mutableListOf<TerrenoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTerrenoViewHolder {
        binding = ItemTerrenoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemTerrenoViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return listItemTerrenos.size
    }

    override fun onBindViewHolder(holder: ItemTerrenoViewHolder, position: Int) {
        val terreno = listItemTerrenos[position]
        holder.bind(terreno)
    }

    fun setData(terreno: List<TerrenoEntity>){
        this.listItemTerrenos.clear()
        this.listItemTerrenos.addAll(terreno)
        notifyDataSetChanged()

    }
    class ItemTerrenoViewHolder (val v: ItemTerrenoBinding): RecyclerView.ViewHolder(v.root){
        fun bind(terreno: TerrenoEntity){
            v.imgTerreno.load(terreno.imagen)
            v.cardTerreno.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", terreno.id)
                Navigation.findNavController(v.root).navigate(R.id.action_listFragment_to_detalleFragment, bundle)
            }
        }
    }
}