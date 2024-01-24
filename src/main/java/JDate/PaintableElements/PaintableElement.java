package JDate.PaintableElements;

import org.slf4j.Logger;

import java.awt.*;
import java.util.HashMap;

public interface PaintableElement {
   HashMap<String, Image> sprites = new HashMap<>();

   Logger getLogger();
   ELEMENT_TYPE getElementType();
   void addSprites();

   default String getElementName() {
      return this.getClass().getName();
   }

   default Image getSprite(String spriteName) {
      return sprites.get(spriteName);
   }

   default void addSprite(String spriteName, Image source) {
      sprites.put(spriteName,source);
   }

   enum ELEMENT_TYPE {
      LABEL,
      PANEL
   }
}
