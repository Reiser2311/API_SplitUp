package com.splitup.crud.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class StringArrayConverter implements AttributeConverter<String[], String> {
    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        // Convierte el array de String a una sola cadena separada por comas
        return attribute != null ? String.join(",", attribute) : null;
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        // Convierte la cadena separada por comas de nuevo en un array de String
        return dbData != null ? dbData.split(",") : new String[0];
    }
}
