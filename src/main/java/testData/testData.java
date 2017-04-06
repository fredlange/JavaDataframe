package testData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class testData {

    /**
     * Takes a url that linkes to a csv formatted website and returns a multidimensional ArrayList.
     * @param apiUrl
     * @return
     */
    public static Float[][] getCSVFromUrl(String apiUrl){

        String line;
        ArrayList collectedData = new ArrayList();
        int lineNbr = 0;

        try{
            URL url = new URL(apiUrl);
            InputStream stream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

            while((line = bufferedReader.readLine()) != null){
                lineNbr += 1;
                if(lineNbr >= 28) { // Remove lines to get smaller test data (Default 28)
                    ArrayList<Float> row = new ArrayList();
                    String[] items = line.split(",");
                    for(String i : items){
                        row.add(Float.parseFloat(i));
                    }
                    collectedData.add(row.toArray(new Float[row.size()]));
                }
            }

            Float[][] convertedArray = (Float[][]) collectedData.toArray(new Float[collectedData.size()][6]);
            return convertedArray;


        }catch (IOException ioe){
            ioe.printStackTrace();
            return null;
        }

    }


    public void getCSVFromFile(String path){
        // TODO Get CSV data from file. Return float[][] array
    }

    public static void main(String args[]){
        // Just some test from yahoo chartapi. Only for demo! Do not use!
        Float[][] csv = testData.getCSVFromUrl("http://chartapi.finance.yahoo.com/instrument/1.0/AAPL/chartdata;type=quote;range=10d/csv");
        System.out.print(csv);
    }





}
