package es.ddonaire.touristicpoi.poi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.ddonaire.touristicpoi.databinding.FragmentPoiDetailBinding


class POIDetailFragment : Fragment() {

    private lateinit var binding: FragmentPoiDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPoiDetailBinding.inflate(layoutInflater)

        return binding.root
    }


}