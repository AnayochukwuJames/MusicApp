package org.example.musicapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.example.musicapp.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/v1/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService musicService;


    private final ThymeleafController thymeleafController;

@DeleteMapping("/delete/{id}")
public ResponseEntity<String>deleteMusic(@PathVariable int id){
    return musicService.deleteMusic(id);
        }

        @PutMapping("/update/{id}")
    public ResponseEntity<Music> updateMusic(@Valid @PathVariable int id, @RequestBody Music music){
    return musicService.updateMusic(id, music);
        }

    @GetMapping("getMusic/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable int id){
        return musicService.getMusicById(id);
    }

        @PostMapping("/SaveMusic")
    public ResponseEntity<Music> addNewMusic (@RequestBody @Valid Music music){
    return musicService.addNewMusic(music);
        }

        @GetMapping("artistName")
    public ResponseEntity<Music> findByArtistName(@RequestParam String artistName){
        return musicService.findByArtistName(artistName);
    }

    @GetMapping("title")
    public ResponseEntity<Music> findByTitle (@RequestParam String title){
        return musicService.findByTitle(title);
    }

    @GetMapping("genre")
    public ResponseEntity<Music>findByGenre(@RequestParam String genre){
        return musicService.findByGenre(genre);
    }

    @GetMapping("year")
    public ResponseEntity<Music>findByYearOfProduction(@RequestParam int year){
        return musicService.findByYearOfProduction(year);
    }

    @GetMapping("albumName")
    public ResponseEntity<Music>findByAlbumName(@RequestParam String albumName){
        return musicService.findByAlbumName(albumName);
    }

    @GetMapping("allMusic")
    public ResponseEntity<Iterable<Music>> getAllMusic(){
        return musicService.getAllMusic();
    }


    @GetMapping("addMusic")
    public ModelAndView registrationForm(){
        ModelAndView musicForm = new ModelAndView("addMusic");
        Music music = new Music();
        musicForm.addObject("addMusic", music);
        return musicForm;
   }

    @PostMapping("addMusic")
    public ModelAndView addMusic(@ModelAttribute @Valid Music music){
        musicService.addNewMusic(music);
        return thymeleafController.displayMusic();

    }
}

