package org.example;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/labjava";
        String user = "postgres";
        String password = "0419";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Подключение к PostgreSQL установлено!");
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pet");
            while (rs.next()) {
                int idPet = rs.getInt("id_pet");
                String name = rs.getString("pet");
                int age = rs.getInt("age");
                int idProfile = rs.getInt("id_profile");

                System.out.println("ID: " + idPet +
                        ", Имя: " + name +
                        ", Возраст: " + age +
                        ", ID хозяина: " + idProfile);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных:");
            e.printStackTrace();
        }
    }
}