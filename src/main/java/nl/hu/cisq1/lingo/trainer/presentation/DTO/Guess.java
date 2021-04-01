package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class Guess {
    @NotBlank
    @Length(min = 5, max = 7)
    public String attempt;
}
