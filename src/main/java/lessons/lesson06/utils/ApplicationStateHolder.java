package lessons.lesson06.utils;

import lessons.lesson06.db.Flight;

public class ApplicationStateHolder {
    private ApplicationState state;
    private Flight currentFlight;

    public ApplicationStateHolder() {
        this.state = ApplicationState.FLIGHT_LIST;
    }

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public ApplicationState getState() {
        return state;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }
}
