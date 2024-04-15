package org.example.musicapp.service;

import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.example.musicapp.repository.MusicRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
//    public ResponseEntity<List<Music>> getAllMusic(){
    @Cacheable(value = "AllMusic")
    public ResponseEntity<Iterable<Music>> getAllMusic(){
        System.out.println("catchable");
        return new ResponseEntity<>(musicRepository.findAll(), HttpStatus.OK);

    }
    @Cacheable(value = "getMusicById", key = "#id")
    public ResponseEntity<Music> getMusicById(int id){
        return new ResponseEntity<>(musicRepository.findById(id).get(),HttpStatus.OK);
    }

    @CacheEvict(value = "addNewMusic", allEntries = true)
    public ResponseEntity<Music> addNewMusic(Music music){
        return new ResponseEntity<>(musicRepository.save(music), HttpStatus.CREATED);
    }
    @CacheEvict(value = {"updateMusic", "getMusicById"}, key = "#id", allEntries = true)
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
    @CacheEvict(value = "deleteMusic",allEntries = true)
    public ResponseEntity<String> deleteMusic(int id){
        Music music = musicRepository.findById(id).get();
        musicRepository.deleteById(id);
        return new ResponseEntity<>("music is deleted Successfully", HttpStatus.OK);
    }

//public ResponseEntity<String> deleteMusic(int id){
//    musicRepository.deleteById(id);
//    return new ResponseEntity<>("Music deleted Successfully", HttpStatus.OK);
//}
    @Cacheable(value = "findByArtistName", key = "#artistName")
    public ResponseEntity<Music> findByArtistName(String artistName){
        return new ResponseEntity<>(musicRepository.findByArtistName(artistName), HttpStatus.FOUND);
    }
    @Cacheable(value = "findByTitle", key = "#title")
    public ResponseEntity<Music> findByTitle (String title){
        return new ResponseEntity<>(musicRepository.findByTitle(title),HttpStatus.OK);
    }
    @Cacheable(value = "findByGenre", key = "#genre")
    public ResponseEntity<Music>findByGenre(String genre){
        return new ResponseEntity<>(musicRepository.findByGenre(genre),HttpStatus.OK);
    }
    @Cacheable(value = "findByYearOfProduction", key = "#year")
    public ResponseEntity<Music>findByYearOfProduction(int year){
        return new ResponseEntity<>(musicRepository.findByYearOfProduction(year), HttpStatus.OK);

    }
    @Cacheable(value = "findByAlbumName", key = "#albumName")
    public ResponseEntity<Music>findByAlbumName(String albumName){
        return new ResponseEntity<>(musicRepository.findByAlbumName(albumName), HttpStatus.OK);
    }

}
