package gfx;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {
    private final static String PATH_TO_SPRITES = "/sprites";
    private final Map<String, Sprite> units;

    public SpriteLibrary() {
        units = new HashMap<>();
        loadSpriteFromDisk();
    }

    public Map<String, Sprite> getUnits() {
        return units;
    }

    private void loadSpriteFromDisk() {
        String[] imagesInFolder = getImagesInFolder();
        Sprite sprite = new Sprite();

        for (String imageName : imagesInFolder) {
            sprite.addSprite(ImageUtils.loadImage(PATH_TO_SPRITES + "/" + imageName));
            units.put("player", sprite);
        }
    }

    private String[] getImagesInFolder() {
        URL resource = SpriteLibrary.class.getResource(SpriteLibrary.PATH_TO_SPRITES);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

}
