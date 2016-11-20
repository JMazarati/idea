package ua.pp.idea.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.pp.idea.entity.Idea;

/**
 * Created by Dark on 20.11.2016.
 */
public class AddideaValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {

        return Idea.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Idea idea = (Idea) o;
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.empty", "Image must not be empty.");

        if (!idea.getFile().isEmpty()) {
            if (!idea.getFile().getContentType().equals("image/jpeg")) {
                errors.rejectValue("file", "file.Toshort", "Image must be image/jpeg.");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "txt", "txt.empty", "Text must not be empty.");
        //if(idea.getTxt().length()<1){errors.rejectValue("txt","txt.Toshort","txt short");}
    }
}
