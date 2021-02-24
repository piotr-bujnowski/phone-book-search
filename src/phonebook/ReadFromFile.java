package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadFromFile {

    private File file;

    public ReadFromFile(File file) {
        this.file = file;
    }

    public void readToList(List<String> list, boolean ignoreNumber) {

        if (ignoreNumber) {
            try (Scanner scanner = new Scanner(this.file)) {
                while (scanner.hasNext()) {
                    scanner.next();
                    list.add(scanner.nextLine().trim());
                }

            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + this.file.getAbsolutePath());
            }
        } else {
            try (Scanner scanner = new Scanner(this.file)) {
                while (scanner.hasNext()) {
                    list.add(scanner.nextLine().trim());
                }

            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + this.file.getAbsolutePath());
            }
        }
    }

    public void readToListHashed(Map<Integer, String> list) {

        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNext()) {
                scanner.next();
                String name = scanner.nextLine().trim();
                int hashedName = name.hashCode();
                list.put(hashedName, name);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + this.file.getAbsolutePath());
        }

    }

}
