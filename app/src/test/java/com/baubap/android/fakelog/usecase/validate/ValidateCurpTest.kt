package com.baubap.android.fakelog.usecase.validate

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.baubap.android.fakelog.framework.common.DEFAULT_CURP
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateCurpTest {

    private lateinit var validateCurp: ValidateCurp
    private lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        validateCurp = ValidateCurp()
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun `Default CURP is successful`() {
        val result = validateCurp.check(DEFAULT_CURP, instrumentationContext)
        assertEquals(result.successful, true)
    }

    @Test
    fun `Empty CURP is error`() {
        val result = validateCurp.check("", instrumentationContext)
        assertEquals(result.successful, false)
    }

    @Test
    fun `CURP lenght minor than 18 chars is error`() {
        val result = validateCurp.check("ABCDE19", instrumentationContext)
        assertEquals(result.successful, false)
    }

}