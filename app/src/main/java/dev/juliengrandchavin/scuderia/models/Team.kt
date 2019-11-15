package dev.juliengrandchavin.scuderia.models

import RaceResult

class Team {
    var money: Int
    var winCount: Int
    var startCount: Int
    var raceResults: ArrayList<RaceResult>

    constructor(money: Int, winCount: Int, startCount: Int, raceResults: ArrayList<RaceResult>) {
        this.money = money
        this.winCount = winCount
        this.startCount = startCount
        this.raceResults = raceResults
    }
}