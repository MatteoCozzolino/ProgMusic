package Model;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBuilder {

    //Questa classe contiene i metodi per la lettura del dataset in esame e, attraverso il metodo .builder() restituisce una struttura dati
    //pronta per essere elaborata

    public ArrayList<Data> builder() {


        String id;
        ArrayList<Data> dataset = new ArrayList<>();
        File file = new File("src/main/resources/dataset.json");

        try {

            int i = 1;
            while(i <=274){

                id = String.valueOf(i);
                FileReader reader = new FileReader(file.getAbsolutePath());
                Data value = new Data();

                JsonArray object = Json.parse(reader).asObject().get(id).asArray();

                for (JsonValue item : object) {
                    value.setDay(item.asObject().getString("giorno", ""));
                    value.setArrestNumber(item.asObject().getInt("numArresti", 0));
                    dataset.add(value);
                    }

                i++;
                reader.close();
            }
        }
        catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.info("Parsing error."); }

        return dataset;
    }

}