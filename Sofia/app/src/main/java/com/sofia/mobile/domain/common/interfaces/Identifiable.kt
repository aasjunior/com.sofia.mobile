package com.sofia.mobile.domain.common.interfaces

import java.util.UUID

interface Identifiable {
    fun createTemporaryId(): String{
        return UUID.randomUUID().toString()
    }
}