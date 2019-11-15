package dev.juliengrandchavin.scuderia.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.models.Skill
import dev.juliengrandchavin.scuderia.repositories.PerformancesRepository

class SkillsAdapter(private val context: Context,
                    private val dataSource: ArrayList<Skill>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val performancesRepository = PerformancesRepository(context.getSharedPreferences("prefs",Context.MODE_PRIVATE))

        val rowView = inflater.inflate(R.layout.skill_item, parent, false)
        val nameTextView = rowView.findViewById(R.id.skillName) as TextView
        val levelTextView = rowView.findViewById(R.id.skillLevel) as TextView
        val priceTextView = rowView.findViewById(R.id.improvePrice) as TextView
        val pictureImageView = rowView.findViewById(R.id.skillPicture) as ImageView
        val improveButton = rowView.findViewById(R.id.improveSkillButton) as Button

        val skill = getItem(position) as Skill

        nameTextView.text = skill.skillName.toString()
        levelTextView.text = "LVL ${skill.currentLevel}"
        priceTextView.text = "100k"
        pictureImageView.setImageDrawable(context.getDrawable(skill.image))

        improveButton.setOnClickListener {
            performancesRepository.updateSkills(skill.skillName, skill.currentLevel)
            levelTextView.text = "LVL ${skill.currentLevel +1}"
        }

        return rowView //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
       return dataSource.size
    }

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}
