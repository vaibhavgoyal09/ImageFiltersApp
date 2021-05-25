package com.myproject.imagefiltersapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.myproject.imagefiltersapp.R
import com.myproject.imagefiltersapp.data.ImageFilter
import com.myproject.imagefiltersapp.databinding.ItemContainerFilterBinding
import com.myproject.imagefiltersapp.listeners.ImageFilterListener

class ImageFiltersAdapter(
    private val imageFilters: List<ImageFilter>,
    private val imageFiltersListener: ImageFilterListener
) : RecyclerView.Adapter<ImageFiltersAdapter.ImageFiltersViewHolder>() {


    private var selectedFilterPosition = 0;
    private var previouslySelectedFilterPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFiltersViewHolder {
        val binding = ItemContainerFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageFiltersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageFiltersViewHolder, position: Int) {
        with(holder) {
            with(imageFilters[position]) {
                binding.imageFilterPreview.setImageBitmap(filterPreview)
                binding.textFilterName.text = name
                binding.root.setOnClickListener {
                    if(position != selectedFilterPosition){
                        imageFiltersListener.onFilterSelected(this)
                        previouslySelectedFilterPosition =selectedFilterPosition
                        selectedFilterPosition =position
                        with(this@ImageFiltersAdapter){
                            notifyItemChanged(previouslySelectedFilterPosition, Unit)
                            notifyItemChanged(selectedFilterPosition, Unit)
                        }
                    }
                }
            }
            binding.textFilterName.setTextColor(
                ContextCompat.getColor(
                    binding.textFilterName.context,
                    if (selectedFilterPosition == position)
                        R.color.primaryDark
                    else
                        R.color.primaryText
                )
            )
        }
    }

    override fun getItemCount() = imageFilters.size


    inner class ImageFiltersViewHolder(val binding: ItemContainerFilterBinding) :
        RecyclerView.ViewHolder(binding.root)


}