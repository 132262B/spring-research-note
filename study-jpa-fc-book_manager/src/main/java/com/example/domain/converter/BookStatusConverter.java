package com.example.domain.converter;

import com.example.domain.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
