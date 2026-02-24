package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.MotivoAjuste;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MotivoAjusteConverter implements AttributeConverter<MotivoAjuste, String> {
    @Override
    public String convertToDatabaseColumn(MotivoAjuste attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public MotivoAjuste convertToEntityAttribute(String dbData) {
        return MotivoAjuste.fromCodigo(dbData);
    }
}
