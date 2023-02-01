package com.baubap.android.fakelog.usecase.event

sealed class LoginFormEvent {

    data class CurpChanged(val curp: String) : LoginFormEvent()

    data class NipChanged(val nip: String) : LoginFormEvent()

    object Submit : LoginFormEvent()

}
