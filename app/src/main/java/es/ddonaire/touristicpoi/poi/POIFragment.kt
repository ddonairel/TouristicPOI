package es.ddonaire.touristicpoi.poi

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import es.ddonaire.touristicpoi.R
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

        prepareViewModel()

        binding.rvPOIs.adapter = POIListAdapter(POIListAdapter.POIListener { poi ->
            viewModel.onPOIClicked(poi.id)
        })

        binding.svPOI.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.getPOIsByTitle(p0)
                } else {
                    viewModel.getAllPOIs()
                }
                return false
            }

        })

        viewModel.navigateToPOIDetail.observe(viewLifecycleOwner) { id ->
            id?.let {
                findNavController().navigate(
                    POIFragmentDirections.actionPOIFragmentToPOIDetailFragment(id)
                )
                viewModel.onPOIDetailNavigated()
            }
        }

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_AZ -> {
                viewModel.setSort(POIViewModel.POIMenu.SORT_AZ)
                true
            }
            R.id.sort_ZA -> {
                viewModel.setSort(POIViewModel.POIMenu.SORT_ZA)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}