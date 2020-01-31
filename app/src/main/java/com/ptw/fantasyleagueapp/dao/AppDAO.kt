package com.ptw.fantasyleagueapp.dao

import androidx.lifecycle.MutableLiveData
import com.ptw.fantasyleagueapp.dataModel.UserListDataModel

enum class AppDaoEnum {
    DAOUserList,
    DAOUserInfo
}

class AppDAO {
    private val userListDataModelList = mutableListOf<UserListDataModel>()
    private val empLiveDataList = MutableLiveData<MutableList<UserListDataModel>>()

    /*private val userInfoDataModelList = mutableListOf<UserInfoDataModel>()
    private var empLiveDataInfo = MutableLiveData<MutableList<UserInfoDataModel>>()
*/
    fun addEmpData(data: Any, type: AppDaoEnum) {
        when (type) {
            AppDaoEnum.DAOUserList -> {
                userListDataModelList.add(data as UserListDataModel)
                empLiveDataList.value = userListDataModelList

            }
           /* AppDaoEnum.DAOUserInfo -> {
                userInfoDataModelList.clear()
                empLiveDataInfo = MutableLiveData<MutableList<UserInfoDataModel>>()
                userInfoDataModelList.add(data as UserInfoDataModel)
                empLiveDataInfo.value = userInfoDataModelList
            }*/
        }

    }

    fun getEmpDataList() = empLiveDataList
    /*fun getEmpDataInfo() = empLiveDataInfo*/
}