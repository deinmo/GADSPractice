package com.alc.gadspractice;

public class LeadersClass {
    public String name;
    public String skillhours;
    public String country;
    //  public String badgeUrl;


    public LeadersClass(String name, String skillhours, String country) {
        this.name = name;
        this.skillhours = skillhours;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillhours() {
        return skillhours;
    }

    public void setSkillhours(String skillhours) {
        this.skillhours = skillhours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
