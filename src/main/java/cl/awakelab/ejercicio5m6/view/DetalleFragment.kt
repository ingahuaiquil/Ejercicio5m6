package cl.awakelab.ejercicio5m6.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.ejercicio5m6.databinding.FragmentDetalleBinding
import coil.load

private const val ARG_PARAM1 = "id"


class DetalleFragment : Fragment() {
    private lateinit var binding: FragmentDetalleBinding
    private val terrenoVM: TerrenoVM by activityViewModels()
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            param1 = it.getString(ARG_PARAM1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
         terrenoVM.obtenerTerrenoLiveData(param1.toString()).observe(viewLifecycleOwner){
             binding.textViewID.text = it.id
             binding.textViewPrice.text = it.precio.toString()
             binding.textViewType.text = it.tipo
             binding.imageView.load(it.imagen)
         }
        return binding.root
    }



}