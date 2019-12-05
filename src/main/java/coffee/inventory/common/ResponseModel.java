package coffee.inventory.common;

import java.io.Serializable;
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

    public void addStatus(ResponseStatus status) {
        statuses.add(status);
    }

    public ResponseModel finishRequest() {
        message = statuses.stream()
            .map(ResponseStatus::getValue)
            .reduce("", String::concat);
            
        return this;
    }
}