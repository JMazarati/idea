package ua.pp.idea.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.idea.dao.UserDaoImpl;
import ua.pp.idea.entity.User;
import java.util.regex.Pattern;

/**
 * Created by Dark on 18.11.2016.
 */

public class SignupValidator implements Validator {
    @Autowired
    UserDaoImpl udi;
    private final static Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
    private final static Pattern LOGIN_PATTERN = Pattern.compile("[0-9a-zA-Z_@.'!$=?{}\\- ()]+");
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User myUser = (User) o;

        String str;
        try {str=udi.findUserByName(myUser.getUsername().toLowerCase()).get(0).getUsername();

        }catch (Exception e){str="";}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username must not be empty.");
        String username = myUser.getUsername();
        if(!isLogin(username)){errors.rejectValue("username","username.wrong","not correct symbols on your username");}
        if ((username.length()) < 2) {
            errors.rejectValue("username", "username.tooLong", "Username must more than 2 characters.");
        }
        if (username.toLowerCase().equals(str.toLowerCase())){
            errors.rejectValue("username","username.full","Username is busy");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userpwd", "password.empty", "Password must not be empty.");
        if (!(myUser.getUserpwd()).equals(myUser.getUserkpwd())) {
            errors.rejectValue("userkpwd", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "useremail.empty", "Email must not be empty.");
        if(!isEmail(myUser.getUseremail())){errors.rejectValue("useremail","useremail.notDog","Not @email format");}
    }
    private boolean isEmail(String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }
    private boolean isLogin(String value) {

        return LOGIN_PATTERN.matcher(value).matches();

    }
}
