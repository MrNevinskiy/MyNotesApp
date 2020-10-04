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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<S> : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    companion object{
        private const val RC_SIGH_IN = 12345
    }

    abstract val viewModel: BaseViewModel<S>
    abstract val layoutRes: Int?

    private lateinit var dataJob: Job
    private lateinit var errorJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getViewState().consumeEach {
                renderData(it)
            }
        }

        errorJob = launch {
            viewModel.getErrorChannel().consumeEach {
                renderError(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        dataJob.cancel()
        errorJob.cancel()
    }

    abstract fun renderData(data: S)

    protected fun renderError(error: Throwable){
        when(error){
            is NoAuthExceptions -> startLogin()
            else -> error.message?.let {
                showError(it)
            }
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