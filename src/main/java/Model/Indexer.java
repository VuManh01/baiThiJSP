package Model;

public class Indexer {
    private int id;
    private String name;
    private float valueMin;
    private float valueMax;

    public Indexer() {
    }

    public Indexer(int id, String name, float valueMin, float valueMax) {
        this.id = id;
        this.name = name;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValueMin() {
        return valueMin;
    }

    public void setValueMin(float valueMin) {
        this.valueMin = valueMin;
    }

    public float getValueMax() {
        return valueMax;
    }

    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }
}
