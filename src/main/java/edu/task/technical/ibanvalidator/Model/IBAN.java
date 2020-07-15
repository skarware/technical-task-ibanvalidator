package edu.task.technical.ibanvalidator.Model;

/**
 * IBAN model to save code and validation result
 */
public class IBAN {

    private final String code;
    private boolean isValid;

    public IBAN(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isValid() {
        return isValid;
    }

    public void isValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return code + "," + isValid;
    }
}
