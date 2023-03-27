public abstract class CommonGoalAbs {

    private int playing = 0;
    private int maxPoint = 8;

    protected Boolean[] playerAchived = new Boolean[Game.Nplayers];
    // Ragiona su utilizzo variabile in common con numero giocatore, devo salvarmi chi l'ha già raggiunto?
    protected Player playerPlying; //= Game.playerOnStage();

    public abstract void control(PersonalShelf shelf);

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }
    public void setPlaying(int playing){this.playing=playing;}
    private void maxDecrease() {
        setMaxPoint(maxPoint - maxPoint % Game.Nplayers);
    }


    /*Metodo per sommare i punti al player, restituito come void che opera direttamente sui punteggi
     * per gestire il fatto che ogni che un primo player ha ottenuto il punteggio questo viene salvato*/
    protected void givePoints(Player player) {

        if (!playerAchived[ playing % 4 ]) {
            player.sumPoints(maxPoint);
            this.playerAchived[ playing % 4 ] = true;
            this.maxDecrease();
        }
    }


    //Funzione da chiamare in un NextTurn per portare di pari passo il valore giocatore del commonGoal
    public void incrementCG() {
            if (playing == 3)
            { setPlaying(0);}
             //  playerPlying=Game.playerOnStage();  }
            else
            {   setPlaying(playing+1);}
             //   playerPlying=Game.playerOnStage();}
        }



    }
