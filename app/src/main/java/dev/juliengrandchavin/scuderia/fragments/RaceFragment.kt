package dev.juliengrandchavin.scuderia.fragments

import RaceRepository
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.activities.RaceResultActivity
import dev.juliengrandchavin.scuderia.constants.tracks
import kotlinx.android.synthetic.*
import java.util.*

 class RaceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container!!.context

        val sharedPreferences = this.activity!!.getSharedPreferences("prefs", Context.MODE_PRIVATE)

        // Repo
        val raceRepository = RaceRepository(sharedPreferences)

        // View
        val view = inflater.inflate(R.layout.fragment_race, container, false)

        val startRaceButton = view.findViewById<Button>(R.id.startRaceButton)
        val trackNameText = view.findViewById<TextView>(R.id.trackNameText)
        trackNameText.text = tracks.shuffled().first()

        startRaceButton.setOnClickListener {
            val result = raceRepository.simulateRace( trackNameText.text.toString())
            val intent = Intent(container.context, RaceResultActivity::class.java)
            intent.putExtra("driver1Place", result.driver1Rank)
            intent.putExtra("driver2Place", result.driver2Rank)
            intent.putExtra("cashPrize", raceRepository.getCashPrize(result.driver1Rank, result.driver2Rank))
            startActivity(intent)
        }

        return view
    }

     override fun onResume() {
        super.onResume()
        val view =  view!!

        val trackNameText = view.findViewById<TextView>(R.id.trackNameText)
        trackNameText.text = tracks.shuffled().first()

    }

}
