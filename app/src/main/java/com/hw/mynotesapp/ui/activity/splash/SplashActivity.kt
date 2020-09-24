package com.hw.mynotesapp.ui.activity.splash

import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.hw.mynotesapp.mvvm.view.SplashViewState
import com.hw.mynotesapp.mvvm.viewmodel.SplashViewModel
import com.hw.mynotesapp.ui.activity.base.BaseActivity
import com.hw.mynotesapp.ui.activity.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>(){

    override val viewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override val layoutRes = null

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({viewModel.requestUser()}, 1000)
    }
    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

}