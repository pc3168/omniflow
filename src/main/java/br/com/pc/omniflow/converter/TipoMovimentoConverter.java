package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.TipoMovimentoEstoque;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoMovimentoConverter implements AttributeConverter<TipoMovimentoEstoque, String> {
    @Override
    public String convertToDatabaseColumn(TipoMovimentoEstoque attribute) {
        return attribute == null ? "N" : attribute.getSimbolo();
    }

    @Override
    public TipoMovimentoEstoque convertToEntityAttribute(String dbData) {
        return TipoMovimentoEstoque.fromSimbolo(dbData);
    }

}
