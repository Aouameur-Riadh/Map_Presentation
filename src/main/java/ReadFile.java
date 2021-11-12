import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
   Zones zones;
    File myObj;
    public  ReadFile(File file) {
        this.myObj = file;
        zones = new Zones();
    }
        public Zones GetDataFromFile(){
            try {
                String[] names = {"Z1", "Z2", "Z3", "Z4", "Z5", "Z6", "Z7", "Z8", "Z9", "Z10",
                "Z11", "Z12","Z13", "Z14", "Z15"};
                Scanner myReader = new Scanner(myObj);
                Zone zone =null;
                int i=0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.equals("-,-")){
                        zone = new Zone();
                        zone.setZoneName(names[i]);
                        i++;
                        zones.getObservableList().add(zone);
                    }
                    else {
                        String sp[] = data.split(",");
                        zone.getObservableList().add(new MyPoint(Double.parseDouble(sp[0]),Double.parseDouble(sp[1])));
                    }


                }
                System.out.println(zones.getObservableList().toString());
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return zones;
        }

}