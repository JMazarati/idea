package ua.pp.idea.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.pp.idea.entity.Idea;

import java.util.regex.Pattern;

/**
 * Created by Dark on 20.11.2016.
 */
public class AddideaValidator implements Validator {
    private final static Pattern NOHTML_PATTERN = Pattern.compile("[0-9a-zA-Zа-яА-ЯёЁіІЄєїЇ/\\Q.'!;:=?$@*+&_,\\E\\- ()\\r\\n\\s]*");
    private final static Pattern NOYOUTUBE_PATTERN = Pattern.compile("[0-9a-zA-Z_\\- ()]*");
    private final static Pattern NOSPACE_PATTERN = Pattern.compile("[0-9a-zA-Zа-яА-ЯёЁіІЄєїЇ/\\Q.'!;:=?$@*+&_,\\E\\-()\\r\\n]*");
    @Override
    public boolean supports(Class<?> aClass) {

        return Idea.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Idea idea = (Idea) o;
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.empty", "Image must not be empty.");

        if (!idea.getFile().isEmpty()) {
            if (!idea.getFile().getContentType().equals("image/jpeg")
                    &!idea.getFile().getContentType().equals("image/gif")
                    &!idea.getFile().getContentType().equals("image/png")) {
                errors.rejectValue("file", "file.Toshort", "Image must be image/jpeg. Mime type in your file was: "+idea.getFile().getContentType()+"");
            }
        }

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "txt", "txt.empty", "Text must not be empty.");
        if(idea.getTxt().isEmpty()&&idea.getVideo().isEmpty()&&idea.getFile().isEmpty()){errors.rejectValue("txt","txt.Toshort","Enter at least something");}

        if(!isHtml(idea.getCaption())){errors.rejectValue("caption","txt.Toshort","No html");}
        if(!isHtml(idea.getTxt())){errors.rejectValue("txt","txt.Toshort","No html");}
        if(!isYOUTUBE(idea.getVideo())){errors.rejectValue("video","video.Toshort","No YOUTUBE CODE");}
        if(!isSPACE(idea.getTags())){errors.rejectValue("tags","txt.Toshort","No html, No Space"); }

    }

    private boolean isHtml(String value) {
        return NOHTML_PATTERN.matcher(value).matches();
    }
    private boolean isYOUTUBE(String value) {
        return NOYOUTUBE_PATTERN.matcher(value).matches();
    }
    private boolean isSPACE(String value) {
        return NOSPACE_PATTERN.matcher(value).matches();
    }


}
