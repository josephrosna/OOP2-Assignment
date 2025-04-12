package com.carpool;

import java.util.Locale;
import java.util.ResourceBundle;

//Localization
public class LocalizationUtil {
    private final ResourceBundle bundle;
    public LocalizationUtil(Locale locale) { this.bundle = ResourceBundle.getBundle("messages", locale); }
    public String t(String key) { return bundle.getString(key); }
}
