import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConvertTextToSql {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// BufferedReader empTxt = new BufferedReader(new FileReader(new File(
		// "C:\\Users\\JackTheRipper\\Desktop\\sql.txt")));
		String line;
		// BufferedWriter whole = new BufferedWriter(new FileWriter(new File(
		// "C:\\Users\\JackTheRipper\\Desktop\\sql.sql")));
		// while ((line = empTxt.readLine()) != null) {
		// String[] oneline = line.split("[' ']+");
		// whole.newLine();
		// whole.append("insert into emp values (");
		// whole.append(oneline[1] + ",");
		// whole.append("'" + oneline[2] + "'" + ",");
		// whole.append("'" + oneline[3] + "'" + ",");
		// whole.append(oneline[4] + ",");
		// whole.append("to_date('" + oneline[5] + "','yyyy-mm-dd')" + ",");
		// whole.append(oneline[6] + ",");
		// whole.append(oneline[7] + ",");
		// whole.append(oneline[8] + ");");
		// }
		// empTxt.close();
		// whole.close();
		File f = new File("C:\\Users\\JackTheRipper\\Desktop\\sql1.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),
				"GBK");
		BufferedReader reader = new BufferedReader(read);

		File ff = new File("C:\\Users\\JackTheRipper\\Desktop\\sql1.sql");
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				ff), "GBK");
		BufferedWriter writer = new BufferedWriter(write);
		while ((line = reader.readLine()) != null) {
			String[] oneline = line.split("[' ']+");
			writer.newLine();
			writer.append("insert into dept values (");
			writer.append(oneline[1] + ",");
			writer.append("'" + oneline[2] + "'" + ",");
			writer.append("'" + oneline[3] + "'" + ")");
		}
		reader.close();
		writer.close();
	}
}
