package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;

import java.util.ArrayList;

public interface ParserHandler {
    Component handleRequest(String text);
}
