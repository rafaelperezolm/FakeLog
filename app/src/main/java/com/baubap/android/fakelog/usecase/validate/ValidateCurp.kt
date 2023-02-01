package com.baubap.android.fakelog.usecase.validate

import android.content.Context
import com.baubap.android.fakelog.R
import com.baubap.android.fakelog.framework.common.DEFAULT_CURP

class ValidateCurp {

    fun check(curp: String, context: Context): ValidationResult {
        // Default curp for testing
        if (curp == DEFAULT_CURP) {
            return ValidationResult(
                successful = true
            )
        }
        // Empty CURP
        if (curp.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.curp_error_empty)
            )
        }
        // Error CURP length
        if (curp.length < 18) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.curp_error_lenght)
            )
        }
        // Valid CURP
        return ValidationResult(
            successful = true
        )
    }

}