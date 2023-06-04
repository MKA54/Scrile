package com.example.scrile.controller;

import com.example.scrile.dto.FormDto;
import com.example.scrile.models.FormValidation;
import com.example.scrile.models.Prices;
import com.example.scrile.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/form/api/v1")
@Tag(name = "Register of Forms.", description = "Form Methods.")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "addForm", method = RequestMethod.POST)
    @Operation(summary = "Add form.")
    @ResponseBody
    public FormValidation addForm(@RequestBody FormDto formDto) {
        return formService.addForm(formDto);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "getPrice", method = RequestMethod.GET)
    @Operation(summary = "Get the cost of goods.")
    @ResponseBody
    public Prices getPrice() {
        return new Prices();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "getAllForms", method = RequestMethod.GET)
    @Operation(summary = "Get all forms.")
    @ResponseBody
    public List<FormDto> getAllForms() {
        return formService.getAllForms();
    }

    @RequestMapping(value = "getFormsByParameter", method = RequestMethod.GET)
    @Operation(summary = "Filtering forms by first name, last name, postal address, product type.")
    @ResponseBody
    public List<FormDto> getFormsByParameter(@RequestHeader String attribute, String parameter) {
        return formService.getFormsByParameter(attribute, parameter);
    }
}
