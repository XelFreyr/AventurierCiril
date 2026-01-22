package adventurer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Adventurer
{
    private int x;
    private int y;

    private int[] startingCoordinates;
    private char[] adventurerInstructions;


    public void sayHello(){
        System.out.println("I'm an adventurer");
        System.out.println("I am at " + this.x + "," + this.y);
    }

    public void getInstructions(String instructionsPath) throws IOException {
        List<String> instructionsLines;
        instructionsLines = Files.readAllLines(Paths.get(instructionsPath), StandardCharsets.UTF_8);
        if (instructionsLines.isEmpty()) {
            throw new IllegalArgumentException("Instructions file is empty: " + instructionsPath);
        }

        try {
        this.setStartingCoordinates(Arrays.stream(instructionsLines.get(0).split(","))
                .mapToInt(Integer::parseInt)
                .toArray());
        this.setAdventurerInstructions(instructionsLines.get(1).toCharArray());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid instructions file/format");
        }
    }

    public boolean checkTileType(char tile){
        return tile == ' ';
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getStartingCoordinates() {
        return startingCoordinates;
    }

    public void setStartingCoordinates(int[] startingCoordinates) {
        this.startingCoordinates = startingCoordinates;
    }

    public char[] getAdventurerInstructions() {
        return adventurerInstructions;
    }

    public void setAdventurerInstructions(char[] adventurerInstructions) {
        this.adventurerInstructions = adventurerInstructions;
    }
}
