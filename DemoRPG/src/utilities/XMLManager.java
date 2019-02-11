package utilities;

import gameCore.GameParams;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesAttributes.EntityAttributes;
import gameEntitiesAttributes.MonsterAttributes;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLManager {

    private static Document openXMLFile(String path) throws ParserConfigurationException, IOException, SAXException {
        return openXMLFile(new File(path));
    }
    private static Document openXMLFile(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        return document;
    }
    private static ArrayList<Document> getXMLFilesInDir(String dirPath) throws ParserConfigurationException, IOException, SAXException {
        File dir = new File(dirPath);
        ArrayList<Document> files = new ArrayList<>();
        if(dir.isDirectory()){
            String[] filesInDirPaths = dir.list();
            for(String filePath : filesInDirPaths) {
                Document document = openXMLFile(new File(dir, filePath));
                files.add(document);
            }
        }
        return files;
    }
    private static String getNodeValueByTagName(Document document, String tagName){
        return document.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue().trim();
    }

    private static void decodeMonster(Document document, MonsterAttributes[] monstersDefinitions){
        MonsterAttributes monsterDefinition = new MonsterAttributes();
        int monsterId = Integer.parseInt(getNodeValueByTagName(document, "id"));

        monsterDefinition.setName(getNodeValueByTagName(document, "name"));
        monsterDefinition.setType(getNodeValueByTagName(document, "type"));
        monsterDefinition.setExperienceFromKilling(
                Integer.parseInt(getNodeValueByTagName(document, "experienceFromKilling"))
        );
        monsterDefinition.setHealth(
                Integer.parseInt(getNodeValueByTagName(document, "health"))
        );
        monsterDefinition.setSoulPoints(
                Integer.parseInt(getNodeValueByTagName(document, "soulPoints"))
        );
        monsterDefinition.setLineOfSight(
                Integer.parseInt(getNodeValueByTagName(document, "lineOfSight"))
        );
        monsterDefinition.setSpeed(
                Double.parseDouble(getNodeValueByTagName(document, "speed"))
        );
        monsterDefinition.setStrength(
                Double.parseDouble(getNodeValueByTagName(document, "strength"))
        );
        monsterDefinition.setAgility(
                Double.parseDouble(getNodeValueByTagName(document, "agility"))
        );
        monsterDefinition.setIntelligence(
                Double.parseDouble(getNodeValueByTagName(document, "intelligence"))
        );
        monsterDefinition.setLuck(
                Double.parseDouble(getNodeValueByTagName(document, "luck"))
        );
        monsterDefinition.setPhysicalDefence(
                Double.parseDouble(getNodeValueByTagName(document, "physicalDefence"))
        );
        monsterDefinition.setMagicDefence(
                Double.parseDouble(getNodeValueByTagName(document, "magicDefence"))
        );
        monsterDefinition.setGraphics(getNodeValueByTagName(document, "graphics"));

        monstersDefinitions[monsterId] = monsterDefinition;
    }
    public static MonsterAttributes[] loadMonsters(){
        MonsterAttributes[] monstersDefinitions = null;
        try {
            ArrayList<Document> files = getXMLFilesInDir(GameParams.MONSTERS_XML_DIR_PATH);
            monstersDefinitions = new MonsterAttributes[files.size() + 1];
            for(Document document : files)
                decodeMonster(document, monstersDefinitions);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return monstersDefinitions;
    }

    private static void decodeUser(Document document, CharacterAttributes userDefinition){
        //int userId = Integer.parseInt(getNodeValueByTagName(document, "id"));
        userDefinition.setName(getNodeValueByTagName(document, "name"));
        userDefinition.setType(getNodeValueByTagName(document, "type"));
        userDefinition.setTotalExperience(
                Integer.parseInt(getNodeValueByTagName(document, "totalExperience"))
        );
        userDefinition.setInitTileX(
                Integer.parseInt(getNodeValueByTagName(document, "tileX"))
        );
        userDefinition.setInitTileY(
                Integer.parseInt(getNodeValueByTagName(document, "tileY"))
        );
        userDefinition.setHealth(
                Integer.parseInt(getNodeValueByTagName(document, "health"))
        );
        userDefinition.setSoulPoints(
                Integer.parseInt(getNodeValueByTagName(document, "soulPoints"))
        );
        userDefinition.setLineOfSight(
                Integer.parseInt(getNodeValueByTagName(document, "lineOfSight"))
        );
        userDefinition.setSpeed(
                Double.parseDouble(getNodeValueByTagName(document, "speed"))
        );
        userDefinition.setStrength(
                Double.parseDouble(getNodeValueByTagName(document, "strength"))
        );
        userDefinition.setAgility(
                Double.parseDouble(getNodeValueByTagName(document, "agility"))
        );
        userDefinition.setIntelligence(
                Double.parseDouble(getNodeValueByTagName(document, "intelligence"))
        );
        userDefinition.setLuck(
                Double.parseDouble(getNodeValueByTagName(document, "luck"))
        );
        userDefinition.setPhysicalDefence(
                Double.parseDouble(getNodeValueByTagName(document, "physicalDefence"))
        );
        userDefinition.setMagicDefence(
                Double.parseDouble(getNodeValueByTagName(document, "magicDefence"))
        );
        userDefinition.setGraphics(getNodeValueByTagName(document, "graphics"));
    }
    public static CharacterAttributes loadUser(){
        CharacterAttributes userDefinition = new CharacterAttributes();
        try {
            Document document = openXMLFile(GameParams.USER_XML_FILE_PATH);
            decodeUser(document, userDefinition);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return userDefinition;
    }
}
