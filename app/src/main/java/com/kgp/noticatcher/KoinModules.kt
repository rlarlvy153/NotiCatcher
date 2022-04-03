package com.kgp.noticatcher

import com.kgp.noticatcher.di.dbModule
import com.kgp.noticatcher.di.viewModelModule

val koinAppModules = listOf(dbModule, viewModelModule)