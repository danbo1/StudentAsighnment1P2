import java.util.Random;

public class Shuffle {

    private int range[] = new int[2];
    private int values[];
    private Random rand = new Random();

    /**
     * shuffle a set of values with no duplicates.
     *
     * @param lowerBound
     * @param upperBound
     */
    public Shuffle(int lowerBound,int upperBound){
        this.range[0] = lowerBound;
        this.range[0] = upperBound;
        this.values = new int[(upperBound-lowerBound)];

        for(int i = 0; i < (upperBound-lowerBound); i++){
            values[i] = (i+lowerBound);
        }
    }

    public int[] setOfRandomNum(int x){
        int results[] = new int[x];
        shuffle();
        for(int i =0; i < x; i++){
            results[i] = values[i];
        }
        return results;
    }

    public void shuffle(){
        int count =((range[0]-range[1])*2);
        int actions[] = new int[count];

        for(int i = 0; i < count; i++){
            actions[i] = rand.nextInt(values.length);
        }

        for(int i =0; i < count; i++){
            swap(actions[i],i%values.length);
        }
    }

    public void newRange( int lowerBound,int upperBound) {
        this.range[0] = lowerBound;
        this.range[0] = upperBound;
        this.values = new int[(upperBound - lowerBound)];

        for (int i = 0; i < (upperBound - lowerBound); i++) {
            values[i] = (i + lowerBound);
        }
    }

    private void swap(int x, int y){
        int temp = this.values[x];
        this.values[x] = this.values[y];
        this.values[y] = temp;
    }
}
