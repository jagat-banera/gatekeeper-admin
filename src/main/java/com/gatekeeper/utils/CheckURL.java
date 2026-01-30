package com.gatekeeper.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CheckURL implements ConstraintValidator<ValidURL,String> {

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {

        try{
            new URL(url).toURI();
            return true ; // URL is valid

        }catch(MalformedURLException e) {
            return false; // URL is invalid
        }catch (URISyntaxException e){
            return false ; // URL us invalid
        }

    }
}
