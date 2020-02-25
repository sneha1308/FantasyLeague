package com.ptw.fantasyleagueapp.dataModelResponses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ptw.fantasyleagueapp.dataModel.Result
import com.ptw.fantasyleagueapp.dataModel.TournamentList

class TaskListResponse {

    @Expose
    @SerializedName("result")
    private var tournamentListResult: TournamentList? = null

    fun getTournamentsList(): List<Result>? {
        return this.tournamentListResult?.getTournamentsList()
    }

}