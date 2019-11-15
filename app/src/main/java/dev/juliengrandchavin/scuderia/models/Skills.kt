package dev.juliengrandchavin.scuderia.models

import SkillName

class Skill {
    var skillName: SkillName
    var currentLevel: Int
    var image :Int

    constructor(skillName: SkillName, currentLevel: Int, image: Int) {
        this.skillName = skillName
        this.currentLevel = currentLevel
        this.image = image
    }
}