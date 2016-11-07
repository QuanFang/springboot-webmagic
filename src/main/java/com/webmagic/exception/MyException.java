package com.webmagic.exception;

/**
 * Created by iceyohoo on 2016/10/20.
 */
public class MyException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 4358030676461433335L;

    public MyException() {
        super();
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

}
