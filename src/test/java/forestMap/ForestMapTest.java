package forestMap;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForestMapTest {

    @Test
    void checkForEmptyMap() throws IOException {
        ForestMap forest = new ForestMap();
        Path badMapPath = Files.createTempFile("badmap", ".txt");
        Files.writeString(badMapPath, "", StandardCharsets.UTF_8);

        assertThrows(IOException.class, () -> forest.getMap(badMapPath.toString()));
    }

    @Test
    void getCorrectMapSize() throws IOException {
        ForestMap forest = new ForestMap();

        Path goodMapFile = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapFile, "###\n# #\n   \n# #", StandardCharsets.UTF_8);

        List<String> lines = forest.getMap(goodMapFile.toString());

        assertEquals(4, lines.size());
        assertEquals(3, forest.getWidth());
        assertEquals(4, forest.getHeight());
    }

    @Test
    void checkForInvalidMap() throws IOException {
        ForestMap forest = new ForestMap();
        Path badMapPath = Files.createTempFile("badmap", ".txt");
        Files.writeString(badMapPath, "##X\n", StandardCharsets.UTF_8);

        List<String> mapLines = Files.readAllLines((badMapPath), StandardCharsets.UTF_8);

        assertThrows( IllegalArgumentException.class, () -> forest.isMapFormatValid(mapLines));
    }

    @Test
    void checkForUnevenMap() throws IOException {
        ForestMap forest = new ForestMap();
        Path badMapPath = Files.createTempFile("badmap", ".txt");
        Files.writeString(badMapPath, "###\n# #\n##", StandardCharsets.UTF_8);

        List<String> mapLines = Files.readAllLines((badMapPath), StandardCharsets.UTF_8);

        assertThrows( IllegalArgumentException.class, () -> forest.isMapFormatValid(mapLines));
    }

    @Test
    void checkForCorrectTileType() throws IOException {
        ForestMap forest = new ForestMap();
        Path goodMapPath = Files.createTempFile("goodmap", ".txt");
        Files.writeString(goodMapPath, "###\n   \n", StandardCharsets.UTF_8);

        forest.setForestLines(forest.getMap(goodMapPath.toString()));

        int[] tileToCheck = {0,0};
        assertEquals('#', forest.getTileType(tileToCheck));

        tileToCheck[1] = 1;
        assertEquals(' ', forest.getTileType(tileToCheck));
    }
}
