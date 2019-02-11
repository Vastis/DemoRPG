package gameEntitiesAttributes;

import gameCore.GameHandler;
import gameEntities.HostileEntity;

public abstract class MortalEntityAttributes extends EntityAttributes {

    //to be changed later
    protected int
            health,
            soulPoints,
            lineOfSight,
            experienceFromKilling;
    protected double
            strength,
            agility,
            intelligence,
            luck,
            physicalDefence,
            magicDefence;

    protected MortalEntityAttributes(){
        super();
    }

    public void physicalDamageDealt(gameEntities.HostileEntity attacker){
        damageDealt(attacker, ((MortalEntityAttributes)attacker.getAttributes()).getStrength(),
                ((MortalEntityAttributes)attacker.getAttributes()).getLuck(), this.physicalDefence);
    }
    public void distanceDamageDealt(gameEntities.HostileEntity attacker){
        damageDealt(attacker, ((MortalEntityAttributes)attacker.getAttributes()).getAgility(),
                ((MortalEntityAttributes)attacker.getAttributes()).getLuck(), this.physicalDefence);
    }
    public void magicDamageDealt(gameEntities.HostileEntity attacker){
        damageDealt(attacker, ((MortalEntityAttributes)attacker.getAttributes()).getIntelligence(),
                ((MortalEntityAttributes)attacker.getAttributes()).getLuck(), this.magicDefence);
    }
    private void damageDealt(HostileEntity attacker, double attackerBaseDamage, double attackerLuck, double defence){
        double luckThisTime = this.generator.nextDouble(this.luck);
        int damageTaken = this.generator.normalInt(
                (attackerBaseDamage - defence) * attackerLuck * (1 - luckThisTime),
                attackerBaseDamage * attackerLuck * (1 - luckThisTime));
        if(damageTaken <= 0)
            damageTaken = 0;
        /*else
            this.gameHandler.getGameRenderer().drawTextRelativeToPlayer(Color.RED, ""+damageTaken, this.entity.getPosX(), this.entity.getPosY());*/
        this.health -= damageTaken;
        System.out.println("Hit by " + damageTaken + ". Health left: " + this.health);
        if(this.health <= 0) {
            this.health = 0;
            attacker.getAttributes().addExperience(this.experienceFromKilling);
            attacker.getMovement().setEntitySelected(null);
            System.out.println("Got " + this.experienceFromKilling + " exp from killing.");
        }
    }

    public void cloneTo(MortalEntityAttributes entityAttributes){
        super.cloneTo(entityAttributes);
        entityAttributes.setHealth(this.health);
        entityAttributes.setSoulPoints(this.soulPoints);
        entityAttributes.setSpeed(this.speed);
        entityAttributes.setStrength(this.strength);
        entityAttributes.setAgility(this.agility);
        entityAttributes.setIntelligence(this.intelligence);
        entityAttributes.setPhysicalDefence(this.physicalDefence);
        entityAttributes.setMagicDefence(this.magicDefence);
        entityAttributes.setLuck(this.luck);
        entityAttributes.setLineOfSight(this.lineOfSight);
        entityAttributes.setExperienceFromKilling(this.experienceFromKilling);
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getSoulPoints() {
        return soulPoints;
    }
    public void setSoulPoints(int mana) {
        this.soulPoints = soulPoints;
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
    public double getLuck() {
        return luck;
    }
    public void setLuck(double luck) {
        this.luck = luck;
    }
    public double getPhysicalDefence() {
        return physicalDefence;
    }
    public void setPhysicalDefence(double physicalDefence) {
        this.physicalDefence = physicalDefence;
    }
    public double getMagicDefence() {
        return magicDefence;
    }
    public void setMagicDefence(double magicDefence) {
        this.magicDefence = magicDefence;
    }
    public int getLineOfSight() {
        return lineOfSight;
    }
    public int getExperienceFromKilling() {
        return experienceFromKilling;
    }
    public void setExperienceFromKilling(int experienceFromKilling) {
        this.experienceFromKilling = experienceFromKilling;
    }
    public void setLineOfSight(int lineOfSight) {
        this.lineOfSight = lineOfSight;
    }
}
