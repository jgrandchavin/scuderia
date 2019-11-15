package dev.juliengrandchavin.scuderia.fragments

import dev.juliengrandchavin.scuderia.adapter.SkillsAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView

import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.repositories.PerformancesRepository
import android.content.SharedPreferences
import TeamRepository





class PerformancesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container!!.context

        val sharedPreferences = this.activity!!.getSharedPreferences("prefs",Context.MODE_PRIVATE)

        // Repo
        val performancesRepository = PerformancesRepository(sharedPreferences)
        val teamRepository = TeamRepository(sharedPreferences)

        // View
        val view  = inflater.inflate(R.layout.fragment_performances, container, false)
        val skills = performancesRepository.getSkills()
        val adapter = SkillsAdapter(container.context, skills)
        val list =  view.findViewById<ListView>(R.id.skillsList) !!
        val teamMoneyText = view.findViewById(R.id.teamMoneyText) as TextView
        teamMoneyText.text = teamRepository.getCurrentTeamMoney().toString() + "k"
        list.adapter = adapter

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            teamMoneyText.text = teamRepository.getCurrentTeamMoney().toString() + "k"
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        return view
    }

}
