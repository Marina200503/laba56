package utility;

import models.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class Ask {
    public static class AskBreak extends Exception {}

    public static LabWork askLabWork(Console console) throws AskBreak {
		/*
            private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
            private String name; //Поле не может быть null, Строка не может быть пустой
            private Coordinates coordinates; //Поле не может быть null
            private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
            private Long minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
            private Difficulty difficulty; //Поле может быть null
            private Person author; //Поле может быть null
        */
        try {
            console.print("name: ");
            String name;
            while (true) {
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.equals("")) break;
                console.print("name: ");
            }
            var coordinates = askCoordinates(console);
            console.print("minimal point: ");
            Long minimalPoint;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try { minimalPoint = Long.parseLong(line); if (minimalPoint>0) break; } catch (NumberFormatException e) { }
                console.print("minimal point: ");
            }
            var difficulty= askDifficulty(console);
            var author=askPerson(console);
            return new LabWork(1000000000, name, coordinates, minimalPoint, difficulty, author);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            /*
            private Integer x; //Поле не может быть null
            private Long y; //Поле не может быть null
             */
            console.print("coordinates.x: ");
            Integer x;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try { x = Integer.parseInt(line); break; } catch (NumberFormatException e) { }
                console.print("coordinates.x: ");
            }
            console.print("coordinates.y: ");
            Long y;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try { y = Long.parseLong(line); break; } catch (NumberFormatException e) { }
                console.print("coordinates.y: ");
            }

            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Location askLocation(Console console) throws AskBreak {
        try {

            console.print("person.location.x (empty for null-location): ");
            float x;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) return null;
                try { x = Integer.parseInt(line); break; } catch (NumberFormatException e) { }
                console.print("person.location.x: ");
            }
            console.print("person.location.y: ");
            float y;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try { y = Integer.parseInt(line); break; } catch (NumberFormatException e) { }
                console.print("person.location.y: ");
            }
            console.print("person.location.z: ");
            Long z;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try { z = Long.parseLong(line); break; } catch (NumberFormatException e) { }
                console.print("person.location.z: ");
            }
            console.print("person.location.name: ");
            String name;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) { name = null; break; }
                try { name = line; break; } catch (NullPointerException e) { }
                console.print("person.location.name: ");
            }
            return new Location(x, y, z, name);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Difficulty askDifficulty(Console console) throws AskBreak {
        try {
            console.print("Difficulty ("+Difficulty.names()+"): ");
            Difficulty r;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) { r = null; break; }
                try { r = Difficulty.valueOf(line); break; } catch (NullPointerException | IllegalArgumentException  e) { }
                console.print("Difficulty: ");
            }
            return r;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Person askPerson(Console console) {
        try {
            /*
            private String name; //Поле не может быть null, Строка не может быть пустой
            private ZonedDateTime birthday; //Поле не может быть null
            private String passportID; //Строка не может быть пустой, Длина строки не должна быть больше 49, Длина строки должна быть не меньше 6, Поле может быть null
            private Location location; //Поле может быть null
            */
            console.print("person.name(не пустая): ");
            String name;
            name = console.readln().trim();
            if (name.isEmpty()) return null;

            console.print("Введите год рождения(не пустой) ");
            ZonedDateTime birthday;
            while (true) {
                try {
                    int year = 0;
                    while (year < 1900 || year > 2024) {
                        console.println("Год должен принадлежать промежутку [1900; 2024]");
                        year = Integer.parseInt(console.readln().trim());
                    }
                    int month = 0;
                    while (month < 1 || month > 12) {
                        console.println("Месяц должен принадлежать промежутку [1; 12]");
                        month = Integer.parseInt(console.readln().trim());
                    }
                    int day = 0;
                    int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
                    while (day < 1 || day > daysInMonth) {
                        console.println("День должен принадлежать промежутку [1; " + daysInMonth + "]");
                        day = Integer.parseInt(console.readln().trim());
                    }
                    birthday = ZonedDateTime.of(year,
                            month,
                            day,
                            0,
                            0,
                            0,
                            0,
                            ZoneId.of("Europe/Moscow"));
                    break;

                } catch (NullPointerException | IllegalArgumentException e) {
                }
                console.print("Birthday: ");
            }
            Long height;
            console.print("Введите person.height(число, не пустое): ");
            while (true) {
                String line = console.readln().trim();
                try {
                    height = Long.parseLong(line);
                    break;
                } catch (NumberFormatException e) {
                }
                console.print("person.height(число, не пустое): ");
            }
            Float weight;
            console.print("Введите person.weight(число, не пустое): ");
            while (true) {
                String line = console.readln().trim();
                try {
                    weight = Float.parseFloat(line);
                    break;
                } catch (NumberFormatException e) {
                }
                console.print("person.weight(число, не пустое): ");
            }
            Location location = askLocation(console);
            return new Person(name, birthday, height, weight, location);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        } catch (AskBreak e) {
            throw new RuntimeException(e);
        }
    }
}

