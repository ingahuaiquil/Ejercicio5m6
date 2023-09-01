package cl.awakelab.ejercicio5m6.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.ejercicio5m6.databinding.FragmentListBinding



class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val terrenoVM: TerrenoVM by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        binding.btnCargar.setOnClickListener{
            terrenoVM.getAllTerrenos()
        }
        return binding.root
    }
    private fun initAdapter(){
        val adapter = AdapterTerreno()
        binding.recyclerViewTerreno.adapter = adapter
        terrenoVM.terrenosLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }


}