package org.example.musicapp.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
@Getter
@Setter
public class MusicResource extends RepresentationModel<MusicResource> {
    private Music music;
}
