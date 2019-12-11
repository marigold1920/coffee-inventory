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

    private String status;
    private Object responseObject;
    private String message;
    @JsonIgnore
    private Collection<ResponseStatus> statuses;

    public ResponseModel() {
        statuses = new ArrayList<>();
        this.status = "FAIL";
    }

    public void addStatus(ResponseStatus status) {
        statuses.add(status);
    }

    public void addData(Object object) {
        responseObject = object;
    }
 
    public ResponseModel finishRequest() {
        message = statuses.stream()
            .map(ResponseStatus::getValue)
                .reduce("", String::concat);
        status = message.equals("Successfully") ? "SUCCESS" : "BAD_DATA";
            
        return this;
    }
}