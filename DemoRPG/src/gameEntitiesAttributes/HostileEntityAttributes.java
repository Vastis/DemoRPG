package gameEntitiesAttributes;

public abstract class HostileEntityAttributes extends MortalEntityAttributes {

    protected double
            strength,
            agility,
            intelligence;

    protected HostileEntityAttributes(){
        super();
    }

    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getAgility() {
        return agility;
    }
    public void setAgility(double agility) {
        this.agility = agility;
    }
    public double getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }
}
