package by.teplouhova.infhandling.composite.impl;

import by.teplouhova.infhandling.composite.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositionTextElement implements Component {

    private TypeTextElement type;
    private List<Component> textElements;

    public CompositionTextElement(TypeTextElement type) {
        textElements = new ArrayList<>();
        this.type = type;
    }

    public CompositionTextElement(List<Component> components, TypeTextElement type) {
        this.textElements = components;
        this.type = type;
    }


    public void add(Component text) {
        textElements.add(text);
    }

    public TypeTextElement getTypeTextElement() {
        return type;
    }

    public Iterator<Component> getIterator() {
        return textElements.iterator();
    }

    public int getSizeTextElement() {
        return textElements.size();
    }

    public Component get(int index) {
        return textElements.get(index);
    }

    public void set(int index, Component component) {
        textElements.set(index, component);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionTextElement that = (CompositionTextElement) o;

        if (type != that.type) return false;
        return textElements != null ? textElements.equals(that.textElements) : that.textElements == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (textElements != null ? textElements.hashCode() : 0);
        return result;
    }

    @Override
    public int countComponent() {
        int count = 0;
        for (Component text : textElements) {
            count += text.countComponent();
        }
        return count;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Component component : textElements) {
            string.append(component.toString());
            if (component.countComponent() != 1) {
                if (((CompositionTextElement) component).type == TypeTextElement.LEXEME &&
                        textElements.indexOf(component) != textElements.size() - 1) {
                    string.append(" ");
                }
            }
        }

        return string.toString();
    }
}
