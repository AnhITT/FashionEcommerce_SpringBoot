package DANHHT.Fashion.Validator;

import DANHHT.Fashion.Validator.annotation.ValidUserId;
import DANHHT.Fashion.model.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null){
            return true;
        }
        return user.getId() != null;
    }
}
