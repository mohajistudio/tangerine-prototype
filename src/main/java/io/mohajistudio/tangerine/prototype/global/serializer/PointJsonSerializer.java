package io.mohajistudio.tangerine.prototype.global.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonComponent
@RequiredArgsConstructor
public class PointJsonSerializer extends JsonSerializer<Point> {
    @Override
    public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            if (value != null) {
                double lat = value.getX();
                double lng = value.getY();
                Map<String, Double> map = new HashMap<>();
                map.put("lat", lat);
                map.put("lng", lng);
                gen.writeObject(map);
            } else {
                gen.writeObject(null);
            }
        } catch (Exception e) {
            gen.writeObject(null);
        }
    }
}
