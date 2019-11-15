package dev.juliengrandchavin.scuderia.fragments


import RaceResult
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.adapter.RaceResultAdapter
import dev.juliengrandchavin.scuderia.adapter.SkillsAdapter

import dev.juliengrandchavin.scuderia.repositories.TeamRepository

class TeamFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container!!.context

        val view = inflater.inflate(R.layout.fragment_team, container, false)

        val teamMoneyText = view.findViewById(R.id.moneyText) as TextView
        val teamWinText = view.findViewById(R.id.winText) as TextView
        val resultListView = view.findViewById(R.id.resultListView) as ListView
        val teamStart = view.findViewById(R.id.startText) as TextView

        val sharedPreferences = this.activity!!.getSharedPreferences("prefs", Context.MODE_PRIVATE)

        // Repo
        val teamRepository = TeamRepository(sharedPreferences)
        teamMoneyText.text = teamRepository.getCurrentTeamMoney().toString() + "k"
        teamWinText.text = teamRepository.getWinCount().toString()
        teamStart.text = teamRepository.getStartCount().toString()
        val adapter = RaceResultAdapter(container.context,
            teamRepository.getResults().reversed() as ArrayList<RaceResult>
        )
        resultListView.adapter = adapter

        return view
    }
}
