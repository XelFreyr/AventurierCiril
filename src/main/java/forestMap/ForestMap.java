package forestMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ForestMap {

    private List<String> forestLines;
    private int height;
    private int width;

    public List<String> getMap(String mapPath) throws IOException {
        List<String> mapLines = Files.readAllLines(Paths.get(mapPath), StandardCharsets.UTF_8);
        if (mapLines.isEmpty()) {
            throw new IOException("Map file is empty: " + mapPath);
        }
        checkMap(mapLines);
        return mapLines;
    }

    public void checkMap(List<String> forestLines) throws IllegalArgumentException {
        int firstLineLength = forestLines.get(0).length();
        for (String line: forestLines){
            for (char tile :  line.toCharArray()) {
                if (!(tile == '#' || tile == ' ')){
                    throw new IllegalArgumentException("Map has undefined character");
                }
            }
            if (line.length() != firstLineLength) {throw new IllegalArgumentException("Map has uneven width");}
        }
        setForestLines(forestLines);
        setHeight(forestLines.size());
        setWidth(forestLines.get(0).length());
    }

    public boolean isTileWithinMap(int[] nextTileCoordinates){
        return nextTileCoordinates[0] >= 0
                && nextTileCoordinates[0] < this.getWidth()
                && nextTileCoordinates[1] >= 0
                && nextTileCoordinates[1] < this.getHeight();
    }

    public char getTileType(int[] tile){
        return this.forestLines.get(tile[1]).charAt(tile[0]);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getForestLines() {
        return forestLines;
    }

    public void setForestLines(List<String> forestLines) {
        this.forestLines = forestLines;
    }
}
