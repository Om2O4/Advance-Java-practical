package JDBC;

import java.sql.*;
import java.util.Scanner;

public class MenuStudentApp {
    private static final String HOST_URL = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_menu_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "Krishna*23";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                1. Create database and table
                2. Insert student
                3. Display all students
                4. Delete student
                5. Update student
                0. Exit
                """);

        while (true) {
            System.out.print("\nChoice: ");
            if (!sc.hasNextInt()) {
                sc.next();
                continue;
            }
            int ch = sc.nextInt();
            try {
                switch (ch) {
                    case 1 -> createDatabaseAndTable();
                    case 2 -> {
                        checkDb();
                        System.out.print("Roll no: ");
                        int r = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Name: ");
                        String n = sc.nextLine();
                        System.out.print("Address: ");
                        String a = sc.nextLine();
                        insertStudent(r, n, a);
                    }
                    case 3 -> {
                        checkDb();
                        displayAll();
                    }
                    case 4 -> {
                        checkDb();
                        System.out.print("Roll no to delete: ");
                        deleteStudent(sc.nextInt());
                    }
                    case 5 -> {
                        checkDb();
                        System.out.print("Roll no to update: ");
                        int r = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New name: ");
                        String n = sc.nextLine();
                        System.out.print("New address: ");
                        String a = sc.nextLine();
                        updateStudent(r, n, a);
                    }
                    case 0 -> System.exit(0);
                    default -> System.out.println("Invalid choice");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createDatabaseAndTable() throws SQLException {
        try (Connection con = DriverManager.getConnection(HOST_URL, USER, PASS);
             Statement st = con.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS jdbc_menu_db");
        }

        try (Connection con = getDbConnection();
             Statement st = con.createStatement()) {
            st.execute("""
                    CREATE TABLE IF NOT EXISTS student(
                        rollno  INT PRIMARY KEY,
                        name    VARCHAR(255),
                        address VARCHAR(255)
                    )
                    """);
        }
        System.out.println("Database and table ready.");
    }

    private static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static void checkDb() throws SQLException {
        try (Connection con = getDbConnection()) {
        } catch (SQLException e) {
            throw new SQLException("Database not created. Run option 1 first.");
        }
    }

    private static void insertStudent(int roll, String name, String address) throws SQLException {
        String sql = "INSERT INTO student (rollno, name, address) VALUES(?,?,?)";
        try (Connection con = getDbConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.executeUpdate();
        }
        System.out.println("Inserted.");
    }

    private static void displayAll() throws SQLException {
        String sql = "SELECT * FROM student ORDER BY rollno";
        try (Connection con = getDbConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("Roll\tName\tAddress");
            while (rs.next()) {
                System.out.println(rs.getInt("rollno") + "\t" + rs.getString("name") + "\t" + rs.getString("address"));
            }
        }
    }

    private static void deleteStudent(int roll) throws SQLException {
        String sql = "DELETE FROM student WHERE rollno=?";
        try (Connection con = getDbConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.executeUpdate();
        }
        System.out.println("Deleted.");
    }

    private static void updateStudent(int roll, String name, String address) throws SQLException {
        String sql = "UPDATE student SET name=?, address=? WHERE rollno=?";
        try (Connection con = getDbConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, roll);
            ps.executeUpdate();
        }
        System.out.println("Updated.");
    }
}