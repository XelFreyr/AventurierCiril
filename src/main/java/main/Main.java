package main;

import adventurer.Adventurer;
import forestMap.ForestMap;
import java.io.IOException;

class Main
{

    static void main(String[] args) throws IOException {
        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        //Map and Adventurer Setup
        forest.setForestLines(forest.getMap("src/main/resources/cartev2.txt"));
        adventurer.getInstructions("src/main/resources/instructions.txt");

        adventurer.setX(adventurer.getStartingCoordinates()[0]);
        adventurer.setY(adventurer.getStartingCoordinates()[1]);

        if (adventurer.getX() >= forest.getWidth() || adventurer.getX() < 0)
            throw new IllegalArgumentException("Adventurer spawns out of bounds");
        if (adventurer.getY() >= forest.getHeight() || adventurer.getY() < 0)
            throw new IllegalArgumentException("Adventurer spawns out of bounds");
        if (!(adventurer.checkTileType(forest.getForestLines().get(adventurer.getY()).charAt(adventurer.getX()))))
            throw new IllegalArgumentException("Adventurer spawns on a forest tile");

        //Main Loop
        for (String line: forest.getForestLines()){
            System.out.println(line);
        }

        for (int i = 0; i < adventurer.getAdventurerInstructions().length; i++) {
            int[] nextTile = {adventurer.getX(), adventurer.getY()};
            switch(adventurer.getAdventurerInstructions()[i]){
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
            if (checkCoordinates(nextTile, forest.getHeight(), forest.getWidth())
                    && adventurer.checkTileType(forest.getForestLines().get(nextTile[1]).charAt(nextTile[0]))
            ) {
                adventurer.setX(nextTile[0]);
                adventurer.setY(nextTile[1]);
            }
            adventurer.sayHello();
        }

        System.out.println("final coordinates: " + adventurer.getX() + "," + adventurer.getY());
    }

    public static boolean checkCoordinates(int[] nextTileCoordinates, int mapHeight, int mapLength){
        return nextTileCoordinates[0] >= 0
                && nextTileCoordinates[0] < mapLength
                && nextTileCoordinates[1] >= 0
                && nextTileCoordinates[1] < mapHeight;

    }
}
