package dev.juliengrandchavin.scuderia.repositories

import dev.juliengrandchavin.scuderia.models.RaceResult
import android.content.SharedPreferences
import com.google.gson.Gson
import dev.juliengrandchavin.scuderia.models.Team

class TeamRepository(var sharedPreferences: SharedPreferences) {

    private var gson = Gson()


    fun getCurrentTeamMoney(): Int {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        return team.money
    }

    fun getWinCount(): Int {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        return team.winCount
    }

    fun getStartCount(): Int {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        return team.startCount
    }

    fun getResults(): ArrayList<RaceResult> {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        return team.raceResults
    }

    fun buy(value: Int): Boolean {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        val editor = sharedPreferences.edit()
        if (team.money - value < 0) {
            return false
        } else {
            team.money = team.money - value
            editor.putString("team", gson.toJson(team))
            editor.apply()
            return true
        }
    }

    fun addReward(value: Int) {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        val editor = sharedPreferences.edit()
        team.money = team.money + value
        editor.putString("team", gson.toJson(team))
        editor.apply()
    }

    fun addStart() {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        val editor = sharedPreferences.edit()
        team.startCount = team.startCount + 1
        editor.putString("team", gson.toJson(team))
        editor.apply()
    }

    fun addWin() {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        val editor = sharedPreferences.edit()
        team.winCount = team.winCount + 1
        editor.putString("team", gson.toJson(team))
        editor.apply()
    }

    fun getTeamLevel(): Int {
        val performancesRepository = PerformancesRepository(sharedPreferences)
        val skills = performancesRepository.getSkills()

        return skills.sumBy { skill -> skill.currentLevel }
    }

    fun addResult(result: RaceResult) {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        val team = gson.fromJson(teamInJson, Team::class.java)
        val editor = sharedPreferences.edit()
        team.raceResults.add(result)
        editor.putString("team", gson.toJson(team))
        editor.apply()
    }
}
