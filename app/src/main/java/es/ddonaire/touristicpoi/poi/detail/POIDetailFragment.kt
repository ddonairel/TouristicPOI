package es.ddonaire.touristicpoi.poi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.ddonaire.touristicpoi.data.local.POIDatabase
import es.ddonaire.touristicpoi.databinding.FragmentPoiDetailBinding
import es.ddonaire.touristicpoi.poi.POIViewModel
import es.ddonaire.touristicpoi.poi.POIViewModelFactory
import es.ddonaire.touristicpoi.repository.POIRepository


class POIDetailFragment : Fragment() {

    private lateinit var binding: FragmentPoiDetailBinding
    private lateinit var viewModel: POIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPoiDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val application = requireNotNull(this.activity).application
        val dataSource = POIDatabase.getInstance(application).poiDao
        val repository = POIRepository(dataSource)

        val viewModelFactory = POIViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[POIViewModel::class.java]

        return binding.root
    }


}