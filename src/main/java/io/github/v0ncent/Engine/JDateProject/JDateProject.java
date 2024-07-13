package io.github.v0ncent.Engine.JDateProject;

import java.io.File;
import java.util.Arrays;

/**
 * An in code representation of an actual JDate Project.
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
    private final Class<?> functions;

    private final File[] otherFiles;

    private final File projectDirectory;

    private final String projectName;

    public JDateProject(File[] assets, File[] music, File[] saves, File[] src, File[] scripts, File gameJSONasFile, GameJSON gameJSON, File functionsAsFile, Class<?> functions, File[] otherFiles, File projectDirectory) {
        this.assets = assets;
        this.music = music;
        this.saves = saves;
        this.src = src;
        this.scripts = scripts;
        this.gameJSONasFile = gameJSONasFile;
        this.gameJSON = gameJSON;
        this.functionsAsFile = functionsAsFile;
        this.functions = functions;
        this.otherFiles = otherFiles;
        this.projectDirectory = projectDirectory;

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

    public Class<?> getFunctions() {
        return functions;
    }

    public String getProjectName() {
        return projectName;
    }

    public File[] getOtherFiles() {
        return otherFiles;
    }

    public File getProjectDirectory() {
        return projectDirectory;
    }

    @Override
    public String toString() {
        return "JDateProject{" +
                "assets=" + Arrays.toString(assets) +
                ", music=" + Arrays.toString(music) +
                ", saves=" + Arrays.toString(saves) +
                ", src=" + Arrays.toString(src) +
                ", scripts=" + Arrays.toString(scripts) +
                ", gameJSONasFile=" + gameJSONasFile +
                ", gameJSON=" + gameJSON +
                ", functionsAsFile=" + functionsAsFile +
                ", functions=" + functions +
                ", otherFiles=" + Arrays.toString(otherFiles) +
                ", projectDirectory=" + projectDirectory +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
