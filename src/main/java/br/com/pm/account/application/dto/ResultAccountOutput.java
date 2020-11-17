package br.com.pm.account.application.dto;


public class ResultAccountOutput<I, E> {
    private I input;
    private E error;

    public ResultAccountOutput(I input, E error) {
        this.input = input;
        this.error = error;
    }

    public I getInput() {
        return input;
    }

    public E getError() {
        return error;
    }
}
