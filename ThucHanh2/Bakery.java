package ThucHanh2;

public class Bakery implements Lock{
    int N;
    boolean choosing[];
    public static int[] number;
    public Bakery(int n) {
        N = n;
        choosing = new boolean[N];
        number = new int[N];
        for(int j = 0 ; j<N;j++) {
            choosing[j] = false;
            number[j] = 0;
        }
    }
    @Override
    public void requestCS(int id) {
        // TODO Auto-generated method stub
        System.out.println("thread " + id + " request");
        choosing[id] = true;
        for(int j = 0; j < N;j++)
            if(number[j] > number[id])
                number[id] = number[j];
        number[id]++;
        choosing[id] = false;
        for(int j = 0; j < N; j++) {
            while(choosing[j]);
            while((number[j] != 0)&&
                    (( number[j] < number[id]) ||
                            ((number[j] == number[id]) && j < id) ));
        }

    }

    @Override
    public void releaseCS(int id) {
        System.out.println("thread " + id + " release");
        // TODO Auto-generated method stub
        number[id] = 0;
    }
}
