package com.vinny.project.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WithdrawReasonTypeConverter implements AttributeConverter<WithdrawReasonType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(WithdrawReasonType attribute) {
            return attribute == null ? null : attribute.getCode();
        }

        @Override
        public WithdrawReasonType convertToEntityAttribute(Integer dbData) {
            return dbData == null ? null : WithdrawReasonType.ofCode(dbData);
        }
    }
