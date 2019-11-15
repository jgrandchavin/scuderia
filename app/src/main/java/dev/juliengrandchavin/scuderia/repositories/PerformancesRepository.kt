package dev.juliengrandchavin.scuderia.repositories

import SkillName
import android.content.SharedPreferences
import dev.juliengrandchavin.scuderia.models.Skill
import com.google.gson.Gson
import dev.juliengrandchavin.scuderia.R


class PerformancesRepository(var sharedPreferences: SharedPreferences) {


    fun getSkills(): ArrayList<Skill> {
        val skills: ArrayList<Skill> = arrayListOf(
            Skill(SkillName.BREAK, 1, R.drawable.ic_brake),
            Skill(SkillName.CHASSIS, 1, R.drawable.ic_chassis),
            Skill(SkillName.AERO, 1, R.drawable.ic_race_car),
            Skill(SkillName.ENGINE, 1, R.drawable.ic_piston)
        )
        val breakLevel = sharedPreferences.getInt("break", 1)
        val chassisLevel = sharedPreferences.getInt("chassis", 1)
        val aeroLevel = sharedPreferences.getInt("aero", 1)
        val engineLevel = sharedPreferences.getInt("engine", 1)
        skills[0].currentLevel = breakLevel
        skills[1].currentLevel = chassisLevel
        skills[2].currentLevel = aeroLevel
        skills[3].currentLevel = engineLevel
        return skills
    }

    fun updateSkills(skill: SkillName) {
        val currentLevel = when (skill) {
            SkillName.BREAK -> {
                sharedPreferences.getInt("break", 1)
            }
            SkillName.CHASSIS ->{
                sharedPreferences.getInt("chassis", 1)
            }
            SkillName.AERO -> {
                sharedPreferences.getInt("aero", 1)
            }
            SkillName.ENGINE -> {
                sharedPreferences.getInt("engine", 1)
            }
        }
        val editor = sharedPreferences.edit()
        when (skill) {
            SkillName.BREAK -> editor.putInt("break", currentLevel + 1)
            SkillName.CHASSIS -> editor.putInt("chassis", currentLevel + 1)
            SkillName.AERO -> editor.putInt("aero", currentLevel + 1)
            SkillName.ENGINE -> editor.putInt("engine", currentLevel + 1)
        }
        editor.apply()
    }

    fun getLevelUpdatePrice(skill: SkillName): Int {
       val currentLevel = when (skill) {
            SkillName.BREAK -> {
                sharedPreferences.getInt("break", 1)
            }
            SkillName.CHASSIS ->{
                sharedPreferences.getInt("chassis", 1)
            }
            SkillName.AERO -> {
                sharedPreferences.getInt("aero", 1)
            }
            SkillName.ENGINE -> {
                sharedPreferences.getInt("engine", 1)
            }
        }
        return currentLevel * 13
    }
}
