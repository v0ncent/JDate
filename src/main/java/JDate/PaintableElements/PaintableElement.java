package JDate.PaintableElements;

import org.slf4j.Logger;

public interface PaintableElement {
   Logger getLogger();
   ELEMENT_TYPE getElementType();

   default String getElementName() {
      return this.getClass().getName();
   }

   enum ELEMENT_TYPE {
      LABEL,
      PANEL
   }
}
