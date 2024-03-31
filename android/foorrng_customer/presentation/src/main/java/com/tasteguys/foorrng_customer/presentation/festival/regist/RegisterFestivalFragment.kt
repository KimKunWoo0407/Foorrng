package com.tasteguys.foorrng_customer.presentation.festival.regist

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.GalleryLauncher
import com.tasteguys.foorrng_customer.presentation.base.toDateString
import com.tasteguys.foorrng_customer.presentation.base.toFile
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentRegisterFestivalBinding
import com.tasteguys.foorrng_customer.presentation.festival.FestivalViewModel
import com.tasteguys.foorrng_customer.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_customer.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "RegisterFestivalFragmen"

@AndroidEntryPoint
class RegisterFestivalFragment @Inject constructor(
    private val isNew: Boolean,
    private val festivalId: Long
) : MainBaseFragment<FragmentRegisterFestivalBinding>(
    { FragmentRegisterFestivalBinding.bind(it) }, R.layout.fragment_register_festival
) {

    private val festivalViewModel: FestivalViewModel by activityViewModels()
    private val inputViewModel: FestivalInputViewModel by viewModels()

    private val galleryLauncher: GalleryLauncher by lazy {
        GalleryLauncher(this)
    }

    override fun setToolbar() {
        MainToolbarControl(
            visible = true,
            title = if (isNew) resources.getString(R.string.register_festival) else resources.getString(
                R.string.update_festival
            ),
            menuRes = R.menu.menu_check
        ).also {
            mainViewModel.changeToolbar(it)
        }.addNavIconClickListener {
            checkBackStackDialog()
        }.addMenuItemClickListener {
            with(inputViewModel) {
                val img = if (imageChanged) infoImg.toFile(requireContext()) else null
                if (isNew) {
                    registerFestival(img)
                } else {
                    updateFestival(festivalId, img)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        galleryLauncher.pictureCallbackListener = object : GalleryLauncher.PictureCallbackListener {
            override fun onGetData(data: Uri) {
                context!!.contentResolver!!.query(data, null, null, null)?.use {
                    val nameIdx = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    it.moveToFirst()
                    val name = it.getString(nameIdx)
                    inputViewModel.infoString = name
                    binding.tilFestivalInfo.editText!!.setText(name)
                }
                inputViewModel.infoImg = data
                inputViewModel.imageChanged = true
            }
        }
        registerObserve()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        with(binding) {
            tiLocation.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, FestivalSelectLocationFragment())
                    .addToBackStack(null)
                    .commit()
            }
            tiFestivalInfo.setOnClickListener {
                galleryLauncher.openGallery()
            }
            tiDate.setOnClickListener {
                val dateRangePicker =
                    MaterialDatePicker.Builder.dateRangePicker().setTitleText("기간 선택").build()
                dateRangePicker.show(childFragmentManager, "date_picker")
                dateRangePicker.addOnPositiveButtonClickListener { selection ->
                    with(inputViewModel) {
                        startDate = selection.first ?: 0
                        endDate = selection.second ?: 0
                        tilDate.editText!!.setText("${startDate.toDateString()} ~ ${endDate.toDateString()}")
                    }
                }
            }
            with(inputViewModel) {
                tilTitle.editText!!.addTextChangedListener { title = it.toString() }
                tilOrganizer.editText!!.addTextChangedListener { organizer = it.toString() }
                tilCallNumber.editText!!.addTextChangedListener { phoneNumber = it.toString() }
                tilEmail.editText!!.addTextChangedListener { email = it.toString() }
                tilKakaoTalk.editText!!.addTextChangedListener { talk = it.toString() }

                if(!isNew && !inputState){
                    with(festivalViewModel){
                        val data = getDetailResult.value!!.getOrNull()!!
                        title = data.title
                        organizer = data.organizer
                        phoneNumber = data.phoneNumber
                        email = data.email
                        talk = data.kakao
                        lat = data.lat
                        lng = data.lng
                        inputViewModel.address = data.address
                        startDate = data.startDate
                        endDate = data.endDate
                    }
                    tilDate.editText!!.setText("${startDate.toDateString()} ~ ${endDate.toDateString()}")
                    tilLocation.editText!!.setText(inputViewModel.address)
                    inputState = true
                }

                tilTitle.editText!!.setText(title)
                tilOrganizer.editText!!.setText(organizer)
                tilCallNumber.editText!!.setText(phoneNumber)
                tilEmail.editText!!.setText(email)
                tilKakaoTalk.editText!!.setText(talk)


            }

        }
    }

    private fun registerObserve() {
        festivalViewModel.address.observe(viewLifecycleOwner) {
            binding.tilLocation.editText!!.setText(it)
            inputViewModel.address = it
            inputViewModel.lat = festivalViewModel.latLng.latitude
            inputViewModel.lng = festivalViewModel.latLng.longitude
        }
        with(inputViewModel) {
            registerResult.observe(viewLifecycleOwner) {
                if (it.isSuccess) {
                    showToast("등록 완료")
                    parentFragmentManager.popBackStack()
                } else {
                    showToast("오류")
                }
            }
            updateResult.observe(viewLifecycleOwner) {
                if (it.isSuccess) {
                    showToast("등록 완료")
                    parentFragmentManager.popBackStack()
                } else {
                    showToast("오류")
                }
            }
        }
    }

    private fun checkBackStackDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("등록을 취소하시겠습니까?")
            .setMessage("작성 중인 내용이 모두 삭제됩니다.")
            .setPositiveButton(resources.getString(R.string.btn_confirm)) { _, _ ->
                parentFragmentManager.popBackStack()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}