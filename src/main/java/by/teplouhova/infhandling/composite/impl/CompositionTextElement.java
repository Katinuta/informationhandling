package by.teplouhova.infhandling.composite.impl;

import by.teplouhova.infhandling.composite.Component;
import by.teplouhova.infhandling.composite.impl.TypeTextElement;

import java.util.ArrayList;

public class CompositionTextElement implements Component {

    private TypeTextElement type;
    private ArrayList<Component> textElements;

    public CompositionTextElement(TypeTextElement type) {
        textElements = new ArrayList<>();
        this.type=type;
    }

    public CompositionTextElement(ArrayList<Component> components,TypeTextElement type) {
        this.textElements= components;
        this.type=type;
    }



    public void add(Component text) {
        textElements.add(text);
    }

    public ArrayList<Component> getTextElements() {
        return textElements;
    }

    public TypeTextElement getTypeTextElement() {
        return type;
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
        StringBuilder  string=new StringBuilder() ;
        for (Component component:textElements ) {
                string.append(component.toString());
        }
        return string.append(" ").toString();
    }
}
