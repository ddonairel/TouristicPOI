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

    private val _navigateToPOIDetail = MutableLiveData<Int?>()
    val navigateToPOIDetail: LiveData<Int?>
        get() = _navigateToPOIDetail

    private val _searchGeoCoordinates = MutableLiveData<POI?>()
    val searchGeoCoordinates: LiveData<POI?>
        get() = _searchGeoCoordinates

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

    fun onPOIClicked(id: Int) {
        _navigateToPOIDetail.value = id
    }

    fun onPOIDetailNavigated() {
        _navigateToPOIDetail.value = null
    }

    fun getPOI(id: Int) {
        viewModelScope.launch {
            _poi.postValue(repository.getPOI(id))
        }
    }

    fun onMapsClicked(poi: POI) {
        _searchGeoCoordinates.value = poi
    }

    fun onGeoCoordinatesNavigated() {
        _searchGeoCoordinates.value = null
    }

}