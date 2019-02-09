package gameEntities;

import gameEngine.GameHandler;
import javafx.scene.paint.Color;
import utilities.PseudoRandomGenerator;

public class EntityAttributes {

    private static final double maxLuck = 0.75;
    //tmp
    private static final int expStep = 10;
    private int expForNextLvl;

    private MortalEntity entity;
    private GameHandler gameHandler;

    private PseudoRandomGenerator generator;

    private String name, type;
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
            speed,
            physicalDefence,
            magicDefence;

    private int
            healthOnLvlUp,
            soulPointsOnLvlUp,
            strengthOnLvlUp,
            agilityOnLvlUp,
            intelligenceOnLvlUp,
            physicalDefenceOnLvlUp,
            magicDefenceOnLvlUp;

    private Color graphics;

    private int initTileX, initTileY;

    public EntityAttributes(){
        this.generator = new PseudoRandomGenerator();
        //default params - if not set by XMLManager.
        this.health = 30;
        this.soulPoints = 10;
        this.speed = 0.0;
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
        this.name = "";
        this.type = "Melee";
        this.graphics = Color.YELLOW;
    }
    public EntityAttributes(MortalEntity entity, GameHandler gameHandler){
        this();
        this.entity = entity;
        this.gameHandler = gameHandler;
    }

    public void setEntity(MortalEntity entity){
        this.entity = entity;
    }
    public void setGameHandler(GameHandler gameHandler){
        this.gameHandler = gameHandler;
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

    public EntityAttributes clone(){
        EntityAttributes cloned = new EntityAttributes(this.entity, this.gameHandler);
        cloned.setInitTileX(this.initTileX);
        cloned.setInitTileY(this.initTileY);
        cloned.setHealth(this.health);
        cloned.setSoulPoints(this.soulPoints);
        cloned.setSpeed(this.speed);
        cloned.setStrength(this.strength);
        cloned.setAgility(this.agility);
        cloned.setIntelligence(this.intelligence);
        cloned.setPhysicalDefence(this.physicalDefence);
        cloned.setMagicDefence(this.magicDefence);
        cloned.setLuck(this.luck);
        cloned.setLineOfSight(this.lineOfSight);
        cloned.setTotalExperience(this.totalExperience);
        cloned.setExperienceFromKilling(this.experienceFromKilling);
        cloned.setLevel(this.level);
        cloned.setName(this.name);
        cloned.setType(this.type);
        cloned.setGraphics(this.graphics);
        cloned.setHealthOnLvlUp(healthOnLvlUp);
        cloned.setSoulPointsOnLvlUp(soulPointsOnLvlUp);
        cloned.setStrengthOnLvlUp(strengthOnLvlUp);
        cloned.setAgilityOnLvlUp(agilityOnLvlUp);
        cloned.setIntelligenceOnLvlUp(intelligenceOnLvlUp);
        cloned.setPhysicalDefenceOnLvlUp(physicalDefenceOnLvlUp);
        cloned.setMagicDefenceOnLvlUp(magicDefenceOnLvlUp);
        return cloned;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        this.expForNextLvl = this.level * this.level * expStep;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setExperienceFromKilling(int experienceFromKilling) {
        this.experienceFromKilling = experienceFromKilling;
    }
    public void setLineOfSight(int lineOfSight) {
        this.lineOfSight = lineOfSight;
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
    public Color getGraphics() {
        return graphics;
    }
    public void setGraphics(Color graphics) {
        this.graphics = graphics;
    }
    public void setGraphics(String graphicsString) {
        this.graphics = decodeGraphics(graphicsString);
    }
    //tmp
    private Color decodeGraphics(String graphicsString){
        switch (graphicsString) {
            case "PINK":
                return Color.PINK;
            case "GREY":
                return Color.GREY;
            case "BROWN":
                return Color.BROWN;
            case "BLUE":
                return Color.BLUE;
            case "DARKGREEN":
                return Color.DARKGREEN;
            case "BLACK":
                return Color.BLACK;
        }
        return Color.YELLOW;
    }

    public int getInitTileX() {
        return initTileX;
    }
    public void setInitTileX(int initTileX) {
        this.initTileX = initTileX;
    }
    public int getInitTileY() {
        return initTileY;
    }
    public void setInitTileY(int initTileY) {
        this.initTileY = initTileY;
    }
}
