package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.TipoProduto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoProdutoConverter implements AttributeConverter<TipoProduto, String> {
    @Override
    public String convertToDatabaseColumn(TipoProduto attribute) {
        return attribute == null ? TipoProduto.SIMPLES.getCodigo() : attribute.getCodigo();
    }

    @Override
    public TipoProduto convertToEntityAttribute(String dbData) {
        return TipoProduto.fromCodigo(dbData);
    }
}
