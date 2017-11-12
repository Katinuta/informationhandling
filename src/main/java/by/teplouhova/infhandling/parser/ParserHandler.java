package by.teplouhova.infhandling.parser;

import by.teplouhova.infhandling.composite.Component;

public interface ParserHandler {
    Component handleRequest(String text);
}
