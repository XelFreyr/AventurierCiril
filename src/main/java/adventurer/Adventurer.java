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


    public void getInstructions(String instructionsPath) throws IOException {
        List<String> instructionsLines;
        instructionsLines = Files.readAllLines(Paths.get(instructionsPath), StandardCharsets.UTF_8);
        if (instructionsLines.size() != 2) {
            throw new IllegalArgumentException("Instruction files empty or is not coordinate and directions: " + instructionsPath);
        }

        try {
            this.setStartingCoordinates(Arrays.stream(instructionsLines.get(0).split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid coordinates format");
        }
        if (getStartingCoordinates().length != 2) {
            throw new IllegalArgumentException("Invalid number of coordinates");
        }
        try {
            this.setAdventurerInstructions(instructionsLines.get(1).toCharArray());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid instructions format");
        }
    }

    public void isSpawnPointValid(int width, int height, List<String> tilemap) throws IllegalArgumentException{
        if (this.getX() >= width || this.getX() < 0)
            throw new IllegalArgumentException("Adventurer spawns out of bounds");
        if (this.getY() >= height || this.getY() < 0)
            throw new IllegalArgumentException("Adventurer spawns out of bounds");
        if (!this.isTileWalkable(tilemap.get(this.getY()).charAt(this.getX())))
            throw new IllegalArgumentException("Adventurer spawns on a forest tile");
    }

    public int[] choseNextCoordinates(int[] nextTile, char currentInstruction){
        switch(currentInstruction){
            case 'N':
                nextTile[1]--;
                break;

            case 'E':
                nextTile[0]++;
                break;

            case 'S':
                nextTile[1]++;
                break;

            case 'O':
                nextTile[0]--;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
        return nextTile;
    }

    public boolean isTileWalkable(char tile){
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
