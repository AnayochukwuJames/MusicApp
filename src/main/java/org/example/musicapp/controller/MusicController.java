package org.example.musicapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.example.musicapp.model.MusicResource;
import org.example.musicapp.service.MusicService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService musicService;
@GetMapping("allMusic")
public ResponseEntity<List<Music>> getAllMusic(){
        return musicService.getAllMusic();
        }
@DeleteMapping("delete/{id}")
public ResponseEntity<String>deleteMusic(@PathVariable int id){
    return musicService.deleteMusic(id);
        }
        @PutMapping("update/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable int id,@Valid @RequestBody Music music){
    return musicService.updateMusic(id, music);
        }
    @GetMapping("getMusic/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable int id){
        return musicService.getMusicById(id);
    }
//        @GetMapping("/{id}")
//    public ResponseEntity<MusicResource>getMusicResource(@PathVariable int id){
//    Music musicToSend = musicService.getMusicById(id).getBody();
//            MusicResource musicResource = new MusicResource();
//            musicResource.setMusic(musicToSend);
//            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class).getMusicById(id)).withSelfRel();
//            Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class).deleteMusic(id)).withRel("delete");
//            Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class).updateMusic(id,musicToSend)).withRel("update");
//            musicResource.add(selfLink, delete,update);
//            return new ResponseEntity<>(musicResource, HttpStatus.OK);
//
//        }
        @PostMapping("SaveMusic")
    public ResponseEntity<Music> addNewMusic (@RequestBody @Valid Music music){
    return musicService.addNewMusic(music);
        }
        @GetMapping("artistName/{String}")
    public ResponseEntity<Music> findByArtistName(@RequestParam String artistName){
        return musicService.findByArtistName(artistName);
    }
    @GetMapping("title/{String}")
    public ResponseEntity<Music> findByTitle (@RequestParam String title){
        return musicService.findByTitle(title);
    }
    @GetMapping("genre/{genre}")
    public ResponseEntity<Music>findByGenre(@PathVariable String genre){
        return musicService.findByGenre(genre);
    }
    @GetMapping("year/{int}")
    public ResponseEntity<Music>findByYearOfProduction(@PathVariable int year){
        return musicService.findByYearOfProduction(year);

    }
    @GetMapping("albumName/{String}")
    public ResponseEntity<Music>findByAlbumName(String albumName){
        return musicService.findByAlbumName(albumName);
    }

}

