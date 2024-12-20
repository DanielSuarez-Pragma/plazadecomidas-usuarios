package com.plazadecomidas.usuarios.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AdultValidator implements ConstraintValidator<IsAdult, String> {

    @Override
    public boolean isValid(String birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false; // La fecha no debe ser nula
        }

        try {
            LocalDate date = LocalDate.parse(birthDate); // Parsear la fecha
            LocalDate today = LocalDate.now();
            LocalDate adultThreshold = today.minusYears(18); // Fecha límite para ser mayor de edad
            return date.isBefore(adultThreshold); // Validar si la fecha es anterior a la fecha límite
        } catch (DateTimeParseException e) {
            return false; // Formato de fecha inválido
        }
    }
}
