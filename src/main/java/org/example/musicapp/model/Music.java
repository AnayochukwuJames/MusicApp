package org.example.musicapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "yearOfProduction"}))
@Entity
public class Music {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //To generate Id manually
//    @Query("SELECT MAX (id) FROM")
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    @Length(min = 4, max = 25)
    private String albumName;
    @NotBlank
    @Length(min = 6, max = 25)
    private String artistName;
    @Range(min = 1, max = 6)
    private double musicDuration;
    @NotBlank
    @Length(min = 4)
    private String genre;
    @Min(1990)
    @Max(2024)
    private int yearOfProduction;

    @Value("${my.name}")
   private String name;
}
