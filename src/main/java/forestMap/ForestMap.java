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
        isMapFormatValid(mapLines);

        setForestLines(mapLines);
        setHeight(mapLines.size());
        setWidth(mapLines.get(0).length());
        return mapLines;
    }

    public void isMapFormatValid(List<String> forestLines) throws IllegalArgumentException {
        int firstLineLength = forestLines.get(0).length();
        for (String line: forestLines){
            isCharacterUndefined(line);
            isMapLengthEven(line, firstLineLength);
        }
    }
    public void isCharacterUndefined(String line) throws IllegalArgumentException {
        for (char tile :  line.toCharArray()) {
            if (!(tile == '#' || tile == ' ')){
                throw new IllegalArgumentException("Map has undefined character");
            }
        }
    }

    public void isMapLengthEven(String line, int firstLineLength) throws IllegalArgumentException {
        for (char tile :  line.toCharArray()) {
            if (line.length() != firstLineLength) {
                throw new IllegalArgumentException("Map has uneven width");
            }
        }
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
