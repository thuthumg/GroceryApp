package com.padcmyanmar.ttm.groceryapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.adapters.GroceryAdapter
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.dialogs.GroceryDialogFragment

import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.ttm.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_AMOUNT
import com.padcmyanmar.ttm.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_DESCRIPTION
import com.padcmyanmar.ttm.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_IMAGE
import com.padcmyanmar.ttm.groceryapp.dialogs.GroceryDialogFragment.Companion.BUNDLE_NAME

import com.padcmyanmar.ttm.groceryapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls.MainPresenterImpl
import com.padcmyanmar.ttm.groceryapp.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : BaseActivity(), MainView {

    private lateinit var mAdapter: GroceryAdapter
    private lateinit var mPresenter: MainPresenter
    private var loginUserName:String = ""
    companion object {
        const val PICK_IMAGE_REQUEST = 1111
       private const val BUNDLE_USER_NAME = "BUNDLE_USER_NAME"

        fun newIntent(context: Context,userName:String):Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(BUNDLE_USER_NAME,userName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setUpPresenter()
        getIntentParam()
        setUpRecyclerView()
        setUpLoginUserName()

        setUpActionListeners()

        mPresenter.onUiReady(this)
    }

    private fun setUpLoginUserName() {
        tvLogInUserName.text = "Hello, $loginUserName !"
    }
    private fun getIntentParam() {

        loginUserName = intent?.getStringExtra(BUNDLE_USER_NAME).toString()



    }
    private fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpActionListeners() {
        fab.setOnClickListener {
            GroceryDialogFragment.newFragment()
                .show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = GroceryAdapter(mPresenter)
        rvGroceries.adapter = mAdapter
        rvGroceries.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showGroceryData(groceryList: List<GroceryVO>) {
        mAdapter.setNewData(groceryList)
    }

    override fun showGroceryDialog(
        name: String,
        description: String,
        amount: String,
        image: String?
    ) {
        val groceryDialog = GroceryDialogFragment.newFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, name)
        bundle.putString(BUNDLE_DESCRIPTION,description)
        bundle.putString(BUNDLE_AMOUNT, amount)
        bundle.putString(BUNDLE_IMAGE,image)
        groceryDialog.arguments = bundle
        groceryDialog.show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG)
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data
            try {

                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source =
                            ImageDecoder.createSource(this.contentResolver, filePath)

                        val bitmap = ImageDecoder.decodeBitmap(source)
                        mPresenter.onPhotoTaken(bitmap)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            applicationContext.contentResolver, filePath
                        )
                        mPresenter.onPhotoTaken(bitmap)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}