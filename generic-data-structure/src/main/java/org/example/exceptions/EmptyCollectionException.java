package org.example.exceptions;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException(String collectionType){
        super(collectionType + " is empty.");
    }
}
