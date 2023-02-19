package es.ddonaire.touristicpoi.poi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.ddonaire.touristicpoi.repository.POIRepository

class POIViewModelFactory(
    private val repository: POIRepository
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(POIViewModel::class.java)) {
            return POIViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}