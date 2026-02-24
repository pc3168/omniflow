package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.OrigemMovimento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrigemMovimentoConverter implements AttributeConverter<OrigemMovimento, String>{

    @Override
    public String convertToDatabaseColumn(OrigemMovimento attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public OrigemMovimento convertToEntityAttribute(String dbData) {
        return OrigemMovimento.fromCodigo(dbData);
    }
}
