package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.PlayResult;

import java.util.ArrayList;
import java.util.Map;


public class PlayEntity {
    private Long id;
    private PlayEntity batter;
    private PlayResult playResult;
    private ArrayList<PlayerEntity> fielders;
    private Map<Bases, PlayerEntity> baseRunners;
    private Integer rbi;
}
