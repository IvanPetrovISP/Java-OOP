package word;

import java.util.*;

public class CommandImpl implements CommandInterface {
    class ToUpperTransform implements TextTransform {
        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex){
            for (int i = startIndex; i < endIndex; i++) {
                text.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            }
        }
    }
    private Map<String, TextTransform> commandTransforms;
    private StringBuilder text;
    private ArrayDeque<String> memory;

    public CommandImpl(StringBuilder text) {
        this.commandTransforms = new HashMap<>();
        this.text = text;
    }

    public void setMemory(ArrayDeque<String> memory) {
        this.memory = new ArrayDeque<>();
    }

    public ArrayDeque<String> getMemory() {
        return this.memory;
    }

    @Override
    public void init() {
        this.commandTransforms.clear();
        for (Command p : this.initCommands()) {
            this.commandTransforms.putIfAbsent(p.getText(), p.getTextTransform());
        }
    }

    @Override
    public void handleInput(String input) {
        String[] tokens = input.split("\\s+");

        String commandName = tokens[0];
        int startInd = Integer.parseInt(tokens[1]);
        int endInd = Integer.parseInt(tokens[2]);

        this.commandTransforms.get(commandName).invokeOn(this.text, startInd, endInd);
    }

    protected List<Command> initCommands() {
        ArrayDeque<String> memory = new ArrayDeque<>();
        List<Command> commands = new ArrayList<>();
        commands.add(new Command("uppercase", new ToUpperTransform()));
        commands.add(new Command("cut", new CutTransform(memory)));
        commands.add(new Command("paste", new PasteTransform(memory)));

        return commands;
    }
}
