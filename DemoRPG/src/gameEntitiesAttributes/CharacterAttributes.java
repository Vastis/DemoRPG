package gameEntitiesAttributes;

import gameCore.GameHandler;

public class CharacterAttributes extends HostileEntityAttributes {

    protected static final int expStep = 10;
    protected int expForNextLvl;

    protected int
            totalExperience,
            level;

    protected int
            healthOnLvlUp,
            soulPointsOnLvlUp,
            strengthOnLvlUp,
            agilityOnLvlUp,
            intelligenceOnLvlUp,
            physicalDefenceOnLvlUp,
            magicDefenceOnLvlUp;

    public CharacterAttributes(){
        super();
        this.expForNextLvl = this.level * this.level * expStep;
    }
    public CharacterAttributes(GameHandler gameHandler){
        this();
        setGameHandler(gameHandler);
    }

    public CharacterAttributes clone(){
        CharacterAttributes entityAttributes = new CharacterAttributes();
        super.cloneTo(entityAttributes);
        entityAttributes.setTotalExperience(this.totalExperience);
        entityAttributes.setLevel(this.level);
        entityAttributes.setHealthOnLvlUp(healthOnLvlUp);
        entityAttributes.setSoulPointsOnLvlUp(soulPointsOnLvlUp);
        entityAttributes.setStrengthOnLvlUp(strengthOnLvlUp);
        entityAttributes.setAgilityOnLvlUp(agilityOnLvlUp);
        entityAttributes.setIntelligenceOnLvlUp(intelligenceOnLvlUp);
        entityAttributes.setPhysicalDefenceOnLvlUp(physicalDefenceOnLvlUp);
        entityAttributes.setMagicDefenceOnLvlUp(magicDefenceOnLvlUp);
        return entityAttributes;
    }

    @Override
    public void addExperience(int experience) {
        this.totalExperience += experience;
        if(this.totalExperience >= this.expForNextLvl)
            levelUp();
    }
    private void levelUp(){
        this.level += 1;
        this.expForNextLvl = this.level * this.level * expStep;
        this.strength += 5;
        this.agility += 5;
        this.intelligence += 5;
        this.health += 10;
        this.soulPoints += 10;
        this.luck += 0.005;
        this.physicalDefence += 1;
        this.magicDefence += 1;
        System.out.println("Advanced to level " + this.level);
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        this.expForNextLvl = this.level * this.level * expStep;
    }
    public int getTotalExperience() {
        return totalExperience;
    }
    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }
    public void setHealthOnLvlUp(int healthOnLvlUp) {
        this.healthOnLvlUp = healthOnLvlUp;
    }
    public int getAgilityOnLvlUp() {
        return agilityOnLvlUp;
    }
    public int getHealthOnLvlUp() {
        return healthOnLvlUp;
    }
    public int getIntelligenceOnLvlUp() {
        return intelligenceOnLvlUp;
    }
    public int getMagicDefenceOnLvlUp() {
        return magicDefenceOnLvlUp;
    }
    public int getPhysicalDefenceOnLvlUp() {
        return physicalDefenceOnLvlUp;
    }
    public int getSoulPointsOnLvlUp() {
        return soulPointsOnLvlUp;
    }
    public int getStrengthOnLvlUp() {
        return strengthOnLvlUp;
    }
    public void setAgilityOnLvlUp(int agilityOnLvlUp) {
        this.agilityOnLvlUp = agilityOnLvlUp;
    }
    public void setIntelligenceOnLvlUp(int intelligenceOnLvlUp) {
        this.intelligenceOnLvlUp = intelligenceOnLvlUp;
    }
    public void setMagicDefenceOnLvlUp(int magicDefenceOnLvlUp) {
        this.magicDefenceOnLvlUp = magicDefenceOnLvlUp;
    }
    public void setPhysicalDefenceOnLvlUp(int physicalDefenceOnLvlUp) {
        this.physicalDefenceOnLvlUp = physicalDefenceOnLvlUp;
    }
    public void setSoulPointsOnLvlUp(int soulPointsOnLvlUp) {
        this.soulPointsOnLvlUp = soulPointsOnLvlUp;
    }
    public void setStrengthOnLvlUp(int strengthOnLvlUp) {
        this.strengthOnLvlUp = strengthOnLvlUp;
    }
}
