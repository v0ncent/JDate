package io.github.v0ncent.Engine.JDateProject;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameJSON(String startingScript, String name) {
    public GameJSON(@JsonProperty("starting_script") String startingScript, @JsonProperty("name") String name) {
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

