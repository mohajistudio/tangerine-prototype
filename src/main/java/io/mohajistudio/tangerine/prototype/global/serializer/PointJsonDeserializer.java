package io.mohajistudio.tangerine.prototype.global.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Map;

@JsonComponent
public class PointJsonDeserializer extends JsonDeserializer<Point> {
    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Override
    public Point deserialize(JsonParser p, DeserializationContext context) throws IOException, JacksonException {
        try {
            TreeNode treeNode = p.readValueAsTree();
            if (treeNode.toString().isEmpty()) {
                return null;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Double> map = objectMapper.readValue(treeNode.toString(), new TypeReference<>() {});
            double lat = map.get("lat");
            double lng = map.get("lat");
            return geometryFactory.createPoint(new Coordinate(lat, lng));
        } catch (Exception e) {
            return null;
        }
    }
}