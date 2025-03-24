package com.splitup.crud.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringArrayConverter implements AttributeConverter<List<String>, String> {
    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "{}";
        }
        return "{" + String.join(SEPARATOR, attribute) + "}";
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.equals("{}")) {
            return null;
        }
        return Arrays.stream(dbData.replaceAll("[{}]", "").split(SEPARATOR)).collect(Collectors.toList());
    }
}
