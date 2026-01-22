package adventurer;

import forestMap.ForestMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
    void isTileWalkable() {
    }
}