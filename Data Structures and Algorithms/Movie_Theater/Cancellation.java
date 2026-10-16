package Movie_Theater;

public class Cancellation {

    private Theater theater;

    public Cancellation(Theater theater) {
        this.theater = theater;
    }

    public void cancelSeat(String selectedSeat) {
        theater.cancelSeat(selectedSeat);
    }
}