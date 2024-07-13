package io.github.v0ncent.Engine.JDateProject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;

public record GameJSON(File startingScript, String name) {
    // Constructor
    public GameJSON(@JsonProperty("starting_script") File startingScript, @JsonProperty("name") String name) {
        this.startingScript = startingScript;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GameJSON{" +
                "starting_script=" + startingScript +
                ", name='" + name + '\'' +
                '}';
    }
}

