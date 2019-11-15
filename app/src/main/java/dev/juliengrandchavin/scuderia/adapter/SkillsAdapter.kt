package dev.juliengrandchavin.scuderia.adapter

import android.annotation.SuppressLint
import dev.juliengrandchavin.scuderia.repositories.TeamRepository
import android.content.Context
import android.content.SharedPreferences
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.models.Skill
import dev.juliengrandchavin.scuderia.repositories.PerformancesRepository

class SkillsAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Skill>
) : BaseAdapter() {

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Repo
        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val performancesRepository = PerformancesRepository(sharedPreferences)
        val teamRepository = TeamRepository(sharedPreferences)

        // View
        val rowView = inflater.inflate(R.layout.skill_item, parent, false)
        val nameTextView = rowView.findViewById(R.id.skillName) as TextView
        val levelTextView = rowView.findViewById(R.id.skillLevel) as TextView
        val priceTextView = rowView.findViewById(R.id.improvePrice) as TextView
        val pictureImageView = rowView.findViewById(R.id.skillPicture) as ImageView
        val improveButton = rowView.findViewById(R.id.improveSkillButton) as Button

        val skill = getItem(position) as Skill

        nameTextView.text = skill.skillName.toString()
        levelTextView.text = "LVL ${skill.currentLevel}"
        priceTextView.text = "${performancesRepository.getLevelUpdatePrice(skill.skillName)}k"
        pictureImageView.setImageDrawable(context.getDrawable(skill.image))

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPref, key ->
            when (key) {
                "break" -> {
                    skill.currentLevel = sharedPref.getInt("break", 1)
                    if (skill.skillName == SkillName.BREAK) {
                        levelTextView.text = "LVL ${skill.currentLevel}"
                        priceTextView.text =
                            "${performancesRepository.getLevelUpdatePrice(skill.skillName)}k"
                    }

                }
                "chassis" -> {
                    skill.currentLevel = sharedPref.getInt("chassis", 1)
                    if (skill.skillName == SkillName.CHASSIS) {
                        levelTextView.text = "LVL ${skill.currentLevel}"
                        priceTextView.text =
                            "${performancesRepository.getLevelUpdatePrice(skill.skillName)}k"
                    }
                }
                "aero" -> {
                    skill.currentLevel = sharedPref.getInt("aero", 1)
                    if (skill.skillName == SkillName.AERO) {
                        levelTextView.text = "LVL ${skill.currentLevel}"
                        priceTextView.text =
                            "${performancesRepository.getLevelUpdatePrice(skill.skillName)}k"
                    }
                }
                "engine" -> {
                    skill.currentLevel = sharedPref.getInt("engine", 1)
                    if (skill.skillName == SkillName.ENGINE) {
                        levelTextView.text = "LVL ${skill.currentLevel}"
                        priceTextView.text =
                            "${performancesRepository.getLevelUpdatePrice(skill.skillName)}k"
                    }

                }
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        improveButton.setOnClickListener {

            if ( teamRepository.buy(performancesRepository.getLevelUpdatePrice(skill.skillName))) {
                performancesRepository.updateSkills(skill.skillName)

                val text = "${skill.skillName} amélioré"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.setGravity(Gravity.TOP, 0, 40)
                toast.show()
            } else {
                val text = "Vous n'avez pas assez d'argent"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.setGravity(Gravity.TOP, 0, 40)
                toast.show()
            }
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}
