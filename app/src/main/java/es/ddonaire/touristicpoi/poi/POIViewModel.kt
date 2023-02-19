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

    init {
        viewModelScope.launch {
            repository.downloadPOIs()
            _pois.postValue(repository.getPOIs())
        }
    }

}