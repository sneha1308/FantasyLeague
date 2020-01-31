package com.ptw.fantasyleagueapp.backEndHandler.interfaceClass

import com.ptw.fantasyleagueapp.viewModel.appViewModelFactory.AppViewModelFactory
import com.ptw.fantasyleagueapp.backEndHandler.repository.AppRepository
import com.ptw.fantasyleagueapp.dao.AppFakeDatabase


object InjectorUtils {
    fun provideEMPViewModelFactory(): AppViewModelFactory {
        val quoteRepository = AppRepository.getInstance(AppFakeDatabase.getInstance().empFakeDao)
        return AppViewModelFactory(quoteRepository)
    }
}