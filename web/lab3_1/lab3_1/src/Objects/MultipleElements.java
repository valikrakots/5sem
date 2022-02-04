package Objects;

import java.util.ArrayList;
import java.util.List;


public abstract class MultipleElements implements Element {
    List<Element> children;

    public MultipleElements() {
        children = new ArrayList<>();
    }

    public MultipleElements(List<Element> children) {
        this.children = children;
    }

    public void add(Element el) {
        children.add(el);
    }

    public void remove(Element el) {
        children.remove(el);
    }

    public List<Element> getElements() {
        return children;
    }


    @Override
    public int count() {
        int count = 0;
        for (Element child : children) {
            count += child.count();
        }
        return count;
    }

    @Override
    public String getValue() {
        StringBuilder builder = new StringBuilder();
        for (Element e : children) {
            builder.append(e.getValue());
        }
        return builder.toString();
    }
}