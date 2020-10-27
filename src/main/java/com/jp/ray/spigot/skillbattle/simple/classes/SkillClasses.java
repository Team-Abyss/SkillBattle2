package com.jp.ray.spigot.skillbattle.simple.classes;

import com.jp.ray.spigot.skillbattle.api.SkillClass;

public enum SkillClasses {

    SABER(new Saber());

    private final SkillClass skillClass;

    SkillClasses(SkillClass skillClass) {
        this.skillClass = skillClass;
    }

    public SkillClass getSkillClass() {
        return skillClass;
    }

}
