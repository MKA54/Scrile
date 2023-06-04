package com.example.scrile.dao;

import com.example.scrile.models.FormModel;
import org.springframework.stereotype.Repository;

@Repository
public class FormDaoImpl extends GenericDaoImpl<FormModel, Long> {
    public FormDaoImpl() {
        super(FormModel.class);
    }
}
