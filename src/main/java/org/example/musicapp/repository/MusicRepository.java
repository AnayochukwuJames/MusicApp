package org.example.musicapp.repository;

import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Integer> {



//    @Query("SELECT MAX (id) FROM Music")
//    Integer getId;


    Music findByArtistName(String artistName);
    Music findByTitle (String title);
    Music findByGenre (String genre);
    Music findByYearOfProduction (int year);
    Music findByAlbumName (String album);
}
