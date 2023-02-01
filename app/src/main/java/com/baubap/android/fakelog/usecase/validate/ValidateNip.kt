package com.baubap.android.fakelog.usecase.validate

import android.content.Context
import com.baubap.android.fakelog.R
import com.baubap.android.fakelog.framework.common.DEFAULT_NIP

class ValidateNip {

    fun check(nip: String, context: Context): ValidationResult {
        // Default nip for testing
        if (nip == DEFAULT_NIP) {
            return ValidationResult(
                successful = true
            )
        }
        // Empty NIP
        if (nip.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.nip_error_empty)
            )
        }
        // Invalid NIP length
        if (nip.length < 4) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.nip_error_lenght)
            )
        }
        // Valid NIP
        return ValidationResult(
            successful = true
        )
    }

}