package by.teplouhova.infhandling.chainresponsobility;

import by.teplouhova.infhandling.composite.Component;

import java.util.ArrayList;

public interface ParserHandler {
    ArrayList<Component> handleRequest(String text);
}
