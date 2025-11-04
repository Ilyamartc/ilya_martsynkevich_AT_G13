package people;

public abstract class Engineer {
    protected String name;
    protected int experience;

    public Engineer(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public abstract String getType();
}
