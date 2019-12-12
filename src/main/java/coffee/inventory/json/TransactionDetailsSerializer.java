package coffee.inventory.json;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

import coffee.inventory.entity.Transaction;
import coffee.inventory.entity.TransactionDetails;

@JsonComponent
public class TransactionDetailsSerializer extends JsonSerializer<TransactionDetails> {

    @Override
    public void serialize(TransactionDetails transactionDetails, JsonGenerator generator,
            SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", transactionDetails.getId());
        Transaction transaction = transactionDetails.getTransaction();
        if (Objects.nonNull(transaction)) {
            generator.writeStringField("fromWarehouse", transaction.getFromWarehouse().getName());
            generator.writeStringField("toWarehouse", transaction.getToWarehouse().getName());
            generator.writeStringField("dateCreate", transaction.getDateCreate().toString());
            LocalDate dateReceive = transaction.getDateReceive();
            if (Objects.nonNull(dateReceive)) {
                generator.writeStringField("dateReceive", dateReceive.toString());
            }
            generator.writeObjectField("status", transaction.getStatus());
        }
        generator.writeEndObject();
    }

}