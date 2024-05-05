package uz.oasis.jsp_cinema_application.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    @ElementCollection
    private List<String> genres;
    private byte[] photo;
    @Column(columnDefinition = "bool default false")
    private boolean isArchived;



    public String formattedGenres() {
        StringBuilder formattedGenres = new StringBuilder();
        for (String genre : genres) {
            String formattedGenre = genre.substring(0, 1).toUpperCase() + genre.substring(1).toLowerCase();
            formattedGenres.append(formattedGenre).append("/");
        }
        if (!formattedGenres.isEmpty()) {
            formattedGenres.setLength(formattedGenres.length() - 1);
        }

        return formattedGenres.toString();
    }
}
