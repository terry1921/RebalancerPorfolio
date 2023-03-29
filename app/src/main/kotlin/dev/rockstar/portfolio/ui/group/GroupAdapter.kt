package dev.rockstar.portfolio.ui.group

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.core.model.Group
import dev.rockstar.portfolio.databinding.ItemGroupBinding
import timber.log.Timber

class GroupAdapter : BindingListAdapter<Group, GroupAdapter.GroupViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        parent.binding<ItemGroupBinding>(R.layout.item_group).let(::GroupViewHolder)

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) =
        holder.bindGroup(getItem(position))

    inner class GroupViewHolder constructor(
        private val binding: ItemGroupBinding
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

        fun bindGroup(group: Group) {
            binding.group = group
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Group>() {
            override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
                return oldItem == newItem
            }
        }
    }

}