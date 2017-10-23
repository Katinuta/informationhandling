package by.teplouhova.infhandling.composite;

import by.teplouhova.infhandling.chainresponsobility.TypeTextElement;
import by.teplouhova.infhandling.composite.Component;

import java.util.ArrayList;

public class CompositionTextElement implements Component {

    private TypeTextElement typeTextElement;
    private ArrayList<Component> textElements;

    public CompositionTextElement(TypeTextElement typeTextElement) {
        textElements = new ArrayList<>();
        this.typeTextElement=typeTextElement;
    }

    public CompositionTextElement(ArrayList<Component> components,TypeTextElement typeTextElement) {
        this.textElements=new ArrayList<>();
        this.typeTextElement=typeTextElement;
    }



    public void add(Component text) {
        textElements.add(text);
    }

    public ArrayList<Component> getTextElements() {
        return textElements;
    }

    public TypeTextElement getTypeTextElement() {
        return typeTextElement;
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
        return "CompositionTextElement{" +
                "textElements=" + textElements +
                '}';
    }
}
