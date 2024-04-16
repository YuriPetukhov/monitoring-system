package com.example.monitoringsystem.demo;

public class OutOfMaterialException extends RuntimeException {
    public OutOfMaterialException() {
        super("Out of material!");
    }
}
