package com.ptw.fantasyleagueapp.backEndHandler.repository

import com.ptw.fantasyleagueapp.dao.AppDAO
import com.ptw.fantasyleagueapp.dao.AppDaoEnum

public class AppRepository(private val empFakeDao: AppDAO) {
    fun addEmpDao(empData: Any, type: AppDaoEnum) {
        empFakeDao.addEmpData(empData, type)
    }

    fun getEmpDataList() = empFakeDao.getEmpDataList()
//    fun getEmpDataInfo() = empFakeDao.getEmpDataInfo()

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(empFakeDao: AppDAO) = instance ?: synchronized(this) {
            instance ?: AppRepository(empFakeDao).also { instance = it }
        }
    }
}