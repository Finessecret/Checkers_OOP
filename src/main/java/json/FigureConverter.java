package json;

import basic.pack.models.Direction;
import basic.pack.models.Figure;
import basic.pack.models.Node;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FigureConverter implements JsonDeserializer<Figure>, JsonSerializer<Figure> {
    @Override
    public Figure deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Node currentNode = jsonDeserializationContext.deserialize(object.get("currentNode"), Node.class);
        String symbol = jsonDeserializationContext.deserialize(object.get("symbol"), String.class);
        ArrayList<Direction> allowedDirection = jsonDeserializationContext.deserialize(object.get("allowedDirection"), Direction.class);
        Boolean isDame = jsonDeserializationContext.deserialize(object.get("isDame"), Boolean.class);
        Integer steps = jsonDeserializationContext.deserialize(object.get("steps"), Integer.class);
        return new Figure(currentNode);
    }

    @Override
    public JsonElement serialize(Figure figure, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("currentNode", String.valueOf(figure.getNode()));
        object.addProperty("symbol", String.valueOf(figure.getSymbol()));
        object.addProperty("allowedDirection", String.valueOf(figure.getAllowedDirection()));
        object.addProperty("isDame", String.valueOf(figure.isDame()));
        object.addProperty("steps", String.valueOf(figure.getSteps()));
        return object;
    }
}
