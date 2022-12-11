package com.padcmyanmar.ttm.groceryapp.dialogs

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.activities.MainActivity
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls.MainPresenterImpl
import kotlinx.android.synthetic.main.dialog_add_grocery.*
import kotlinx.android.synthetic.main.dialog_add_grocery.view.*
import kotlinx.android.synthetic.main.view_holder_grocery_item.view.*
import java.io.IOException

class GroceryDialogFragment : DialogFragment() {

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_NAME = "BUNDLE_NAME"
        const val BUNDLE_DESCRIPTION = "BUNDLE_DESCRIPTION"
        const val BUNDLE_AMOUNT = "BUNDLE_AMOUNT"
        const val BUNDLE_IMAGE = "BUNDLE_IMAGE"

        fun newFragment(): GroceryDialogFragment {
            return GroceryDialogFragment()
        }
    }
    private var mImageUrl:String = ""
    private lateinit var mPresenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_grocery, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        view.etGroceryName?.setText(arguments?.getString(BUNDLE_NAME))
        view.etDescription?.setText(arguments?.getString(BUNDLE_DESCRIPTION))
        view.etAmount?.setText(arguments?.getString(BUNDLE_AMOUNT))

        Glide.with(this)
            .load(arguments?.getString(BUNDLE_IMAGE))
            .into(view.ivUpload)

        view.btnAddGrocery.setOnClickListener {
            mPresenter.onTapAddGrocery(
                etGroceryName.text.toString(),
                etDescription.text.toString(),
                etAmount.text.toString().toInt(),
                        mImageUrl
            )
            dismiss()
        }
        view.btnFileUploadDialog.setOnClickListener {
            openGallery()
        }

    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(MainPresenterImpl::class.java)
        }
    }


    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
            MainActivity.PICK_IMAGE_REQUEST
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data

            view?.ivUpload?.let {
                Glide.with(this)
                    .load(filePath)
                    .into(it)
            }


            try {

                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {

                        context?.contentResolver?.let {
                            val source: ImageDecoder.Source =
                                ImageDecoder.createSource(it, filePath)

                            val bitmap = ImageDecoder.decodeBitmap(source)
                            mPresenter.onPhotoTaken(bitmap)
//                            mPresenter.getGroceriesByKey(etGroceryName.text.toString(), onSuccess = { it ->
//                                it.let { groceryVO ->
//                                    mImageUrl = groceryVO.image.toString()
//                                }
//
//
//                            }, onFailure = {
//
//                            })
                        }


                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            context?.applicationContext?.contentResolver, filePath
                        )
                        mPresenter.onPhotoTaken(bitmap)
//                        mPresenter.getGroceriesByKey(etGroceryName.text.toString(), onSuccess = { it ->
//                            it.let { groceryVO ->
//                                mImageUrl = groceryVO.image.toString()
//                            }
//
//
//                        }, onFailure = {
//
//                        })
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}