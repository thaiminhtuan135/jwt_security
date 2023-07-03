package com.alibou.security.common;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gson {
    public static com.google.gson.Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<>() {
                    @Override
                    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .create();
    }
}
