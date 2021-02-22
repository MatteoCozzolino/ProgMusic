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

    /**
     * This class contains the method builder() to read the dataset and returns a data structure ready to be analyzed
     *
     */

    public ArrayList<Data> builder() {


        String id;
        ArrayList<Data> dataset = new ArrayList<>();
        File file = new File("src/main/resources/dataset.json");

        try {

            int i = 1;
            while(i <=366){

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