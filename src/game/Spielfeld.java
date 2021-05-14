package game;

class Spielfeld
{
    int punkte;
    
    Block[][] feld;
    final int groesse;
    int anzahl;
    Block[] index;

    Spielfeld (int g)
    {
        groeﬂe = g;
        feld = new Block[g][g];
        index[] = new Block[groesse*groesse];
    }

    
    public void blockErstellen(){}
    public void verschieben(){}
    public Boolean istVoll(){}
    public Block Addieren(){}
}



/*public int[] leereFelder(){
        int l = groesse-anzahl;
        int[] leereFelder = new int[l];
        leereFelder[0] = l;

        for(int i = 0; i < index.legth;i++){
            if(index[i]==null){
                for (int k = 0;k < leereFelder.legth;k++){
                    if(leereFelder[k]==null){
                        leereFelder[k] = i;
                    }
                }
            }
        }
        return leereFelder;
    }