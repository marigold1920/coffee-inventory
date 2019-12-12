package coffee.inventory.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

import coffee.inventory.entity.Warehouse;

@JsonComponent
public class WarehouseSerializer extends JsonSerializer<Warehouse> {

    @Override
    public void serialize(Warehouse warehouse, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", warehouse.getId());
        generator.writeStringField("name", warehouse.getName());
        generator.writeStringField("addressWarehouse", warehouse.getAddress().getName());
        generator.writeEndObject();
    }

}