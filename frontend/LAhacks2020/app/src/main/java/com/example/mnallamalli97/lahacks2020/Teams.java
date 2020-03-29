package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Teams {
        @SerializedName("name")
        private String teamName;
        @SerializedName("points")
        private int points;

        public Teams(String teamName, int points){
            this.teamName = teamName;
            this.points = points;
        }

        public int getPoints(){
            return points;
        }

        public String getTeamName(){
            return teamName;
        }

}
