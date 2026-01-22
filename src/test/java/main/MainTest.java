package main;


import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void mainIT1() throws Exception {
        String mapContent =
                "###    ######    ###\n" +
                        "###      ##      ###\n" +
                        "##     ##  ##     ##\n" +
                        "#      ##  ##      #\n" +
                        "##                ##\n" +
                        "#####          #####\n" +
                        "###### ##  ##  #####\n" +
                        " #     ######     # \n" +
                        "     ########       \n" +
                        "    ############    \n" +
                        "    ############    \n" +
                        "     ########      #\n" +
                        " #     ######     ##\n" +
                        "###### ##  ## ######\n" +
                        "#####          #####\n" +
                        "##                ##\n" +
                        "#   ## #    # ##   #\n" +
                        "##   ##      ##   ##\n" +
                        "###    #    #    ###\n" +
                        "###    ######    ###\n";

        Path mapFile = Files.createTempFile("carte", ".txt");
        Files.writeString(mapFile, mapContent, StandardCharsets.UTF_8);

        Path instructionsFile = Files.createTempFile("instructions", ".txt");
        Files.writeString(instructionsFile, "3,0\nSSSSEEEEEENN\n", StandardCharsets.UTF_8);

        String[] args = {mapFile.toString(), instructionsFile.toString()};

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        Main.main(args);
        assertEquals("Le personnage doit se trouver en (9,2)", out.toString());

    }

    @Test
    void mainIT2() throws Exception {
        String mapContent =
                "###    ######    ###\n" +
                        "###      ##      ###\n" +
                        "##     ##  ##     ##\n" +
                        "#      ##  ##      #\n" +
                        "##                ##\n" +
                        "#####          #####\n" +
                        "###### ##  ##  #####\n" +
                        " #     ######     # \n" +
                        "     ########       \n" +
                        "    ############    \n" +
                        "    ############    \n" +
                        "     ########      #\n" +
                        " #     ######     ##\n" +
                        "###### ##  ## ######\n" +
                        "#####          #####\n" +
                        "##                ##\n" +
                        "#   ## #    # ##   #\n" +
                        "##   ##      ##   ##\n" +
                        "###    #    #    ###\n" +
                        "###    ######    ###\n";

        Path mapFile = Files.createTempFile("carte", ".txt");
        Files.writeString(mapFile, mapContent, StandardCharsets.UTF_8);

        Path instructionsFile = Files.createTempFile("instructions", ".txt");
        Files.writeString(instructionsFile, "6,7\nOONOOOSSO\n", StandardCharsets.UTF_8);

        String[] args = {mapFile.toString(), instructionsFile.toString()};

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        Main.main(args);
        assertEquals("Le personnage doit se trouver en (1,9)", out.toString());

    }

}