package com.baubap.android.fakelog.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baubap.android.fakelog.data.LoginRepository
import com.baubap.android.fakelog.domain.User
import com.baubap.android.fakelog.usecase.event.LoginFormEvent
import com.baubap.android.fakelog.usecase.event.ValidationEvent
import com.baubap.android.fakelog.usecase.validate.ValidateCurp
import com.baubap.android.fakelog.usecase.validate.ValidateNip
import com.baubap.android.fakelog.usecase.validate.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// ViewModel for the login screen fragment
class LoginViewModel(
    private val validateCurp: ValidateCurp = ValidateCurp(),
    private val validateNip: ValidateNip = ValidateNip()
) : ViewModel() {

    // Repository associated to the current screen
    val repository = LoginRepository()

    // Handle the state of the data
    private val _state = MutableStateFlow(User())
    val state = _state.asStateFlow()

    // Handle the event channel
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    // Set the events data
    fun onEvent(event: LoginFormEvent, context: Context) {
        when (event) {
            is LoginFormEvent.CurpChanged -> {
                _state.value = _state.value.copy(curp = event.curp)
            }
            is LoginFormEvent.NipChanged -> {
                _state.value = _state.value.copy(nip = event.nip)
            }
            is LoginFormEvent.Submit -> {
                submitData(context)
            }
        }
    }

    // Get the login access
    fun submitData(context: Context) {
        viewModelScope.launch {
            // Check the validation results
            val curpResult = validateCurp.check(curp = _state.value.curp, context = context)
            val nipResult = validateNip.check(nip = _state.value.nip, context = context)
            val listOfResults = listOf(curpResult, nipResult)

            // If there is an error, set the error message
            if (anyResultHasError(listOfResults)) {
                setErrorMessage(curpResult, nipResult)
                return@launch
            }

            // If there is no error, get the login access
            getLoginAccess()
        }
    }

    // Know if there is an error on the validation results
    fun anyResultHasError(listOfResults: List<ValidationResult>): Boolean {
        return listOfResults.any { !it.successful }
    }

    // Set the error message via channel
    private suspend fun setErrorMessage(curpResult: ValidationResult, nipResult: ValidationResult) {
        _state.value = _state.value.copy(
            curpError = curpResult.errorMessage ?: "",
            nipError = nipResult.errorMessage ?: ""
        )
        validationEventChannel.send(ValidationEvent.Error)
    }

    // Get the login access and allow via channel
    private suspend fun getLoginAccess() {
        when (repository.getLoginAccess()) {
            true -> {
                validationEventChannel.send(ValidationEvent.Success)
            }
            else -> {}
        }
    }

}