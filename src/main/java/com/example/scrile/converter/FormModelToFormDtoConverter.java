package com.example.scrile.converter;

import com.example.scrile.dto.FormDto;
import com.example.scrile.models.FormModel;
import org.springframework.stereotype.Service;

@Service
public class FormModelToFormDtoConverter extends AbstractConverter<FormModel, FormDto> {
    @Override
    public FormDto convert(FormModel source) {
        FormDto f = new FormDto();

        f.setFirstName(source.getFirstName());
        f.setLastName(source.getLastName());
        f.setEmail(source.getEmail());
        f.setProductType(source.getProductType());
        f.setComment(source.getComment());
        f.setSelectedProductCost(source.getSelectedProductCost());
        f.setAmountAdditionalOptions(source.getAmountAdditionalOptions());
        f.setTotalPrice(source.getTotalPrice());

        return f;
    }
}
