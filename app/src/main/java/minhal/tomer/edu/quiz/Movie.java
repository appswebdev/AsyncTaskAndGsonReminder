package minhal.tomer.edu.quiz;

/**
 * Created by ANDROID on 19/02/2017.
 */
public class Movie {
    public String poster_path;
    public String title;
    public String overview;
    public String popularity;
    public String release_date;

    @Override
    public String toString() {
        return "Movie{" +
                "poster_path='" + poster_path + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity='" + popularity + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
