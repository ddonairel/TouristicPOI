package es.ddonaire.touristicpoi.poi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.ddonaire.touristicpoi.data.model.POI
import es.ddonaire.touristicpoi.databinding.PoiItemBinding

class POIListAdapter(private val clickListener: POIListener) :
    ListAdapter<POI, POIListAdapter.POIViewHolder>(POIDiffCallBack) {

    class POIViewHolder(private var binding: PoiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(poi: POI) {
            binding.poi = poi
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POIViewHolder {
        return POIViewHolder(
            PoiItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: POIViewHolder, position: Int) {
        val poi = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(poi)
        }
        holder.bind(poi)
    }

    companion object POIDiffCallBack : DiffUtil.ItemCallback<POI>() {
        override fun areItemsTheSame(oldItem: POI, newItem: POI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: POI, newItem: POI): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class POIListener(val clickListener: (poi: POI) -> Unit) {
        fun onClick(poi: POI) = clickListener(poi)
    }



}