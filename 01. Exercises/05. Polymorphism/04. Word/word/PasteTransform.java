package word;

import java.util.ArrayDeque;

public class PasteTransform implements TextTransform {
    private ArrayDeque<String> memory;

    public PasteTransform(ArrayDeque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        text.replace(startIndex, endIndex, memory.peek());
    }
}
