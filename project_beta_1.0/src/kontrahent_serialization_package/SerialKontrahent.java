package kontrahent_serialization_package;

import main_package.FileOutputStream;
import main_package.IOException;
import main_package.ObjectOutputStream;

public class SerialKontrahent {

	public void serializeToFile()  {
		try {
	        FileOutputStream fileOut =
	        new FileOutputStream("/tmp/employee.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(e);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved in /tmp/employee.ser");
	     } catch (IOException i) {
	        i.printStackTrace();
	     }
	}
}
