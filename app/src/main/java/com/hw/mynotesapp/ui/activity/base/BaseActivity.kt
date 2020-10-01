package com.hw.mynotesapp.ui.activity.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.errors.NoAuthExceptions
import com.hw.mynotesapp.mvvm.view.BaseViewState
import com.hw.mynotesapp.mvvm.viewmodel.BaseViewModel

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    companion object{
        private const val RC_SIGH_IN = 12345
    }

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
        viewModel.getViewState().observe(this, Observer { state ->
            state ?: return@Observer
            state.error?.let {
                renderError(it)
                return@Observer
            }
            renderData(state.data)
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable){
        when(error){
            is NoAuthExceptions -> startLogin()
            else -> error.message?.let {
                showError(it)
            }
        }

        error.message?.let {
            showError(it)
        }
    }

    private fun startLogin(){
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val intet = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setLogo(R.drawable.books)
            .setTheme(R.style.LoginStyle)
            .setAvailableProviders(providers)
            .build()

        startActivityForResult(intet, RC_SIGH_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RC_SIGH_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }

    }

    protected fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}