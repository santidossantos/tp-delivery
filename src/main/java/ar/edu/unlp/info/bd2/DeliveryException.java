package ar.edu.unlp.info.bd2;

public class DeliveryException extends Exception{

    private String message;

    public DeliveryException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
