package BakeryTest;

public interface Lock {
    void requestCS(int tid);
    void releaseCS(int tid);
}
