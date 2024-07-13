package io.github.v0ncent.Exceptions;

/**
 * The given JDate project is not useable by the engine due to a variety of reasons specified by the project loader process.
 * @see io.github.v0ncent.MainWindow.ProjectLoader
 * @see io.github.v0ncent.Engine.JDateProject.JDateProject
 * @see io.github.v0ncent.Engine.JDateEngine
 */
public final class InvalidProject extends Exception {
    public InvalidProject(String message) {
        super(message);
    }
}
