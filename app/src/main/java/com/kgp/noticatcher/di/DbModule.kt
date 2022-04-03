package com.kgp.noticatcher.di

import androidx.room.Room
import com.kgp.noticatcher.NotiCatcherApp
import com.kgp.noticatcher.db.AppDatabase
import com.kgp.noticatcher.db.NotiRepository
import org.koin.dsl.module

val dbModule = module {
    val db = Room.databaseBuilder(NotiCatcherApp.appContext, AppDatabase::class.java, "talks")
        .build()
    single { db }
    single { db.notiDao() }
    single { NotiRepository() }
}