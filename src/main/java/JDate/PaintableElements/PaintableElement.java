package JDate.PaintableElements;

import org.slf4j.Logger;

public interface PaintableElement {
   Logger getLogger();

   default String getElementName() {
      return this.getClass().getName();
   }
}
