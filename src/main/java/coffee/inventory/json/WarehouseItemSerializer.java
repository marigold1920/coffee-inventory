package coffee.inventory.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

import coffee.inventory.entity.Product;
import coffee.inventory.entity.WarehouseItem;

@JsonComponent
public class WarehouseItemSerializer extends JsonSerializer<WarehouseItem> {

    @Override
    public void serialize(WarehouseItem item, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        Product product = item.getItem().getProduct();
        generator.writeNumberField("id", item.getId());
        generator.writeStringField("productName", product.getName());
        generator.writeNumberField("price", product.getPrice());
        generator.writeStringField("unit",product.getUnit().getName());
        generator.writeNumberField("quantity", item.getQuantity());
        generator.writeStringField("supplier", item.getItem().getSupplier().getName());
        generator.writeStringField("productCode", item.getItem().getProduct().getProductCode());
        generator.writeEndObject();
    }

}