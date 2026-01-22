package adventurer;

import forestMap.ForestMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Test
    void checkForEmptyInstructions() throws IOException {
        Adventurer adventurer = new Adventurer();
        Path badInstructions = Files.createTempFile("badinstructions", ".txt");
        Files.writeString(badInstructions, "", StandardCharsets.UTF_8);

        assertThrows(IllegalArgumentException.class, () -> adventurer.getInstructions(badInstructions.toString()));
    }

    @Test
    void checkForValidInstructions() throws IOException {
        Adventurer adventurer = new Adventurer();
        Path goodInstructions = Files.createTempFile("goodinstructions", ".txt");
        Files.writeString(goodInstructions, "0,0\nSSSEEE", StandardCharsets.UTF_8);

        adventurer.getInstructions(goodInstructions.toString());

        assertArrayEquals(new int[]{0, 0}, adventurer.getStartingCoordinates());
        assertArrayEquals(new char[]{'S', 'S', 'S', 'E', 'E', 'E'}, adventurer.getAdventurerInstructions());
    }

    @Test
    void checkForSpawnInTrees() throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n   \n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());
        adventurer.setX(0);
        adventurer.setY(0);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));

        adventurer.setX(-1);
        adventurer.setY(0);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));
    }

    @Test
    void checkForSpawnOutOfBoundXLow() throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n   \n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());

        adventurer.setX(-1);
        adventurer.setY(0);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));
    }

    @Test
    void checkForSpawnOutOfBoundYLow() throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n   \n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());

        adventurer.setX(0);
        adventurer.setY(-1);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));
    }

    void checkForSpawnOutOfBoundXHigh() throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n   \n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());

        adventurer.setX(4);
        adventurer.setY(0);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));
    }

    @Test
    void checkForSpawnOutOfBoundYHigh() throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n   \n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());

        adventurer.setX(0);
        adventurer.setY(4);
        assertThrows(IllegalArgumentException.class, () -> adventurer.isSpawnPointValid(forest.getWidth(), forest.getHeight(), lines));
    }
}