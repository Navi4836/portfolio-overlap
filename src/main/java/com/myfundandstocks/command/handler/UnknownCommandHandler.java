package com.myfundandstocks.command.handler;

public class UnknownCommandHandler implements CommandHandler {
    @Override
    public void handle() {
        System.out.println("Command not found");
    }
}
