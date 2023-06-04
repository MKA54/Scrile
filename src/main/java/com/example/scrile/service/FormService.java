package com.example.scrile.service;

import com.example.scrile.converter.FormDtoToFormModelConverter;
import com.example.scrile.converter.FormModelToFormDtoConverter;
import com.example.scrile.dao.FormDaoImpl;
import com.example.scrile.dto.FormDto;
import com.example.scrile.models.FormModel;
import com.example.scrile.models.FormValidation;
import com.example.scrile.models.Prices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FormService {
    private final FormDtoToFormModelConverter formDtoToFormModelConverter;
    private final FormModelToFormDtoConverter formModelToFormDtoConverter;
    private final FormDaoImpl formDao;

    public FormService(FormDaoImpl formDao,
                       FormDtoToFormModelConverter formDtoToFormModelConverter,
                       FormModelToFormDtoConverter formModelToFormDtoConverter) {
        this.formDtoToFormModelConverter = formDtoToFormModelConverter;
        this.formModelToFormDtoConverter = formModelToFormDtoConverter;
        this.formDao = formDao;
    }

    public FormValidation addForm(FormDto formDto) {
        FormValidation formValidation = validateForm(formDto);

        if (formValidation.isValid()) {
            formDao.create(formDtoToFormModelConverter.convert(formDto));
        }

        return formValidation;
    }

    public List<FormDto> getAllForms() {
        List<FormModel> formsModel = formDao.findAll();

        if (formsModel.isEmpty()) {
            return null;
        }

        List<FormDto> formsDto = new ArrayList<>();
        for (FormModel f : formsModel) {
            formsDto.add(formModelToFormDtoConverter.convert(f));
        }

        return formsDto;
    }

    public List<FormDto> getFormsByParameter(String attribute, String parameter) {
        List<FormModel> formsModel = formDao.getFormsByParameter(attribute, parameter);

        if (formsModel.isEmpty()) {
            return null;
        }

        List<FormDto> formsDto = new ArrayList<>();
        for (FormModel f : formsModel) {
            formsDto.add(formModelToFormDtoConverter.convert(f));
        }

        return formsDto;
    }

    private FormValidation validateForm(FormDto formDto) {
        FormValidation formValidation = new FormValidation();
        formValidation.setValid(true);

        if (formDto.getFirstName().isEmpty()) {
            formValidation.setValid(false);
            formValidation.setError("The name field must be filled in.");
            return formValidation;
        }

        if (formDto.getLastName().isEmpty()) {
            formValidation.setValid(false);
            formValidation.setError("The last name field must be filled in.");
            return formValidation;
        }

        String email = formDto.getEmail();
        if (email.isEmpty()) {
            formValidation.setValid(false);
            formValidation.setError("The email field must be filled in.");
            return formValidation;
        }

        Pattern pattern = Pattern.compile("^[A-Za-z\\d+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            formValidation.setValid(false);
            formValidation.setError("Incorrect email format entered, example - user@gmail.com.");
            return formValidation;
        }

        if (formDto.getProductType().isEmpty()) {
            formValidation.setValid(false);
            formValidation.setError("No product type selected.");
            return formValidation;
        }

        if (priceValidation(formDto.getProductType(), formDto.getSelectedProductCost())) {
            formValidation.setValid(false);
            formValidation.setError("Incorrect product price " + formDto.getProductType() + ".");
            return formValidation;
        }

        return formValidation;
    }

    private boolean priceValidation(String productType, int price) {
        Prices prices = new Prices();
        if (productType.equals("monitor")) {
            System.out.println(prices.getMonitor() != price);
            return prices.getMonitor() != price;
        }

        if (productType.equals("laptop")) {
            return prices.getLaptop() != price;
        }

        if (productType.equals("phone")) {
            return prices.getPhone() != price;
        }

        return false;
    }
}
