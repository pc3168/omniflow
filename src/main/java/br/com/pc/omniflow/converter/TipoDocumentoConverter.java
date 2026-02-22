package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.TipoDocumento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class    TipoDocumentoConverter implements AttributeConverter<TipoDocumento, String> {
    @Override
    public String convertToDatabaseColumn(TipoDocumento attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public TipoDocumento convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return TipoDocumento.fromCodigo(dbData);
    }

}
