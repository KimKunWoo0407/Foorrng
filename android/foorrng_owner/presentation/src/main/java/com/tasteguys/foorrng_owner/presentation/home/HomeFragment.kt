package com.tasteguys.foorrng_owner.presentation.home

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentHomeBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.info.FoodtruckInfoFragment
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment

class HomeFragment : MainBaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind, R.layout.fragment_home
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.onBackPressedDispatcher.addCallback(mainActivity.onBackPressedCallback)


        binding.cvMyFoodtruck.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment, FoodtruckInfoFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}