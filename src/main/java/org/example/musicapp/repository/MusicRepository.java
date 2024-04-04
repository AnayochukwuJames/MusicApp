package org.example.musicapp.repository;

import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Integer> {

    Music findByArtistName(String artistName);
    Music findByTitle (String title);
    Music findByGenre (String genre);
    Music findByYearOfProduction (int year);
    Music findByAlbumName (String album);
}
