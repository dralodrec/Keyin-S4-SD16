package Movie_Theater;

public class Reservation {

    private Theater theater;

    public Reservation(Theater theater) {
        this.theater = theater;
    }

    public void bookSeat(String selectedSeat) {
        theater.reserveSeat(selectedSeat);
    }

}