package football;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setEndurance(int endurance) {
        validateSkill(endurance, "Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        validateSkill(sprint, "Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateSkill(dribble, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateSkill(passing, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateSkill(shooting, "Shooting");
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        double sum = 0.0 + this.shooting + this.dribble + this.endurance + this.passing + this.sprint;
        double result = sum / 5;

        return result;
    }

    private void validateSkill(int skill, String name){
        if (skill < 0 || skill > 100){
            throw new IllegalArgumentException(String.format("%s should be between 0 and 100.", name));
        }
    }

}
