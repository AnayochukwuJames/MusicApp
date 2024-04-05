package org.example.musicapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.musicapp.model.Music;
import org.example.musicapp.model.MusicResource;
import org.example.musicapp.service.MusicService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final MusicService musicService;

    @GetMapping("/{id}")
    public ResponseEntity<MusicResource> getMusicResource(@PathVariable int id){
        Music musicToSend = musicService.getMusicById(id).getBody();
        MusicResource musicResource = new MusicResource();
        musicResource.setMusic(musicToSend);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .getMusicById(id)).withSelfRel();
        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .deleteMusic(id)).withRel("delete");
        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .updateMusic(id, musicToSend)).withRel("update");
        Link allMusic = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .getAllMusic()).withRel("allMusic");
        Link findByArtistName = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .findByArtistName(musicToSend.getArtistName())).withRel("findByArtistName");
        Link findByTitle = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .findByTitle(musicToSend.getTitle())).withRel("findByTitle");
        Link findByGenre = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .findByGenre(musicToSend.getGenre())).withRel("findByGenre");
        Link findByYearOfProduction = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController
                .class).findByYearOfProduction(musicToSend.getYearOfProduction())).withRel("findByYearOfProduction");
        Link findByAlbumName = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicController.class)
                .findByAlbumName(musicToSend.getAlbumName())).withRel("findByAlbumName");
        musicResource.add(selfLink, delete, update, allMusic, findByArtistName, findByTitle, findByGenre,
                findByYearOfProduction, findByAlbumName);

        return new ResponseEntity<>(musicResource, HttpStatus.OK);
    }
}