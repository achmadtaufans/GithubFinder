package com.example.bluebird.utilities

import junit.framework.Assert
import org.junit.Test

import org.junit.Assert.*

class DateHandlerTest {

    @Test
    fun getEpochTest() {
        val date = "13-03-2020 01:00"
        val epochdate: Long = 1584036000

        Assert.assertEquals(epochdate, DateHandler.getEpoch(date))
    }
}