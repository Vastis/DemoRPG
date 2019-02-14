package gameCore;

import java.util.concurrent.Semaphore;

public class GameParams {

    public static Semaphore semaphore = new Semaphore(1);

    public static final double
        TILE_SIZE = 40;
    public static final int
        TICKS_PER_SECOND = 60,
        ITEM_SLOT_SIZE = 40,
        MAX_COLS_IN_EQUIPMENT_DISPLAY = 4;

    public static final String
        WORLD_MAP_FILE_PATH = "res/worldGeneration/world.map",
        MONSTERS_MATRIX_FILE_PATH = "res/worldGeneration/monsters.matrix",
        MONSTERS_XML_DIR_PATH = "res/entitiesDefinitions/monstersDefinitions",
        USER_XML_FILE_PATH = "res/entitiesDefinitions/user.xml";
}
