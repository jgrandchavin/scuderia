import android.content.SharedPreferences
import com.google.gson.Gson
import dev.juliengrandchavin.scuderia.activities.MenuActivity
import dev.juliengrandchavin.scuderia.models.Team

public class TeamRepository(var sharedPreferences: SharedPreferences) {

    private var gson = Gson()
    var team: Team

    init {
        val teamInJson: String = sharedPreferences.getString("team", "")!!
        this.team = gson.fromJson(teamInJson, Team::class.java)
    }


    fun getCurrentTeamMoney(): Int {
        return team.money
    }

    fun buy(value: Int) {
        
    }

}