package basic.pack;

import basic.pack.service.gameService.GameService;
import json.JsonSerializerD;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        JsonSerializerD jsonSerializerd = new JsonSerializerD();
        GameService game = jsonSerializerd.deserialize("src/main/java/json/checkers.json");
        game.start();
    }
}
