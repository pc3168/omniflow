package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.StatusRegra;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusRegraConverter implements AttributeConverter<StatusRegra, String> {
    @Override
    public String convertToDatabaseColumn(StatusRegra attribute) {
        return attribute == null ? "P" : attribute.getSigla();
    }

    @Override
    public StatusRegra convertToEntityAttribute(String dbData) {
        return StatusRegra.fromCodigo(dbData);
    }

}
