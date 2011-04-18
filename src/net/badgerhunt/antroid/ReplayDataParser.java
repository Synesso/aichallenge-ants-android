package net.badgerhunt.antroid;

import android.graphics.drawable.RotateDrawable;
import com.google.gson.Gson;

import java.text.ParseException;

public class ReplayDataParser {

    private final Gson gson = new Gson();

    public ReplayData parse(String data) throws ParseException {
        return gson.fromJson(data, ReplayDataRaw.class).bake();
    }
}
