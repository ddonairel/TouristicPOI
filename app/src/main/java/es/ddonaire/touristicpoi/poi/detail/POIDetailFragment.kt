package es.ddonaire.touristicpoi.poi.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.ddonaire.touristicpoi.data.local.POIDatabase
import es.ddonaire.touristicpoi.data.model.POI
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

        prepareViewModel()

        val poiId = POIDetailFragmentArgs.fromBundle(requireArguments()).poiId
        viewModel.getPOI(poiId)

        binding.ibMaps.setOnClickListener {
            viewModel.poi.value?.let { poi ->
                viewModel.onMapsClicked(poi)
            }
        }

        viewModel.searchGeoCoordinates.observe(viewLifecycleOwner) {
            if (it != null) {
                searchGeoCoordinates(it)
            }
        }

        return binding.root
    }

    private fun prepareViewModel() {
        val application = requireNotNull(this.activity).application
        val dataSource = POIDatabase.getInstance(application).poiDao
        val repository = POIRepository(dataSource)

        val viewModelFactory = POIViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[POIViewModel::class.java]

        binding.viewModel = viewModel
    }

    private fun searchGeoCoordinates(poi: POI) {
        val gmmIntentUri = Uri.parse("geo:${poi.geocoordinates}?q=" + Uri.encode(poi.title))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireActivity().packageManager)?.let {
            startActivity(mapIntent)
        }
        viewModel.onGeoCoordinatesNavigated()
    }


}