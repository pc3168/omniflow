package br.com.pc.omniflow.converter;

import br.com.pc.omniflow.domain.enums.ModeloDocumento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModeloDocumentoConverter implements AttributeConverter<ModeloDocumento, String> {
    @Override
    public String convertToDatabaseColumn(ModeloDocumento attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public ModeloDocumento convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return ModeloDocumento.fromCodigo(dbData);
    }

}
