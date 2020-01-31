package com.ptw.fantasyleagueapp.viewModel

import androidx.lifecycle.ViewModel
import com.ptw.fantasyleagueapp.backEndHandler.repository.AppRepository
import com.ptw.fantasyleagueapp.dao.AppDaoEnum
import com.ptw.fantasyleagueapp.dataModel.UserListDataModel

class UserDataViewModel (private val empRepository: AppRepository) : ViewModel() {
    fun getEMPFakeDAO() = empRepository.getEmpDataList()
    fun addEMPFakeDAO(empData: UserListDataModel) = empRepository.addEmpDao(empData,AppDaoEnum.DAOUserList)
}