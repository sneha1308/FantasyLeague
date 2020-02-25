package com.ptw.fantasyleagueapp.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class TournamentList(
    val count: Int,
    val next: Any,
    val previous: Any,

    @Expose
    @SerializedName("result")
    val result: List<Result>
)

{
        fun getTournamentsList(): List<Result> {
            val tournamentList = ArrayList<Result>()

            for (c in this.result) {
                tournamentList.add(c)
            }
            return tournamentList
        }
}

data class Result(
    val `public`: Boolean,
    val comments: String,
    val contact: String,
    val country: String,
    val createdBy: String,
    val createdOn: String,
    val description: String,
    val discipline: String,
    val discord: String,
    val fullName: String,
    val id: String,
    val isActive: Boolean,
    val locationId: String,
    val name: String,
    val online: Boolean,
    val organization: String,
    val playDate: Any,
    val prize: String,
    val registrationClosingDatetime: String,
    val registrationEnabled: Boolean,
    val registrationOpeningDatetime: String,
    val rules: String,
    val scheduledDateEnd: String,
    val scheduledDateStart: String,
    val status: Status,
    val timezone: String,
    val updatedBy: Any,
    val updatedOn: Any,
    val website: String
)


data class Status(
    val code: String,
    val value: String
)