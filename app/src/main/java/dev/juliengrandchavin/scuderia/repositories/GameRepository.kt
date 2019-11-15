import android.content.SharedPreferences
import dev.juliengrandchavin.scuderia.models.Team
import com.google.gson.Gson


class GameRepository(var sharedPreferences: SharedPreferences) {
    private var gson = Gson()

    fun initGame() {
        val isGameInit: Boolean = sharedPreferences.getBoolean("IsGameInit", false)

        if (!isGameInit) {
            val team = Team(300, 0, 0, arrayListOf())
            val editor = sharedPreferences.edit()
            editor.putString("team", gson.toJson(team))
            editor.putInt("break", 1)
            editor.putInt("chassis", 1)
            editor.putInt("aero", 1)
            editor.putInt("engine", 1)
            editor.putBoolean("IsGameInit", true)
            editor.apply()
        }
    }
}
