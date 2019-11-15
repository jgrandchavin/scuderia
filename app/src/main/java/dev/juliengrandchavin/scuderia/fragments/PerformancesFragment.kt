package dev.juliengrandchavin.scuderia.fragments

import dev.juliengrandchavin.scuderia.adapter.SkillsAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.repositories.PerformancesRepository


class PerformancesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container!!.context
        val performancesRepository = PerformancesRepository(this.activity!!.getSharedPreferences("prefs",Context.MODE_PRIVATE))

       val view  = inflater.inflate(R.layout.fragment_performances, container, false)

        val skills = performancesRepository.getSkills()

        val adapter = SkillsAdapter(container.context, skills)

       val list =  view.findViewById<ListView>(R.id.skillsList) !!

        list.adapter = adapter

        return view
    }

}
