package ua.pp.idea.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.idea.entity.Comment;
import java.util.regex.Pattern;
/**
 * Created by idea on 02.12.2016.
 */
public class AddcommentValidator implements Validator{
    private final static Pattern NOHTML_PATTERN = Pattern.compile("[0-9a-zA-Zа-яА-ЯёЁіІЄєїЇ.'!?$:@*+&_,\\- ()\\r\\n\\s]*");


    @Override
    public boolean supports(Class<?> aClass) {
        return Comment.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "note", "note.empty", "Text must not be empty.");
        if(!isHtml(comment.getNote())){errors.rejectValue("note","note.Toshort","No html");}
    }

    private boolean isHtml(String value) {

        return NOHTML_PATTERN.matcher(value).matches();

    }
}
