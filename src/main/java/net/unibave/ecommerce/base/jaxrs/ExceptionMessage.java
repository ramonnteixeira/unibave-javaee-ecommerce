package net.unibave.ecommerce.base.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionMessage {

    private final String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
