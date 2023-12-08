package lab3.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
@FacesValidator("rValidator")
public class RValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || Double.parseDouble(value.toString()) <= 0) {
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
                    .add("drawShapesByR(0);");
            FacesMessage message = new FacesMessage("Выберите R!");
            throw new ValidatorException(message);
        }
    }
}

