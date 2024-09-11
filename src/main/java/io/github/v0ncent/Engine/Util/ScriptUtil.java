package io.github.v0ncent.Engine.Util;

import io.github.v0ncent.Constants;

public final class ScriptUtil {
    public static boolean isFileEndingKeyword(String keyword) {
        switch (keyword) {
            case Constants.ScriptKeyWords.END_OF_FILE_FUNCTION -> {
                return true;
            }
            default -> {
                return false;
            }
        }

    }

}
