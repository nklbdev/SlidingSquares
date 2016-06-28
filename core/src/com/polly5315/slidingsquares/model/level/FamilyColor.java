package com.polly5315.slidingsquares.model.level;

public enum FamilyColor {
    Pink("Pink"),
    Blue("Blue"),
    Green("Green"),
    Yellow("Yellow"),
    Orange("Orange"),
    Purple("Purple");

    private final String _name;

    FamilyColor(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
