package com.baubap.android.fakelog.usecase.validate

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.baubap.android.fakelog.framework.common.DEFAULT_NIP
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateNipTest {

    private lateinit var validateNip: ValidateNip
    private lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        validateNip = ValidateNip()
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun `Default Nip is successful`() {
        val result = validateNip.check(DEFAULT_NIP, instrumentationContext)
        assertEquals(result.successful, true)
    }

    @Test
    fun `Empty Nip is error`() {
        val result = validateNip.check("", instrumentationContext)
        assertEquals(result.successful, false)
    }

    @Test
    fun `Nip lenght minor than 4 chars is error`() {
        val result = validateNip.check("32", instrumentationContext)
        assertEquals(result.successful, false)
    }

}