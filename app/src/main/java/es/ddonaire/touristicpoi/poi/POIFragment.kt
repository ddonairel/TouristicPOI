package es.ddonaire.touristicpoi.poi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.ddonaire.touristicpoi.data.local.POIDatabase
import es.ddonaire.touristicpoi.databinding.FragmentPoiBinding
import es.ddonaire.touristicpoi.poi.adapter.POIListAdapter
import es.ddonaire.touristicpoi.repository.POIRepository

class POIFragment : Fragment() {

    private lateinit var binding: FragmentPoiBinding
    private lateinit var viewModel: POIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPoiBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val application = requireNotNull(this.activity).application
        val dataSource = POIDatabase.getInstance(application).poiDao
        val repository = POIRepository(dataSource)

        val viewModelFactory = POIViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[POIViewModel::class.java]

        binding.viewModel = viewModel

        binding.rvPOIs.adapter = POIListAdapter(POIListAdapter.POIListener { poi ->

        })

        return binding.root

    }

}