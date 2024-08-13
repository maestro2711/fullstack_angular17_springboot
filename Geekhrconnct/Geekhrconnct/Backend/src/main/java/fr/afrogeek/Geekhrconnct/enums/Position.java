package fr.afrogeek.Geekhrconnct.enums;

public enum Position {

        CEO,  // Superior: no
        CTO, // Superior: CEO
        COO,// Superior: CEO
        TEAM_MANAGER_SOFTWARE,// Superior: COO or CTO
        SENIOR_SOFTWARE_DEVELOPER, // Superior:TEAM_MANAGER_SOFTWARE
        SOFTWARE_DEVELOPER,// Superior:SENIOR_SOFTWARE_DEVELOPER
        JUNIOR_SOFTWARE_DEVELOPER, // Superior:SOFTWARE_DEVELOPER
        WORKING_STUDENT, // Superior: JUNIOR_SOFTWARE_DEVELOPER




}
