package main;

import adventurer.Adventurer;
import forestMap.ForestMap;
import java.io.IOException;

class Main
{

     public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IOException("More than 2 files added to arguments");
        }

        Adventurer adventurer = new Adventurer();
        ForestMap forest = new ForestMap();

        //Map and Adventurer Setup

        forest.setForestLines(forest.getMap(args[0]));
        adventurer.getInstructions(args[1]);

        adventurer.setX(adventurer.getStartingCoordinates()[0]);
        adventurer.setY(adventurer.getStartingCoordinates()[1]);

        adventurer.checkSpawnPoint(
                forest.getWidth(),
                forest.getHeight(),
                forest.getForestLines());

        //Main Loop
        for (int i = 0; i < adventurer.getAdventurerInstructions().length; i++) {

            int[] currentTile = {adventurer.getX(), adventurer.getY()};

            int[] nextTile = adventurer.choseNextCoordinates(currentTile, adventurer.getAdventurerInstructions()[i]);
            if (forest.isTileWithinMap(nextTile)
                    && adventurer.isTileWalkable(forest.getTileType(nextTile))
            ) {
                adventurer.setX(nextTile[0]);
                adventurer.setY(nextTile[1]);
            }
        }

        System.out.println("Le personnage doit se trouver en (" + adventurer.getX() + "," + adventurer.getY() + ")");
    }
}
