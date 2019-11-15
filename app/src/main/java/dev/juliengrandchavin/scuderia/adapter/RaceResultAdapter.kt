package dev.juliengrandchavin.scuderia.adapter

import dev.juliengrandchavin.scuderia.models.RaceResult
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dev.juliengrandchavin.scuderia.R

class RaceResultAdapter(
    private val context: Context,
    private val dataSource: ArrayList<RaceResult>
) : BaseAdapter() {

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // View
        val rowView = inflater.inflate(R.layout.result_item, parent, false)
        val raceNameText = rowView.findViewById(R.id.raceName) as TextView
        val rank1Text = rowView.findViewById(R.id.rank1) as TextView
        val rank2Text = rowView.findViewById(R.id.rank2) as TextView

        val raceResult = getItem(position) as RaceResult

        raceNameText.text = raceResult.raceName
        rank1Text.text = raceResult.driver1Rank.toString()
        rank2Text.text = raceResult.driver2Rank.toString()

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
