package org.example.musicapp.service;

import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.example.musicapp.repository.MusicRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
//    @Cacheable(value = {"AllMusic", "getMusic"}, key = "#id")
@Cacheable(value = "allMusicCache")
    public ResponseEntity<Iterable<Music>> getAllMusic(){
        System.out.println("catchable");
        return new ResponseEntity<>(musicRepository.findAll(), HttpStatus.OK);

    }
//    @Cacheable(value = "getMusic", key = "#id")
    @Cacheable(value = "musicCache", key = "#id")
    public ResponseEntity<Music> getMusicById(int id){
        return new ResponseEntity<>(musicRepository.findById(id).get(),HttpStatus.OK);
    }

//    @CacheEvict(value = "allMusic", allEntries = true)
    @CacheEvict(value = "allMusicCache", allEntries = true)
    public ResponseEntity<Music> addNewMusic(Music music){
        return new ResponseEntity<>(musicRepository.save(music), HttpStatus.CREATED);
    }
//    @CacheEvict(value = {"allMusic", "getMusic"}, key = "#id")
    @CacheEvict(value = "musicCache", key = "#id")
    public ResponseEntity<Music> updateMusic(int id, Music music){
        Music dbMusic = musicRepository.findById(id).get();
        dbMusic.setTitle(music.getTitle());
        dbMusic.setMusicDuration(music.getMusicDuration());
        dbMusic.setGenre(music.getGenre());
        dbMusic.setAlbumName(music.getAlbumName());
        dbMusic.setArtistName(music.getArtistName());
        dbMusic.setYearOfProduction(music.getYearOfProduction());
        return new ResponseEntity<>(musicRepository.save(dbMusic),HttpStatus.ACCEPTED);
    }
//    @CacheEvict(value = {"allMusic","getMusic" },allEntries = true)
    @CacheEvict(value = "musicCache", key = "#id")
    public ResponseEntity<String> deleteMusic(int id){
        Music music = musicRepository.findById(id).get();
        musicRepository.deleteById(id);
        return new ResponseEntity<>(music + "music is deleted Successfully", HttpStatus.OK);
    }

//    @Cacheable(value = "artistName", key = "#artistName")
    @Cacheable(value = "musicCache", key = "#artistName")
    public ResponseEntity<Music> findByArtistName(String artistName){
        return new ResponseEntity<>(musicRepository.findByArtistName(artistName), HttpStatus.FOUND);
    }
//    @Cacheable(value = "title", key = "#title")
    @Cacheable(value = "musicCache", key = "#title")
    public ResponseEntity<Music> findByTitle (String title){
        return new ResponseEntity<>(musicRepository.findByTitle(title),HttpStatus.OK);
    }
//    @Cacheable(value = "genre", key = "#genre")
    @Cacheable(value = "musicCache", key = "#genre")
    public ResponseEntity<Music>findByGenre(String genre){
        return new ResponseEntity<>(musicRepository.findByGenre(genre),HttpStatus.OK);
    }
//    @Cacheable(value = "year", key = "#year")
    @Cacheable(value = "musicCache", key = "#year")
    public ResponseEntity<Music>findByYearOfProduction(int year){
        return new ResponseEntity<>(musicRepository.findByYearOfProduction(year), HttpStatus.OK);

    }
//    @Cacheable(value = "albumName", key = "#albumName")
    @Cacheable(value = "musicCache", key = "#albumName")
    public ResponseEntity<Music>findByAlbumName(String albumName){
        return new ResponseEntity<>(musicRepository.findByAlbumName(albumName), HttpStatus.OK);
    }
}
