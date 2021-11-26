package com.test.view;

public enum FontSize {
    SMALL,
    MEDIUM,
    LARGE;

    public static String getCssPath(FontSize fontSize){
        return switch (fontSize) {
            case MEDIUM -> "css/fontMedium.css";
            case LARGE -> "css/fontLarge.css";
            case SMALL -> "css/fontSmall.css";
            // default -> null;
        };
    }
}
