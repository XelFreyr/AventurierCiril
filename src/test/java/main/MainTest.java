package main;

import adventurer.Adventurer;
import forestMap.ForestMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class MainTest {

    @Test
    void playerCantSpawnInWall() throws IOException {
        ForestMap forest = new ForestMap();
        Adventurer adventurer = new Adventurer();

        adventurer.setX(0);
        adventurer.setY(0);

        Path mapFile = Files.createTempFile("map", ".txt");
        Files.writeString(mapFile, "###\n###\n###\n###", StandardCharsets.UTF_8);
        List<String> lines = forest.getMap(mapFile.toString());


        if (!(adventurer.isTileWalkable(forest.getForestLines().get(adventurer.getY()).charAt(adventurer.getX()))))
            throw new IllegalArgumentException("Adventurer spawns on a forest tile");


    }
}