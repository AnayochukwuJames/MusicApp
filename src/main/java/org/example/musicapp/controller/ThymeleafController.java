package org.example.musicapp.controller;

import org.example.musicapp.model.Music;
import org.example.musicapp.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/music")
public class ThymeleafController {

   public final MusicService musicService;

    public ThymeleafController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/display-music")
    public ModelAndView displayMusic(){
        ModelAndView allMusicView = new ModelAndView("all-music");
        Iterable<Music> allMusic = musicService.getAllMusic().getBody();
        allMusicView.addObject("allMusic", allMusic);
        return allMusicView;
    }


    //Only when your using thymeleaf
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Music music){
        return "redirect:/display-music";
    }
}
