package io.ven.game;

public class Player {
    private final String name;
    private final String id;
    int currentPosition;

    public Player(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
