package fr.human.it.ig2i.todoappjava.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class ToastUtils {

    public static void displayToast(@NonNull Context context, @StringRes int stringResourceId) {
        Toast.makeText(context, stringResourceId, Toast.LENGTH_LONG).show();
    }

}
