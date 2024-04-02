package com.tasteguys.foorrng_customer.presentation.truck.regist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.base.GalleryLauncher
import com.tasteguys.foorrng_customer.presentation.base.toFile
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentRegisterTruckBinding
import com.tasteguys.foorrng_customer.presentation.login.DailyFavoriteViewModel
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import com.tasteguys.foorrng_customer.presentation.model.FavoriteCategory
import com.tasteguys.foorrng_customer.presentation.profile.adapter.DailyFavoriteListAdapter
import com.tasteguys.foorrng_customer.presentation.truck.TruckViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "RegisterTruckFragment"

@AndroidEntryPoint
class RegisterTruckFragment @Inject constructor(
    private val isNew: Boolean,
    private val truckId: Long
) : MainBaseFragment<FragmentRegisterTruckBinding>(
    { FragmentRegisterTruckBinding.bind(it) }, R.layout.fragment_register_truck
) {

    private val truckViewModel: TruckViewModel by activityViewModels()
    private val registerInputViewModel: RegisterInputViewModel by activityViewModels()
    private val truckRegisterUpdateViewModel: TruckRegisterUpdateViewModel by viewModels()
    private val dailyFavoriteViewModel: DailyFavoriteViewModel by activityViewModels()

    private val favoriteListAdapter = DailyFavoriteListAdapter()

    private val galleryLauncher: GalleryLauncher by lazy {
        GalleryLauncher(this)
    }

    override fun setToolbar() {
        MainToolbarControl(
            visible = true,
            title = if(isNew) resources.getString(R.string.register_foodtruck) else resources.getString(R.string.update_foodtruck),
            menuRes = R.menu.menu_check,
            backIcon = !isNew
        ).addMenuItemClickListener {
            confirmDialog()
//            showToast("등록 완료")
        }.addNavIconClickListener {
            checkBackStackDialog()
        }.also {
            mainViewModel.changeToolbar(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        galleryLauncher.pictureCallbackListener = object : GalleryLauncher.PictureCallbackListener {
            override fun onGetData(data: Uri) {
                Glide.with(requireContext())
                    .load(data)
                    .fallback(R.drawable.logo_truck)
                    .into(binding.ivTruckPhoto)
                registerInputViewModel.inputPicture(data)
                registerInputViewModel.imageChanged = true

            }
        }
        registerObserve()
    }

    private fun initView() {
        with(binding) {
            with(registerInputViewModel) {
                rvMenuCategory.apply {
                    layoutManager = FlexboxLayoutManager(context)
                    adapter = favoriteListAdapter.apply {
                        submitList(category.value)
                        setOnItemClickListener(object : BaseHolder.ItemClickListener {
                            override fun onClick(position: Int) {
                                val name = currentList[position].name
                                foodCategory[name] = !foodCategory[name]!!
                            }
                        })
                    }
                }
                foodCategory.clear()
                foodCategory.putAll( dailyFavoriteViewModel.getCategoryResult.value!!.getOrNull()!!.associateWith { false })

                tilTruckName.editText!!.addTextChangedListener {
                    inputName(it.toString())
                }
                tilCallNumber.editText!!.addTextChangedListener {
                    inputPhoneNumber(it.toString())
                }
                tilCarNumber.editText!!.addTextChangedListener {
                    inputCarNumber(it.toString())
                }
                tilNotice.editText!!.addTextChangedListener {
                    inputAnnouncement(it.toString())
                }

                if (!isNew && !inputState) {
                    with(truckViewModel) {
                        val data = truckDetailResult.value!!.getOrNull()!!
                        val mainData = data.mainData
                        val opData = data.operation[0]
                        inputName(mainData.name)
                        tilTruckName.editText!!.setText(mainData.name)
                        inputPhoneNumber(mainData.phoneNumber)
                        tilCallNumber.editText!!.setText(mainData.phoneNumber)
                        inputCarNumber(mainData.carNumber)
                        tilCarNumber.editText!!.setText(mainData.carNumber)
                        inputAnnouncement(mainData.announcement)
                        tilNotice.editText!!.setText(mainData.announcement)
                        setMark(opData.address, opData.lat, opData.lng)
                        tilLocation.editText!!.setText(opData.address)
                        Glide.with(requireContext())
                            .load(mainData.picture)
                            .error(R.drawable.bg_profile_photo)
                            .fallback(R.drawable.bg_profile_photo)
                            .into(binding.ivTruckPhoto)
                        for(food in mainData.category){
                            if(food in foodCategory)
                                foodCategory[food] = true
                        }
                    }
                    inputState = true
                }else{
                    tilTruckName.editText!!.setText(name.value)
                    tilCallNumber.editText!!.setText(phoneNumber.value)
                    tilCarNumber.editText!!.setText(carNumber.value)
                    tilNotice.editText!!.setText(announcement.value)
                    tilLocation.editText!!.setText(markAddress.value)
                    Glide.with(requireContext())
                        .load(picture.value)
                        .error(R.drawable.bg_profile_photo)
                        .fallback(R.drawable.bg_profile_photo)
                        .into(binding.ivTruckPhoto)
                }
                favoriteListAdapter.submitList(foodCategory.map { FavoriteCategory(it.key, it.value) })
            }

            tilLocation.visibility = if(isNew) View.VISIBLE else View.GONE

            tiLocation.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, TruckSelectLocationFragment(isNew))
                    .addToBackStack(null)
                    .commit()
            }

            ivTruckPhoto.setOnClickListener {
                galleryLauncher.openGallery()
            }
        }


    }

    private fun registerObserve() {
        registerInputViewModel.markAddress.observe(viewLifecycleOwner) {
            binding.tilLocation.editText!!.setText(it)
        }

        with(truckRegisterUpdateViewModel){
            registerResult.observe(viewLifecycleOwner){
                if (it.isSuccess) {
                    registerOperationInfo(it.getOrNull()!!.id)

                    // register하면 무조건 즐겨찾기에 들어간다
                    truckViewModel.markFavoriteTruck(it.getOrNull()!!.id)
                }
            }
            markRegisterResult.observe(viewLifecycleOwner) {
                if (it.isSuccess) {
                    showToast("등록 성공")
                    parentFragmentManager.popBackStack()
                }
            }
            updateResult.observe(viewLifecycleOwner){
                if(it.isSuccess){
                    showToast("업데이트 성공")
                    parentFragmentManager.popBackStack()

                }
            }
        }
    }

    private fun register() {
        with(registerInputViewModel) {
            truckRegisterUpdateViewModel.registerTruck(
                name.value!!,
                picture.value.toFile(requireContext()),
                carNumber.value!!,
                announcement.value!!,
                phoneNumber.value!!,
                foodCategory.filter { it.value }.map { it.key }
            )
        }
    }

    private fun update() {
        with(registerInputViewModel) {
            truckRegisterUpdateViewModel.updateTruck(
                truckId,
                name.value!!,
                if(imageChanged) picture.value!!.toFile(requireContext()) else null,
                carNumber.value!!,
                announcement.value!!,
                phoneNumber.value!!,
                foodCategory.filter { it.value }.map { it.key }
            )
        }
    }

    private fun registerOperationInfo(id: Long) {
        with(registerInputViewModel) {
            truckRegisterUpdateViewModel.registerOperationInfo(
                id,
                markAddress.value!!,
                markLat.value!!,
                markLng.value!!,
                listOf()
            )
        }
    }


    private fun checkBackStackDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("등록을 취소하시겠습니까?")
            .setMessage("작성 중인 내용이 모두 삭제됩니다.")
            .setPositiveButton(resources.getString(R.string.btn_confirm)) { _, _ ->
                registerInputViewModel.initData()
                parentFragmentManager.popBackStack()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun confirmDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("제보 하시겠습니까?")
            .setMessage("제보시 3회이상의 신고가 있을 시에만 삭제가 가능합니다.")
            .setPositiveButton(resources.getString(R.string.btn_confirm)) { _, _ ->
                validateInput().onSuccess {
                    if (isNew) register() else update()
                }.onFailure {
                    showToast(it.message ?: "알 수 없는 오류가 발생했습니다.")
                }

            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun validateInput(): Result<String> {
        val nameValidation = binding.tilTruckName.editText!!.text.toString().isNotBlank()
        val carNumValidation = binding.tilCarNumber.editText!!.text.toString().let {
            it.isNotBlank() && it.matches(Regex("^[0-9]{2,3}[가-힣][0-9]{4}$"))
        }
//        val categoryValidation =
//            menuCategoryAdapter?.getSelectedCategoryList()?.isNotEmpty() ?: false

        val msg = if (!nameValidation) {
            "이름을 입력해주세요"
        } else if (!carNumValidation) {
            "차량 번호를 올바르게 입력해주세요"
        }
//        else if (!categoryValidation) {
//            "카테고리를 선택해주세요"
//        }
        else {
            return Result.success("success")
        }

        return Result.failure(Exception(msg))
    }


    override fun onDestroy() {
        with(registerInputViewModel) {
            if (!isNew && inputState) {
                inputState = false
            }
            imageChanged = false
            initData()
        }

        super.onDestroy()
    }

}