package com.baubap.android.fakelog.presentation.login

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.baubap.android.fakelog.domain.User
import com.baubap.android.fakelog.framework.common.DEFAULT_CURP
import com.baubap.android.fakelog.usecase.event.LoginFormEvent
import com.baubap.android.fakelog.usecase.validate.ValidateCurp
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var instrumentationContext: Context
    private lateinit var state: StateFlow<User>

    @Before
    fun setUp() {
        viewModel = LoginViewModel()
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        state = viewModel.state
    }

    @Test
    fun `The repository access is always true`() {
        val response = viewModel.repository.getLoginAccess()
        assertEquals(response, true)
    }

    @Test
    fun `Value added in onEvent is correct`() {
        // Set the curp value
        viewModel.onEvent(LoginFormEvent.CurpChanged(DEFAULT_CURP), instrumentationContext)
        // Get the curp value
        val getCurp = viewModel.state.value.curp
        assertEquals(getCurp, DEFAULT_CURP)
    }

    @Test
    fun `Return if any event has an error`() {
        // Set the value with a wrong lenght error
        viewModel.onEvent(LoginFormEvent.CurpChanged("abc"), instrumentationContext)
        // Check for the validate data
        val curpResult = ValidateCurp().check(curp = state.value.curp, context = instrumentationContext)
        // Adds the events to a list and check if any has an error
        val listOfResults = listOf(curpResult)
        val resultCheckResponse = viewModel.anyResultHasError(listOfResults)
        assertEquals(resultCheckResponse, true)
    }

}