package es.ddonaire.touristicpoi.poi.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.ddonaire.touristicpoi.R
import es.ddonaire.touristicpoi.data.model.POI

@BindingAdapter("poiData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<POI>?) {
    val adapter = recyclerView.adapter as POIListAdapter
    adapter.submitList(data)
}

@BindingAdapter("poiImage")
fun getImage(view: ImageView, src: String?) {
    src?.let {
        val uri = src.toUri().buildUpon().scheme("https").build()
        Glide
            .with(view.context)
            .load(uri)
            .placeholder(R.drawable.baseline_image_128)
            .into(view)
    }
}

@BindingAdapter("latlngText")
fun bindTextViewToLatLng(textView: TextView, geoCoordinates: String?) {
    geoCoordinates?.let {
        val context = textView.context
        val lat = geoCoordinates.substringBefore(",")
        val long = geoCoordinates.substringAfter(",")
        textView.text = String.format(context.getString(R.string.lat_long_snippet), lat, long)
    }
}