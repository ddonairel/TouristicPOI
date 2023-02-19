package es.ddonaire.touristicpoi.poi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.ddonaire.touristicpoi.databinding.FragmentPoiBinding

class POIFragment : Fragment() {

    private lateinit var binding: FragmentPoiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPoiBinding.inflate(layoutInflater)

        return binding.root

    }

}