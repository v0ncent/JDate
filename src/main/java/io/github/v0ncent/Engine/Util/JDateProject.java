package io.github.v0ncent.Engine.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * A code representation of a generated JDate Project and utilities for working with them.
 */
public class JDateProject {
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
    public static String getMissingElement(JDateProject project) {
        final List<String> missingItems = new ArrayList<>();

        if (!project.hasAssetsFolder) {
            missingItems.add("Assets");
        }

        if (!project.hasMusicFolder) {
            missingItems.add("Music");
        }

        if (!project.hasSRCFolder) {
            missingItems.add("src");
        }

        if (!project.srcFolderHasScriptsFolder) {
            missingItems.add("src/scripts");
        }

        if (!project.srcContainsGameJson) {
            missingItems.add("src/game.json");
        }

        if (!project.hasSavesFolder) {
            missingItems.add("Saves");
        }

        if (!project.srcContainsFunctionsJavaFile) {
            missingItems.add("src/Functions.java");
        }

        if (!missingItems.isEmpty()) {
            return String.join(", ", missingItems);
        }

        // this should likely never run due to the use cases of this function :0
        return "IT AINT MISSING SHIT";
    }
}
