package people;

public class AutomatedEngineer extends Engineer {
    public AutomatedEngineer(String name, int experience) {
        super(name, experience);
    }

    @Override
    public String getType() {
        return "Automated";
    }
}
