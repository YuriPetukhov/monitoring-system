package com.example.monitoringsystem.demo.exception;

public class OutOfMaterialException extends RuntimeException {
    public OutOfMaterialException() {
        super("Out of material!");
    }
}
