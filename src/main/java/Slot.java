public class Slot {
    private Color color;
    private boolean catchable;

    public Slot(Color color){
        this.color=color;
    }

    public boolean isCatchable() {
        return catchable;
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
}

