package word;

import java.util.ArrayDeque;

public class CutTransform implements TextTransform {
    public ArrayDeque<String> memory;

    public CutTransform(ArrayDeque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        memory.clear();
        memory.push(text.toString().substring(startIndex, endIndex));
        text.replace(startIndex, endIndex, "");
    }
}
