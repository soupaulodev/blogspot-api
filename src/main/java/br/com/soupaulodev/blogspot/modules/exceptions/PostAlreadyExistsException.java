package br.com.soupaulodev.blogspot.modules.exceptions;

public class PostAlreadyExistsException extends RuntimeException {

    public PostAlreadyExistsException() {
        super("Post already exists");
    }
}
