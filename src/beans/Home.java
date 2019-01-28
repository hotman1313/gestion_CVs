package beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("r")
public class Home implements Serializable {

    private static final long serialVersionUID = 1L;

    String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return super.toString() + "[place=" + place + "]";
    }

}