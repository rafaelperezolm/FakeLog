package com.baubap.android.fakelog.usecase.event

sealed class ValidationEvent {

    object Success : ValidationEvent()

    object Error : ValidationEvent()

}
