package br.com.soupaulodev.blogspot.modules.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post not found");
    }
}
