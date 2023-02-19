package es.ddonaire.touristicpoi.poi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.ddonaire.touristicpoi.data.model.POI
import es.ddonaire.touristicpoi.repository.POIRepository
import kotlinx.coroutines.launch

class POIViewModel(val repository: POIRepository): ViewModel() {

    private val _pois = MutableLiveData<List<POI>>()
    val pois: LiveData<List<POI>>
        get() = _pois

    private val _poi = MutableLiveData<POI>()
    val poi: LiveData<POI>
        get() = _poi

    enum class POIMenu { SORT_AZ, SORT_ZA }

    private val _poiSort = MutableLiveData<POIMenu>()
    val poiSort: LiveData<POIMenu>
        get() = _poiSort

    init {
        viewModelScope.launch {
            repository.downloadPOIs()
            _pois.postValue(repository.getPOIs())
        }
    }

    fun getAllPOIs() {
        viewModelScope.launch {
            _pois.postValue(repository.getPOIs())
        }
    }

    fun getPOIsByTitle(title: String) {
        viewModelScope.launch {
            _pois.postValue(repository.getPOIsByTitle(title))
        }
    }

    /**
     * Sort the list of POIs alphabetically
     */
    fun setSort(sort: POIMenu) {
        if (sort == POIMenu.SORT_AZ) {
            _pois.value = _pois.value?.sortedBy { it.title }
        } else {
            _pois.value = _pois.value?.sortedByDescending { it.title }
        }
        _poiSort.value = sort
    }

}