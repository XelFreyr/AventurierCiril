package forestMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ForestMap {

    //public List<String> forestLines;
    private List<String> forestLines;
    private int height;
    private int width;

    public List<String> getMap(String mapPath) throws IOException {
        List<String> mapLines = Files.readAllLines(Paths.get(mapPath), StandardCharsets.UTF_8);
        if (mapLines.isEmpty()) {
            throw new IllegalArgumentException("Map file is empty: " + mapPath);
        }
        checkMap(mapLines);
        return mapLines;
    }

    public void checkMap(List<String> forestLines) {
        for (String line: forestLines){
            for (char tile :  line.toCharArray()) {
                if (!(tile == '#' || tile == ' ')){
                    throw new IllegalArgumentException("Map has undefined character");
                }
            }
        }
        setHeight(forestLines.size());
        setWidth(forestLines.get(0).length());
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
