import android.content.SharedPreferences
import dev.juliengrandchavin.scuderia.models.RaceResult
import dev.juliengrandchavin.scuderia.repositories.TeamRepository

class RaceRepository(var sharedPreferences: SharedPreferences) {

    fun simulateRace(raceName: String): RaceResult {

        val teamRepository = TeamRepository(sharedPreferences)
        teamRepository.addStart()
        val rank = getRacePlace()
        teamRepository.addReward(getCashPrize(rank[0], rank[1]))
        if (rank[0] == 1 || rank[1] == 1) {
            teamRepository.addWin()
        }
        val raceResult = RaceResult(raceName, rank[0], rank[1])
        teamRepository.addResult(raceResult)
        return raceResult
    }

    fun getCashPrize(driver1Rank: Int, driver2Rank: Int): Int {

        val driver1Cash = when (driver1Rank) {
            in 1..5 -> 40
            in 6..10 -> 20
            in 11..15 -> 10
            in 16..20 -> 5
            else -> 0
        }

        val driver2Cash = when (driver2Rank) {
            in 1..5 -> 40
            in 6..10 -> 20
            in 11..15 -> 10
            in 16..20 -> 5
            else -> 0
        }

        return driver1Cash + driver2Cash
    }


    private fun getRacePlace(): ArrayList<Int> {
        val teamRepository = TeamRepository(sharedPreferences)
        val teamLevel = teamRepository.getTeamLevel()

        val driver1Place = when (teamLevel) {
            in 4..10 -> (17..20).shuffled().first()
            in 11..20 -> (13..20).shuffled().first()
            in 21..50 -> (6..15).shuffled().first()
            in 50..80 -> (1..7).shuffled().first()
            else -> (1..5).shuffled().first()
        }

        var driver2Place: Int = driver1Place

        while (driver1Place == driver2Place) {
            driver2Place = when (teamLevel) {
                in 4..10 -> (17..20).shuffled().first()
                in 11..20 -> (13..20).shuffled().first()
                in 21..50 -> (6..15).shuffled().first()
                in 50..80 -> (1..7).shuffled().first()
                else -> (1..5).shuffled().first()
            }
        }

        return arrayListOf(driver1Place, driver2Place)
    }
}
