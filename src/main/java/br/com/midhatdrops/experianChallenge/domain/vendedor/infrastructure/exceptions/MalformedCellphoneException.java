package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions;

public class MalformedCellphoneException extends RuntimeException{

    public MalformedCellphoneException(String cellphone) {
        super("Bad cellphone: " + cellphone);
    }

}
