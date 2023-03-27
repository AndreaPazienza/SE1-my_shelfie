public class Slot {
    private Color color;
    private Type type;
    private boolean catchable;

    public Slot(Color color){
        this.color=color;
    }

    public boolean isCatchable() {
        return catchable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCatchable(boolean catchable) {
        this.catchable = catchable;
    }

    public void setGrey(){ // la tessera è stata presa
        this.color=Color.GREY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }
}

