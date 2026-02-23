package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.TipoOperacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoOperacaoConverter implements AttributeConverter<TipoOperacao, String> {
    @Override
    public String convertToDatabaseColumn(TipoOperacao attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public TipoOperacao convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return TipoOperacao.fromCodigo(dbData);
    }

}
