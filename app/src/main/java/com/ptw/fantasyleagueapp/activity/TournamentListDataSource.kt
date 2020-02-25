package com.ptw.fantasyleagueapp.activity

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.ptw.fantasyleagueapp.backEndHandler.apiHandler.NetworkHandler
import com.ptw.fantasyleagueapp.dataModel.TournamentList


class TournamentListDataSource : PageKeyedDataSource<Long, TournamentList>() {

    companion object {
        private val TAG: String = TournamentListDataSource::class.java.simpleName

        fun TournamentListDataSource(tournamentListDataSource: TournamentListDataSource) {
            tournamentListDataSource.restApiFactory = NetworkHandler.getInstance()
            tournamentListDataSource.networkState = MutableLiveData<TournamentList>()
            tournamentListDataSource.initialLoading = MutableLiveData<TournamentList>()
        }

    }

    private var restApiFactory: NetworkHandler? = null

    private var networkState: MutableLiveData<TournamentList>? = null
    private var initialLoading: MutableLiveData<TournamentList>? = null


    fun getNetworkState(): MutableLiveData<TournamentList>? {
        return networkState
    }

    fun getInitialLoading(): MutableLiveData<TournamentList>? {
        return initialLoading
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, TournamentList>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TournamentList>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, TournamentList>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}