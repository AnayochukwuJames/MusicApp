package org.example.musicapp.apiConsum;

import org.example.musicapp.model.Music;
import org.example.musicapp.model.MusicResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class APIConsumptionWithRestTemplate {


    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        MusicResource response = restTemplate.getForObject("http://localhost:8084/api/v1/resource/1",MusicResource.class);
        assert response !=null;
        System.out.println(response.getMusic());
        response.getLinks("delete").forEach(System.out::println);

        ResponseEntity<Music> music = restTemplate.getForEntity("http://localhost:8084/music/update/1", Music.class);
        Music toPost = music.getBody();
        System.out.println(toPost);
        System.out.println(music.getHeaders());
        System.out.println(music.getStatusCode());
        System.out.println(music.getClass());

        Music music1 = restTemplate.getForObject("http://localhost:8084/music/update/1",Music.class);
        System.out.println(music1);
        assert  toPost != null;
        toPost.setTitle("Going Higher");
        toPost.setGenre("Gospel");

        ResponseEntity<Music> posted = restTemplate.postForEntity("http://localhost:8084/music/SaveMusic", toPost, Music.class);


        




    }
}