package MODEL;

import java.io.Serializable;

public class Slot implements Serializable {
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
    public void fillType(int numberType){
        switch(numberType % 3) {
            case 0-> setType(Type.TYPE1);
            case 1-> setType(Type.TYPE2);
            case 2-> setType(Type.TYPE3);
        }

    }

    public void setCatchable(boolean catchable) {
        this.catchable = catchable;
    }

    public void setGrey(){ // la tessera è stata presa
        this.color=Color.GREY;
    }

    public void setBlack() {
        this.color = Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }
}

