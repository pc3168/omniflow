package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.TipoEntidade;
import jakarta.persistence.AttributeConverter;

public class TipoEntidadeConverter implements AttributeConverter<TipoEntidade, String> {

    @Override
    public String convertToDatabaseColumn(TipoEntidade attribute) {
        return attribute == null ? null : String.valueOf(attribute.getCodigo());
    }

    @Override
    public TipoEntidade convertToEntityAttribute(String dbData) {
        return dbData == null ? null : TipoEntidade.fromCodigo(dbData.charAt(0));
    }
}
