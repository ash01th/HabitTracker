package com.example.habittracker;

public class HabitDataModel {
    String UserName;
    String HabitName;
    Integer DaysDone;
    Integer DaysMissed;
    Integer CurrentStreak;
    Integer LongestStreak;

    public HabitDataModel(String userName, String habitName, Integer daysDone, Integer daysMissed, Integer currentStreak, Integer longestStreak) {
        UserName = userName;
        HabitName = habitName;
        DaysDone = daysDone;
        DaysMissed = daysMissed;
        CurrentStreak = currentStreak;
        LongestStreak = longestStreak;
    }

    public String getUserName() {
        return UserName;
    }

    public String getHabitName() {
        return HabitName;
    }

    public Integer getDaysDone() {
        return DaysDone;
    }

    public Integer getDaysMissed() {
        return DaysMissed;
    }

    public Integer getCurrentStreak() {
        return CurrentStreak;
    }

    public Integer getLongestStreak() {
        return LongestStreak;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setHabitName(String habitName) {
        HabitName = habitName;
    }

    public void setDaysDone(Integer daysDone) {
        DaysDone = daysDone;
    }

    public void setDaysMissed(Integer daysMissed) {
        DaysMissed = daysMissed;
    }

    public void setCurrentStreak(Integer currentStreak) {
        CurrentStreak = currentStreak;
    }

    public void setLongestStreak(Integer longestStreak) {
        LongestStreak = longestStreak;
    }
}
