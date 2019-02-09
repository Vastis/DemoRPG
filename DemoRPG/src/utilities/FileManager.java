package utilities;

import java.io.*;

public class FileManager {

    private static String readFileAsLines(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        new File(filePath)));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static int[][] readFileAsMatrix(String filePath) throws IOException {
        //Left file format exceptions unhandled
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        new File(filePath)));
        String line = bufferedReader.readLine();
        String[] firstLine = line.split(" ");
        int[][] map = new int[Integer.parseInt(firstLine[0])][Integer.parseInt(firstLine[1])];
        int row = 0;
        while((line = bufferedReader.readLine()) != null){
           String[] mapStringValues = line.split(" ");
           for(int i = 0; i < map[0].length; i++)
               map[row][i] = Integer.parseInt(mapStringValues[i]);
           row++;
        }
        return map;
    }


    public static void exportToFile(String fileName, String string){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(string);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
