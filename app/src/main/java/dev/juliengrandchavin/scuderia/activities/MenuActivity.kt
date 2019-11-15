package dev.juliengrandchavin.scuderia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.juliengrandchavin.scuderia.R
import dev.juliengrandchavin.scuderia.fragments.PerformancesFragment
import dev.juliengrandchavin.scuderia.fragments.RaceFragment
import dev.juliengrandchavin.scuderia.fragments.TeamFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {

    private var raceFragment = RaceFragment()
    private var performancesFragment = PerformancesFragment()
    private var teamFragment = TeamFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        goToRace()

        raceButton.setOnClickListener {
            goToRace()
        }

        performancesButton.setOnClickListener {
            goToPerformances()
        }

        teamButton.setOnClickListener {
            goToTeam()
        }

    }

    private fun goToPerformances() {
        loadFragment(performancesFragment)
    }

    private fun goToTeam() {
        loadFragment(teamFragment)
    }

    private fun goToRace() {
        loadFragment(raceFragment)
    }


    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
            .commit()
    }
}
