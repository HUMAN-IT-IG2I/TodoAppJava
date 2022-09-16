package fr.human.it.ig2i.todoappjava.utils;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    @NonNull
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
    @NonNull
    private final static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.FRANCE);

    public static String extractDateFromDateField(@NonNull Date date) {
        return dateFormat.format(date);
    }

    public static String extractTimeFromDateField(@NonNull Date date) {
        return timeFormat.format(date);
    }

}
