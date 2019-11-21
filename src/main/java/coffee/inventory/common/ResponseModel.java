package coffee.inventory.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private Object responseObject;
}