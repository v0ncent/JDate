package io.github.v0ncent.Engine.Util;

import io.github.v0ncent.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility for working with JDate Projects.
 */
public class JDateProjectUtil {
    // All of these are a required element of a JDate project.
    private boolean hasAssetsFolder = false;
    private boolean hasMusicFolder = false;
    private boolean hasSRCFolder = false;
    private boolean hasSavesFolder = false;
    private boolean srcFolderHasScriptsFolder = false;
    private boolean srcContainsGameJson = false;
    private boolean srcContainsFunctionsJavaFile = false;

    public boolean isJDateProject() {
        return hasAssetsFolder && hasMusicFolder && hasSRCFolder && srcFolderHasScriptsFolder && srcContainsGameJson && hasSavesFolder && srcContainsFunctionsJavaFile;
    }

    public boolean isHasSavesFolder() {
        return hasSavesFolder;
    }

    public void setHasSavesFolder(boolean hasSavesFolder) {
        this.hasSavesFolder = hasSavesFolder;
    }

    public boolean isHasAssetsFolder() {
        return hasAssetsFolder;
    }

    public void setHasAssetsFolder(boolean hasAssetsFolder) {
        this.hasAssetsFolder = hasAssetsFolder;
    }

    public boolean isHasMusicFolder() {
        return hasMusicFolder;
    }

    public void setHasMusicFolder(boolean hasMusicFolder) {
        this.hasMusicFolder = hasMusicFolder;
    }

    public boolean isHasSRCFolder() {
        return hasSRCFolder;
    }

    public void setHasSRCFolder(boolean hasSRCFolder) {
        this.hasSRCFolder = hasSRCFolder;
    }

    public boolean isSrcFolderHasScriptsFolder() {
        return srcFolderHasScriptsFolder;
    }

    public void setSrcFolderHasScriptsFolder(boolean srcFolderHasScriptsFolder) {
        this.srcFolderHasScriptsFolder = srcFolderHasScriptsFolder;
    }

    public boolean isSrcContainsGameJson() {
        return srcContainsGameJson;
    }

    public void setSrcContainsGameJson(boolean srcContainsGameJson) {
        this.srcContainsGameJson = srcContainsGameJson;
    }

    public boolean isSrcContainsFunctionsJavaFile() {
        return srcContainsFunctionsJavaFile;
    }

    public void setSrcContainsFunctionsJavaFile(boolean srcContainsFunctionsJavaFile) {
        this.srcContainsFunctionsJavaFile = srcContainsFunctionsJavaFile;
    }

    /**
     * Determines the missing files / directories of a passed JDate project.
     * @param project Project to determine missing files / directories of.
     * @return A string containing the missing JDate files seperated with a , .
     */
    public static String getMissingElement(JDateProjectUtil project) {
        final List<String> missingItems = new ArrayList<>();

        if (!project.hasAssetsFolder) {
            missingItems.add(Constants.FileContent.ASSETS_DIRECTORY_NAME);
        }

        if (!project.hasMusicFolder) {
            missingItems.add(Constants.FileContent.MUSIC_DIRECTORY_NAME);
        }

        if (!project.hasSRCFolder) {
            missingItems.add(Constants.FileContent.SRC_DIRECTORY_NAME);
        }

        if (!project.srcFolderHasScriptsFolder) {
            missingItems.add(Constants.FileContent.SCRIPT_DIRECTORY_PATH);
        }

        if (!project.srcContainsGameJson) {
            missingItems.add(Constants.FileContent.GAME_JSON_FILE_PATH);
        }

        if (!project.hasSavesFolder) {
            missingItems.add(Constants.FileContent.SAVES_DIRECTORY_NAME);
        }

        if (!project.srcContainsFunctionsJavaFile) {
            missingItems.add(Constants.FileContent.FUNCTIONS_JAVA_FILE_PATH);
        }

        if (!missingItems.isEmpty()) {
            return String.join(", ", missingItems);
        }

        // this should likely never run due to the use cases of this function :0
        return "IT AINT MISSING SHIT";
    }
}
