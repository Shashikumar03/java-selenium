package TestClass;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    @DataProvider(name = "getData")
    public  Object[][] getData(){
        Object[][] obj=  new Object[2][2];
        obj[0][0]="Amit";
        obj[0][1]="Amit@123";
        return obj;
    }

    @DataProvider(name = "csvData")
    public Object[][] getCsvData() throws IOException {
        String path = "src/test/resources/testdata.csv"; // Update path as per your project
        return Data.readCSV(path);
    }

    public static Object[][] readCSV(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        boolean isFirstLine = true;

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false; // skip header
                continue;
            }
            String[] fields = line.split(",");
            data.add(new Object[]{fields[0], fields[1]});
        }
        br.close();

        return data.toArray(new Object[0][]);
    }
}

