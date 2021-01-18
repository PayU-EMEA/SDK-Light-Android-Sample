package com.payu.android.front.sdk.demo.ui.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.api.model.AuthenticateResponse
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.PersistentRepository
import com.payu.android.front.sdk.demo.ui.base.BaseViewModel
import com.payu.android.front.sdk.demo.ui.base.SingleLiveEvent
import com.payu.android.front.sdk.demo.ui.base.binding.ObservableString
import com.payu.android.front.sdk.demo.ui.base.binding.addOnPropertyChanged
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val CLIENT_SECRET_LENGTH = 32

class LoginViewModel(
    private val api: PayUApi,
    private val authRepository: AuthenticationRepository,
    private val persistentRepository: PersistentRepository
) : BaseViewModel() {
    val posId = ObservableString(persistentRepository.posid)
    val clientSecret = ObservableString(persistentRepository.clientSecret)
    val isSaveCredentialsChecked = ObservableBoolean(persistentRepository.saveCredentials)
    val isLoginDataValid = ObservableBoolean(false)
    val showLoginProgress = ObservableBoolean(false)

    val loginEvent = SingleLiveEvent<Boolean>()

    private val _login = MutableLiveData<String>()
    val login: LiveData<String> = _login

    init {
        observeLoginDataChanged()
    }

    private fun observeLoginDataChanged() {
        posId.addOnPropertyChanged { validateCredentials() }.register()
        clientSecret.addOnPropertyChanged { validateCredentials() }.register()
    }

    private fun isPosIdValid() = posId.get()?.length?.let { it > 0 } ?: false

    private fun isClientSecretValid() = clientSecret.get()?.length == CLIENT_SECRET_LENGTH

    private fun validateCredentials() {
        isLoginDataValid.set(isPosIdValid() && isClientSecretValid())
    }

    private fun saveCredentialsIfNedded(posId: String, clientSecret: String) {
        persistentRepository.saveCredentials = isSaveCredentialsChecked.get()
        if (isSaveCredentialsChecked.get()) {
            persistentRepository.posid = posId
            persistentRepository.clientSecret = clientSecret
        }
    }

    private fun handleSuccessfulLogin(response: AuthenticateResponse) {
        authRepository.accessToken = response.access_token
        posId.get()?.let { authRepository.posId = it }
        loginEvent.value = true
    }

    fun login() {
        val posId = posId.get() ?: ""
        val clientSecret = clientSecret.get() ?: ""
        saveCredentialsIfNedded(posId, clientSecret)
        showLoginProgress.set(true)
        api.authenticate(posId, clientSecret)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showLoginProgress.set(false) }
                .subscribe(
                        { handleSuccessfulLogin(it) },
                        { loginEvent.value = false }
                ).register()
    }
}
