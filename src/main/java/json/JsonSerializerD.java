package json;

import basic.pack.models.Figure;
import basic.pack.models.Graph;
import basic.pack.models.Node;
import basic.pack.service.gameService.GameService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSerializerD {

    public void serialize(GameService game, String fileName) throws IOException {
        Gson gson = initGSon();
        FileWriter fileWriter = new FileWriter(fileName);
        gson.toJson(game, fileWriter);
        fileWriter.close();
    }

    public GameService deserialize(String fileName) throws FileNotFoundException {
        Gson gson = initGSon();
        FileReader fileReader = new FileReader(fileName);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, GameService.class);
    }

    private Gson initGSon() {
        return new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Node.class, new NodeConverter())
                .registerTypeAdapter(Figure.class, new FigureConverter())
                .registerTypeAdapter(Graph.class, new GraphConverter())
                .enableComplexMapKeySerialization()
                .create();
    }
}
