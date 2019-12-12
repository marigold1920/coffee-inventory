package coffee.inventory.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coffee.inventory.enumeration.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponseStatus status;
    private Object responseObject;
    private String message;
    @JsonIgnore
    private Collection<String> errors;

    public ResponseModel() {
        errors = new ArrayList<>();
        message = "Successfully!";
    }

    public void addStatus(String error) {
        if (error.isEmpty())
            return;
        errors.add(error);
    }

    public void addData(Object object) {
        responseObject = object;
    }
 
    public ResponseModel finishRequest() {
        message = errors.stream()
                .reduce("", String::concat);
        status = message.equals("Successfully!") ? ResponseStatus.SUCCESS : ResponseStatus.BAD_REQUEST;
            
        return this;
    }
}