package com.tenprj.multimoduleadminboard.domain.converter;

import com.tenprj.multimoduleadminboard.domain.constant.RoleType;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;



public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    private static  final String DELIMITER =",";

    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(DELIMITER)).map(RoleType::valueOf).collect(Collectors.toSet());
    }
}
