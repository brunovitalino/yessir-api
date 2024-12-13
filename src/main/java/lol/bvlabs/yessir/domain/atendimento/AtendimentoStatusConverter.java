package lol.bvlabs.yessir.domain.atendimento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AtendimentoStatusConverter implements AttributeConverter<AtendimentoStatusEnum, String> {

	@Override
    public String convertToDatabaseColumn(AtendimentoStatusEnum atendimentoStatusEnum) {
        return atendimentoStatusEnum.getValue();
    }

    @Override
    public AtendimentoStatusEnum convertToEntityAttribute(String atendimentoStatusEnumValue) {
        return AtendimentoStatusEnum.valueOf(atendimentoStatusEnumValue);
    }
}
