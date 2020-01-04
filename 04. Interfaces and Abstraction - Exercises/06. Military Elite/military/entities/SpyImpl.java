package military.entities;

import military.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(System.lineSeparator())
                .append(String.format("Code Number: %s", this.codeNumber));
        return output.toString();
    }
}
