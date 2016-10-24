import java.util.Scanner;
import java.util.Random;

public class Snake001 {

	public static int size() {

		final int N = 7;
		return N;
	} // метод размера
	public static void main(String [] args) {

		int length = 1; // кол-во секций
		int [] sectionY = new int [size() * size() - 1]; // массив координат Y секций змеи
		int [] sectionX = new int [size() * size() - 1]; // массив координат Х секций змеи
		char [][] map = new char [size()][size()]; // поле size x size
		int headX = size() / 2, headY = size() / 2; // начальные координаты головы
		sectionX[0] = size() / 2 + 1;//
		sectionY[0] = size() / 2;	 // координаты секции змеи
		// Заполнение поля
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				if ((i == 0) || (i == size() - 1) || (j == 0) || (j == size() - 1)) {
					map[i][j] = '#';
				}
				else {
					map[i][j] = ' ';
				}
			}
		}
		// *
		// Создание "тоннелей"" в стенах
		map[size() / 2][0] = ' ';
		map[size() / 2][size() - 1] = ' ';
		map[0][size() / 2] = ' ';
		map[size() - 1][size() / 2] = ' ';
		// *
		map[headY][headX] = '>'; // Размещение головы
		map[sectionY[0]][sectionX[0]] = '*'; // Размещение секции
		fruit(map);
		// Вывод поля
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}	
			System.out.println();
		}
		// *
		char input;
		int destCoord; // Координата направления
		boolean alive = true, win = false, wall = false;
		while (alive && !win && !wall) {
			Scanner sc = new Scanner(System.in);
			input = sc.next().charAt(0);	
			if (input == 'a') {
				destCoord = headX - 1;
				if (destCoord < 0) {
					destCoord = size() - 1;
				}
				if (map[headY][headX] == '<') {
					continue;					
				}
				else if (map[headY][destCoord] == '#') {
					wall = true;
				}
				else if (map[headY][destCoord] != '@') {
					move(map, sectionX, sectionY, length, headX, headY);
					headX = destCoord;
					for (int i = 0; i < length; i++) {
						if (headX == sectionX[i] && headY == sectionY[i]) {
							alive = false;
							System.out.println(1);
							break;
						}
					}
					map[headY][headX] = '>';
				}
				else {
					length++;
					growth(map, sectionX, sectionY, headX, headY, length);
					headX = destCoord;
					map[headY][headX] = '>';
					if (length == (size() - 2) * (size() - 2) + 4) {
						win = true;
						break;
					}
					fruit(map);
				}
			}
			if(input == 'w') {
				destCoord = headY - 1;
				if (destCoord < 0) {
					destCoord = size() - 1;
				}
				if (map[headY][headX] == '^') {
					continue;			
				}
				else if (map[destCoord][headX] == '#') {
					wall = true;
				}
				else if (map[destCoord][headX] != '@') {
					move(map, sectionX, sectionY, length, headX, headY);
					headY = destCoord;
					for (int i = 0; i < length; i++) {
						if (headX == sectionX[i] && headY == sectionY[i]) {
							alive = false;
							break;
						}
					}
					map[headY][headX] = 'v';
				}
				else {
					length++;
					growth(map, sectionX, sectionY, headX, headY, length);
					headY = destCoord;
					map[headY][headX] = 'v';
					if (length == (size() - 2) * (size() - 2) + 4) {
						win = true;
						break;
					}
					fruit(map);
				}
			}
			if(input == 's') {
				destCoord = headY + 1;
				if (destCoord > size() - 1) {
					destCoord = 0;
				}
				if (map[headY][headX] == 'v') {
					continue;			
				}
				else if (map[destCoord][headX] == '#') {
					wall = true;
				}
				else if (map[destCoord][headX] != '@') {
					move(map, sectionX, sectionY, length, headX, headY);
					headY = destCoord;
					for (int i = 0; i < length; i++) {
						if (headX == sectionX[i] && headY == sectionY[i]) {
							alive = false;
							break;
						}
					}
					map[headY][headX] = '^';
				}
				else {
					length++;
					growth(map, sectionX, sectionY, headX, headY, length);
					headY = destCoord;
					map[headY][headX] = '^';
					if (length == (size() - 2) * (size() - 2) + 4) {
						win = true;
						break;
					}
					fruit(map);
				}
			}	
			if(input == 'd') {
				destCoord = headX + 1;
				if (destCoord > size() - 1) {
					destCoord = 0;
				}
				if (map[headY][headX] == '>') {
					continue;
				}
				else if (map[headY][destCoord] == '#') {
					wall = true;
				}
				else if (map[headY][destCoord] != '@') {
					move(map, sectionX, sectionY, length, headX, headY);
					headX = destCoord;
					for (int i = 0; i < length; i++) {
						if (headX == sectionX[i] && headY == sectionY[i]) {
							alive = false;
							break;
						}
					}
					map[headY][headX] = '<';
				}
				else {
					length++;
					growth(map, sectionX, sectionY, headX, headY, length);
					headX = destCoord;
					map[headY][headX] = '<';
					if (length == (size() - 2) * (size() - 2) + 4) {
						win = true;
						break;
					}
					fruit(map);
				}
			}				
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++) {
					System.out.print(map[i][j]);
					System.out.print(" ");
				}	
				System.out.println();
			}
		}
		if (!alive) {
			System.out.println("The snake ate itself and died in agony. Game over.");
		}
		else if (win) {
			System.out.println("You won! Congratulations! You can undoubtedly live the snake life!");
		}
		else if (wall) {
			System.out.println("The snake hit the wall and died. Game over.");
		}
	}
	// Метод для фрукта
	public static void fruit(char[][] map) {

		Random r = new Random();
		int y, x;
		do {
			y = r.nextInt(size());
			x = r.nextInt(size());
		}
		while (map[y][x] != ' ');
		map[y][x] = '@';
	}
	// *
	// Метод для движения
	public static void move(char[][] map, int[] sectionX, int[] sectionY, int length, int headX, int headY) {

		map[sectionY[0]][sectionX[0]] = ' ';
		for (int i = 0; i < length - 1; i++) {
			sectionX[i] = sectionX[i + 1];
			sectionY[i] = sectionY[i + 1];
		}
		sectionX[length - 1] = headX;
		sectionY[length - 1] = headY;
		map[headY][headX] = '*';
	}
	// *
	// Метод для роста
	public static void growth(char[][] map, int[] sectionX, int[] sectionY, int headX, int headY, int length) {

		sectionX[length - 1] = headX;
		sectionY[length - 1] = headY;
		map[headY][headX] = '*';
	}
	// *
}