package com.sofia.mobile.repository

import com.sofia.mobile.api.RetrofitInstance
import com.sofia.mobile.api.SofiaApiService
import com.sofia.mobile.data.PacienteRepository

object RepositoryProvider {
    val apiService: SofiaApiService by lazy { RetrofitInstance.api }
    val pacienteRepository: PacienteRepository by lazy { PacienteRepository(apiService) }
}