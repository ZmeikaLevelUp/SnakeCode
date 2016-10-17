import java.util.Scanner;
import java.util.Random;

public class Snake001 {

	public static int size() {

		final int N = 5;
		return N;
	} // метод размера
	public static void main(String [] args) {

		int length = 1; // кол-во секций
		int [] sectionY = new int [size() * size() - 1];
		int [] sectionX = new int [size() * size() - 1];
		char [][] map = new char [size()][size()]; // поле size x size
		int headX = size() / 2, headY = size() / 2; // начальные координаты головы
		char space = '.'; // пустая ячейка
		sectionX[0] = size() / 2 + 1;
		sectionY[0] = size() / 2; // координаты секции змеи
		char section = '*'; // секция змеи
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				map[i][j] = space;
			}
		} // заполнение поля
		map[headY][headX] = '>'; // размещение головы
		map[sectionY[0]][sectionX[0]] = section;
		fruit(map);
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}	
			System.out.println();
		} // вывод поля
		char p;
		boolean alive = true, win = false, wall = false;
		while (alive && !win && !wall) {
			Scanner sc = new Scanner(System.in);
			p = sc.next().charAt(0);	
			if (p == 'a') {
				if (map[headY][headX] == '<') {
					continue;					
				}
				else if (headX - 1 < 0) {
					wall = true;
					break;
				}
				else if (map[headY][headX - 1] == section) {
					alive = false;
					break;
				}
				else if (map[headY][headX - 1] == space) {
					map[sectionY[0]][sectionX[0]] = space;
					move(sectionX, sectionY, length, headX, headY);
					map[headY][headX] = section;
					headX--;
					map[headY][headX] = '>';
				}
				else if (map[headY][headX - 1] == '@') {
					length++;
					growth(sectionX, sectionY, headX, headY, length);
					map[headY][headX] = section;
					headX--;
					map[headY][headX] = '>';
					if (length == size() * size() - 1) {
						win = true;
						break;
					}
					fruit(map);
				}
			}
			if(p == 'w') {
				if (map[headY][headX] == '^') {
					continue;			
				}
				else if (headY - 1 < 0) {
					wall = true;
					break;
				}
				else if (map[headY - 1][headX] == section) {
					alive = false;
					break;
				}
				else if (map[headY - 1][headX] == space) {
					map[sectionY[0]][sectionX[0]] = space;
					move(sectionX, sectionY, length, headX, headY);
					map[headY][headX] = section;
					headY--;
					map[headY][headX] = 'v';
				}
				else if (map[headY - 1][headX] == '@') {
					length++;
					growth(sectionX, sectionY, headX, headY, length);
					map[headY][headX] = section;
					headY--;
					map[headY][headX] = 'v';
					if (length == size() * size() - 1) {
						win = true;
						break;
					}
					fruit(map);
				}
			}
			if(p == 's') {
				if (map[headY][headX] == 'v') {
					continue;			
				}
				else if (headY + 1 > size() - 1) {
					wall = true;
					break;
				}
				else if (map[headY + 1][headX] == section) {
					alive = false;
					break;
				}
				else if (map[headY + 1][headX] == space) {
					map[sectionY[0]][sectionX[0]] = space;
					move(sectionX, sectionY, length, headX, headY);
					map[headY][headX] = section;
					headY++;
					map[headY][headX] = '^';
					System.out.println("move");
				}
				else if (map[headY + 1][headX] == '@') {
					length++;
					growth(sectionX, sectionY, headX, headY, length);
					map[headY][headX] = section;
					headY++;
					map[headY][headX] = '^';
					if (length == size() * size() - 1) {
						win = true;
						break;
					}
					fruit(map);
				}
			}	
			if(p == 'd') {
				if (map[headY][headX] == '>') {
					continue;
				}
				else if (headX + 1 > size() - 1) {
					wall = true;
					break;
				}
				else if (map[headY][headX + 1] == section) {
					alive = false;
					break;
				}
				else if (map[headY][headX + 1] == space) {
					map[sectionY[0]][sectionX[0]] = space;
					move(sectionX, sectionY, length, headX, headY);
					map[headY][headX] = section;
					headX++;
					map[headY][headX] = '<';
				}
				else if (map[headY][headX + 1] == '@') {
					length++;
					growth(sectionX, sectionY, headX, headY, length);
					map[headY][headX] = section;
					headX++;
					map[headY][headX] = '<';
					if (length == size() * size() - 1) {
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
			System.out.println("The snake had gone too far from it's nest and lost. Game over.");
		}
	}
	public static void fruit(char[][] map) {

		Random r = new Random();
		int y, x;
		do {
			y = r.nextInt(size());
			x = r.nextInt(size());
		}
		while (map[y][x] != '.');
		map[y][x] = '@';
	} // метод для фрукта
	public static void move(int[] sectionX, int[] sectionY, int length, int headX, int headY) {

		for (int i = 0; i < length - 1; i++) {
			sectionX[i] = sectionX[i + 1];
			sectionY[i] = sectionY[i + 1];
		}
		sectionX[length - 1] = headX;
		sectionY[length - 1] = headY;
	} // метод для движения
	public static void growth(int[] sectionX, int[] sectionY, int headX, int headY, int length) {

		sectionX[length - 1] = headX;
		sectionY[length - 1] = headY;
	} // метод для роста
}