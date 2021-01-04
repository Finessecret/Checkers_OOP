package json;

import basic.pack.models.Node;
import com.google.gson.*;

import java.lang.reflect.Type;

public class NodeConverter implements JsonDeserializer<Node>, JsonSerializer<Node> {

    @Override
    public Node deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        int x = jsonDeserializationContext.deserialize(object.get("coordinateX"), Node.class);
        int y = jsonDeserializationContext.deserialize(object.get("coordinateY"), Node.class);
        return new Node(x, y);
    }

    @Override
    public JsonElement serialize(Node node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("coordinateX", String.valueOf(node.getNodeX()));
        object.addProperty("coordinateY", String.valueOf(node.getNodeY()));
        return object;
    }
}
