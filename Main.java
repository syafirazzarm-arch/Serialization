
package handson9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // =====================================
    // 1. SERIALIZE
    // =====================================

    public static void serialize(
            List<Employee> employees, Path path) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path.toFile()))) {

            oos.writeObject(employees);

            System.out.println("Serialization berhasil!");

        } catch (IOException e) {

            System.out.println("Gagal melakukan serialization: "
                    + e.getMessage());
        }
    }


    // =====================================
    // 2. DESERIALIZE
    // =====================================

    @SuppressWarnings("unchecked")
    public static List<Employee> deserialize(Path path) {

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path.toFile()))) {

            List<Employee> employees =
                    (List<Employee>) ois.readObject();

            System.out.println("Deserialization berhasil!");

            return employees;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Gagal melakukan deserialization: "
                    + e.getMessage());

            return new ArrayList<>();
        }
    }


    // =====================================
    // 3. MAIN METHOD
    // =====================================

    public static void main(String[] args) {

        Path path = Paths.get("handson9", "employees.ser");

        // Membuat beberapa objek Employee
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("John", 30));
        employees.add(new Employee("Siti", 25));
        employees.add(new Employee("Budi", 28));

        // Menampilkan data sebelum serialization
        System.out.println("=== ORIGINAL EMPLOYEE DATA ===");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Menyimpan objek ke file
        System.out.println("\n=== SERIALIZATION ===");

        serialize(employees, path);

        // Membaca kembali objek dari file
        System.out.println("\n=== DESERIALIZATION ===");

        List<Employee> restoredEmployees = deserialize(path);

        // Menampilkan hasil deserialization
        System.out.println("\n=== RESTORED EMPLOYEE DATA ===");

        for (Employee employee : restoredEmployees) {
            System.out.println(employee);
        }
    }
}
