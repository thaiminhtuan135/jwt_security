package com.alibou.security.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
@RequiredArgsConstructor

public enum TrangThaidon {
    CHO_XU_LY("0", "Cho xu ly"),
    CHAP_NHAN("1", "Chap nhan"),
    TU_CHOI("2", "Tu choi");
    @Getter
    private final String value;
    @Getter
    private final String description;

    public static String getDescriptionByValue(String value) {
        for (GioiTinh item : GioiTinh.values()) {
            if (item.getValue().equals(value)) {
                return item.getDescription();
            }
        }
        return "";
    }

    public static String getValueByDescription(String description) {
        for (TrangThaidon item : TrangThaidon.values()) {
            if (item.getDescription().equals(description)) {
                return item.getValue();
            }
        }
        return "";
    }

    public static List<Map<String,Object>> parseArray() {
        List<Map<String, Object>> data = new ArrayList<>();

        Arrays.stream(GioiTinh.values()).toList().forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("title", item.getDescription());
            map.put("value", item.getValue());
            data.add(map);
        });
        return data;
    }
}
