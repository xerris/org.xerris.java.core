package org.xerris.core.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Json {

    private static Gson gson;

    public static <T> String toJson(T toJson) {
        return instance().toJson(toJson);
    }

    public static <T> T fromJson(String fromJson, Class<T> ofType) {
        return instance().fromJson(fromJson, ofType);
    }

    private static Gson instance() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
        }
        return gson;
    }
}
