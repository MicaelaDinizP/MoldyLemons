package devandroid.micaela.moldylemons;

public enum Demographic {
    SHOUNEN(1, "Shounen", "Boys (10–18 years)", "Action, adventure, friendship"),
    SHOUJO(2, "Shoujo", "Girls (10–18 years)", "Romance, fantasy, emotions"),
    SEINEN(3, "Seinen", "Young adult men (18+)", "Mature themes, violence, philosophy"),
    JOSEI(4, "Josei", "Young adult women (18+)", "Realistic relationships, daily life"),
    KODOMO(5, "Kodomo", "Children", "Simple stories, educational");

    private final int id;
    private final String name;
    private final String audience;
    private final String description;

    Demographic(int id, String name, String audience, String description) {
        this.id = id;
        this.name = name;
        this.audience = audience;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAudience() {
        return this.audience;
    }

    public String getDescription() {
        return this.description;
    }
}
