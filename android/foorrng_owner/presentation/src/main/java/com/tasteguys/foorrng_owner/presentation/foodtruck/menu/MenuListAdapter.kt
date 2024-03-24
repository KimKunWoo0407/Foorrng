package com.tasteguys.foorrng_owner.presentation.foodtruck.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasteguys.foorrng_owner.presentation.databinding.ItemFoodtruckMenuBinding
import com.tasteguys.foorrng_owner.presentation.model.Menu

class MenuListAdapter(
    private val menuList: List<Menu>
) : RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            ItemFoodtruckMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class MenuViewHolder(
        private val binding: ItemFoodtruckMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            Glide.with(binding.root)
                .load(menu.imageUrl)
                .into(binding.ivMenuImage)

            binding.tvMenuName.text = menu.name
            binding.tvMenuPrice.text = "${menu.price}원"
        }
    }
}