#Задание 2. Метод ближайшего соседа (TSP)

import java.util.*;

public class TSPNearestNeighbor {
    
    public static List<Integer> tspNearest(int[][] dist) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[dist.length];
        int curr = 0;
        path.add(curr);
        visited[curr] = true;
        
        // Цикл выбора ближайшего непосещённого города
        for (int i = 0; i < dist.length - 1; i++) {
            int next = -1;
            int minDist = Integer.MAX_VALUE;
            
            // Поиск ближайшего непосещённого соседа
            for (int j = 0; j < dist.length; j++) {
                if (!visited[j] && dist[curr][j] < minDist && dist[curr][j] > 0) {
                    minDist = dist[curr][j];
                    next = j;
                }
            }
            
            // Переход к найденному городу
            if (next != -1) {
                curr = next;
                path.add(curr);
                visited[curr] = true;
            }
        }
        
        return path;
    }
    
    public static int calculateTotalDistance(List<Integer> path, int[][] dist) {
        int total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            total += dist[path.get(i)][path.get(i + 1)];
        }
        // Возвращаемся в начальный город
        total += dist[path.get(path.size() - 1)][path.get(0)];
        return total;
    }
    
    public static void printRoute(List<Integer> path, int[][] dist) {
        System.out.println("Маршрут коммивояжера:");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.println("Город " + path.get(i) + " -> Город " + path.get(i + 1) + 
                             " (расстояние: " + dist[path.get(i)][path.get(i + 1)] + ")");
        }
        // Возврат в начальный город
        System.out.println("Город " + path.get(path.size() - 1) + " -> Город " + path.get(0) + 
                         " (расстояние: " + dist[path.get(path.size() - 1)][path.get(0)] + ")");
    }
    
    public static void main(String[] args) {
        // Матрица расстояний между 5 городами
        int[][] distances = {
            {0, 10, 15, 20, 30},  // Расстояния от города 0
            {10, 0, 35, 25, 40},   // Расстояния от города 1
            {15, 35, 0, 30, 50},   // Расстояния от города 2
            {20, 25, 30, 0, 45},   // Расстояния от города 3
            {30, 40, 50, 45, 0}    // Расстояния от города 4
        };
        
        System.out.println("Матрица расстояций:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(Arrays.toString(distances[i]));
        }
        
        List<Integer> route = tspNearest(distances);
        int totalDistance = calculateTotalDistance(route, distances);
        
        System.out.println("\nНайденный маршрут: " + route);
        System.out.println("Общее расстояние: " + totalDistance);
        System.out.println();
        printRoute(route, distances);
    }
}
