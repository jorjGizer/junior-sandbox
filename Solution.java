//package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WordsGame {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }
        }
        String[] array1 = stringBuilder.toString().split(" ");
        StringBuilder result = getLine(array1);
            System.out.println(result);
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, words);
        List<String> result = new ArrayList<>();
        String lastLetter = "";

        playCitiesGame(cities, result, lastLetter);
        stringBuilder.append(result.stream().collect(Collectors.joining(" ")));
        return stringBuilder;
    }

    private static void playCitiesGame(List<String> cities, List<String> result, String lastLetter) {
        String currentCity = result.isEmpty() ? null : result.get(result.size() - 1);

        List<String> possibleNextCities = cities.stream()
                .filter(city -> city.startsWith(lastLetter.toUpperCase()))
                .filter(city -> !city.equalsIgnoreCase(currentCity))
                .collect(Collectors.toList());

        if (possibleNextCities.isEmpty()) {
            return;
        }

        for (String nextCity : possibleNextCities) {
            result.add(nextCity);
            String nextLastLetter = nextCity.substring(nextCity.length() - 1);
            cities.remove(nextCity);
            playCitiesGame(cities, result, nextLastLetter);
            if (cities.isEmpty()) {
                return;
            }
            else {
                cities.add(nextCity);
                result.remove(nextCity);
            }
        }
    }
}
