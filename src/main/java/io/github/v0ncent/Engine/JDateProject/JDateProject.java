package io.github.v0ncent.Engine.JDateProject;

import java.io.File;

/**
 * An in code representation of a actual JDate Project.
 */
public class JDateProject {
    private final File[] assets;
    private final File[] music;
    private final File[] saves;
    private final File[] src;
    private final File[] scripts;

    private final File gameJSONasFile;
    private final GameJSON gameJSON;

    private final File functionsAsFile;
    private final Functions functions;

    private final String projectName;

    public JDateProject(File[] assets, File[] music, File[] saves, File[] src, File[] scripts, File gameJSONasFile, GameJSON gameJSON, File functionsAsFile, Functions functions) {
        this.assets = assets;
        this.music = music;
        this.saves = saves;
        this.src = src;
        this.scripts = scripts;
        this.gameJSONasFile = gameJSONasFile;
        this.gameJSON = gameJSON;
        this.functionsAsFile = functionsAsFile;
        this.functions = functions;

        this.projectName = this.gameJSON.name();
    }

    public File[] getAssets() {
        return assets;
    }

    public File[] getMusic() {
        return music;
    }

    public File[] getSaves() {
        return saves;
    }

    public File[] getSrc() {
        return src;
    }

    public File[] getScripts() {
        return scripts;
    }

    public File getGameJSONasFile() {
        return gameJSONasFile;
    }

    public GameJSON getGameJSON() {
        return gameJSON;
    }

    public File getFunctionsAsFile() {
        return functionsAsFile;
    }

    public Functions getFunctions() {
        return functions;
    }

    public String getProjectName() {
        return projectName;
    }
}
