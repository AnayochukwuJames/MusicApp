package org.example.musicapp.apiConsum;

import org.example.musicapp.model.Music;
import org.example.musicapp.model.MusicResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class APIConsumptionWithRestTemplate {


    public static void main(String[] args) {

            RestTemplate restTemplate = new RestTemplate();
            MusicResource response = restTemplate.getForObject("http://localhost:8080/resource/3", MusicResource.class);
            assert response != null;
//        System.out.println(response.getMusic());
//        response.getLinks("delete").forEach(System.out::println);

            ResponseEntity<Music> music = restTemplate.getForEntity("http://localhost:8080/music/single/3", Music.class);
            Music toPost = music.getBody();
            System.out.println(toPost);
            System.out.println(music.getHeaders());
            System.out.println(music.getStatusCode());
            System.out.println(music.getClass());

            Music music1 = restTemplate.getForObject("http://localhost:8080/music/update/6", Music.class);
            System.out.println(music1);
            assert toPost != null;
            toPost.setTitle("Going Higher");
            toPost.setGenre("Gospel");

//        ResponseEntity<Music> posted = restTemplate.postForEntity("http://localhost:8080/music/single", toPost, Music.class);
//
//        restTemplate.put("http://localhost:8080/music/update/6", toPost);
//
//        restTemplate.delete("http://localhost:8080/music/update/6");

            ResponseEntity<List<Music>> listResponseEntity =
                    restTemplate.exchange("http://localhost:8080/music/allMusic",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Music>>() {
                            });

            List<Music> musicList = listResponseEntity.getBody();
            musicList.forEach(System.out::println);

        }
    }