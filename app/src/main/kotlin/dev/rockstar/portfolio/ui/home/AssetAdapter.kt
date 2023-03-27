package dev.rockstar.portfolio.ui.home

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.core.model.Asset
import dev.rockstar.portfolio.databinding.ItemAssetBinding
import timber.log.Timber

class AssetAdapter : BindingListAdapter<Asset, AssetAdapter.AssetViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        parent.binding<ItemAssetBinding>(R.layout.item_asset).let(::AssetViewHolder)

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) =
        holder.bindAsset(getItem(position))

    inner class AssetViewHolder     constructor(
        private val binding: ItemAssetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position =
                    bindingAdapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    Timber.d("Clicked on ${getItem(position).name} at $position")
                    //DetailActivity.startActivity(binding.transformationLayout, getItem(position))
                    onClickedAt = currentClickedAt
                }
            }
        }

        fun bindAsset(asset: Asset) {
            binding.asset = asset
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Asset>() {

            override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                oldItem == newItem
        }
    }

}