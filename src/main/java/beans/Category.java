package beans;

public enum Category {
    FOOD,
    ELECTRICITY,
    RESTAURANT,
    VACATION;

    public int DBValue() {
        return this.ordinal() + 1;
    }

}