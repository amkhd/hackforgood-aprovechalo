package hackforgood.demo.sales.util;

import android.text.format.DateFormat;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file TimeFormatter.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class TimeFormatter {

    public static final String getTimeDiffDescription(long time) {

        long now = System.currentTimeMillis();

        long seconds = (now - time) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        String friendly = null;
        long num = 0;
        if (days > 0) {
            return DateFormat.format("dd/MM/yyyy", time).toString();
        }
        else if (hours > 0) {
            num = hours;
            friendly = hours + " hora";
        }
        else if (minutes > 0) {
            num = minutes;
            friendly = minutes + " minuto";
        }
        else {
            num = seconds;
            friendly = seconds + " segundo";
        }
        if (num > 1) {
            friendly += "s";
        }
        return "hace " + friendly;
    }
}
