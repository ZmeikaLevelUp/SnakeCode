//в разработке...
import java.util.Scanner;

public class Snake {
	
	public static void main(String [] args) {
	final int m = 16;
	final int n = 10;
		char [][] map = new char [n][m];
		int headX = m / 2, headY = n / 2;
		char space = '_';
		int section1X = m / 2 + 1, section1Y = n / 2;
		char section = '@';
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = space;
			}
		}
		int snake = 1;
		map[headY][headX] = '>';
		for (int i = 0; i < snake; i++) {	
		map[section1Y][section1X + i] = section;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}	
			System.out.println();
		}
		char p = ' ';
		while (p != 'p') {
			Scanner sc = new Scanner(System.in);
			p = sc.next().charAt(0);	
			if (p == 'a') {
				if (map[headY][headX] == '<') {
					continue;					
				}
				if (map[headY][headX] == 'v') {
					section1Y = headY + snake;
					section1X = headX;
				}
				if (map[headY][headX] == '^') {
					section1Y = headY - snake;
					section1X = headX;
				}
				if (map[headY][headX] == '>') {
				section1X = headX + snake;
				section1Y = headY;
				}	
				map[headY][headX] = map[section1Y][section1X];
				map[section1Y][section1X] = space;
				headX--;	
				map[headY][headX] = '>';
			}
			if(p == 'w'){
				if (map[headY][headX] == '>') {
					section1X = headX + snake;	
					section1Y = headY;
				}
				if (map[headY][headX] == '<') {
					section1X = headX - snake;
					section1Y = headY;
				}
				if (map[headY][headX] == '^') {
					continue;			
				}
				if (map[headY][headX] == 'v') {
				section1X = headX;
				section1Y = headY + snake;
				}	
				map[headY][headX] = map[section1Y][section1X];
				map[section1Y][section1X] = space;
				headY--;
				map[headY][headX] = 'v';
			}
			if(p == 's'){
				if (map[headY][headX] == 'v') {
					continue;			
				}
				if (map[headY][headX] == '<') {
					section1X = headX - snake;
					section1Y = headY;
				}
				if (map[headY][headX] == '>') {
					section1X = headX + snake;	
					section1Y = headY;
				}
				if (map[headY][headX] == '^') {
				section1X = headX;
				section1Y = headY - snake;
				}	
				map[headY][headX] = map[section1Y][section1X];
				map[section1Y][section1X] = space;
				headY++;
				map[headY][headX] = '^';
			}	
			if(p == 'd'){
				if (map[headY][headX] == '>') {
					continue;
				}
				if (map[headY][headX] == 'v') {
					section1Y = headY + snake;
					section1X = headX;
				}	
				if (map[headY][headX] == '^') {
					section1Y = headY - snake;
					section1X = headX;
				}
				if (map[headY][headX] == '<') {
					section1Y = headY;
					section1X = headX - snake;
					
				}
				map[headY][headX] = map[section1Y][section1X];
				map[section1Y][section1X] = space;
				headX++;
				map[headY][headX] = '<';
			}				
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(map[i][j]);
					System.out.print(" ");
				}	
				System.out.println();
			}
		}
	}	
}
	
	
