package gameEntities;

import gameEngine.GameHandler;
import utilities.PseudoRandomGenerator;

public class EntityAttributes {

    private static final double maxLuck = 0.75;
    //tmp
    private static final int expStep = 10;
    private int expForNextLvl;

    private MortalEntity entity;
    private GameHandler gameHandler;

    private PseudoRandomGenerator generator;
    //to be changed later
    private int
            health,
            soulPoints,
            lineOfSight,
            totalExperience,
            experienceFromKilling,
            level;
    private double
            strength,
            agility,
            intelligence,
            luck,
            physicalDefence,
            magicDefence;

    public EntityAttributes(MortalEntity entity, GameHandler gameHandler){
        this.entity = entity;
        this.gameHandler = gameHandler;
        this.generator = new PseudoRandomGenerator();
        this.health = 30;
        this.soulPoints = 10;
        this.strength = 10;
        this.agility = 10;
        this.intelligence = 30;
        this.physicalDefence = 5;
        this.magicDefence = 2;
        this.luck = 0.3;
        this.lineOfSight = 3;
        this.totalExperience = 0;
        this.experienceFromKilling = 10;
        this.level = 1;
        this.expForNextLvl = this.level * this.level * expStep;
    }

    public void physicalDamageDealt(HostileEntity attacker){
        dealDamage(attacker, attacker.getAttributes().getStrength(), attacker.getAttributes().getLuck(), this.physicalDefence);
    }
    public void distanceDamageDealt(HostileEntity attacker){
        dealDamage(attacker, attacker.getAttributes().getAgility(), attacker.getAttributes().getLuck(), this.physicalDefence);
    }
    public void magicDamageDealt(HostileEntity attacker){
        dealDamage(attacker, attacker.getAttributes().getIntelligence(), attacker.getAttributes().getLuck(), this.magicDefence);
    }
    private void dealDamage(HostileEntity attacker, double attackerBaseDamage, double attackerLuck, double defence){
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
            System.out.println("Got " + this.experienceFromKilling + " exp from killing. Total exp: " + attacker.getAttributes().getTotalExperience());
        }
    }

    public void addExperience(int totalExperience) {
        this.totalExperience += totalExperience;
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
    public int getTotalExperience() {
        return totalExperience;
    }
    public int getExperienceFromKilling() {
        return experienceFromKilling;
    }
}
