import java.util.Arrays;
 
public class BattleShipsTest {
    public static void main(String[] args) {
        Tests.RunTests();
    }
}
 
class Battleships {
    // Method 1.1
    public static boolean ValidBoardSquare(char BoardSquare) {
        // Check if the board square is a valid character
        return BoardSquare == '.' || BoardSquare == 'S' || BoardSquare == '*';
    }
 
    // Method 1.2
    public static int ValidBoard(char[][] Board) {
        // Check each square of the board
        for (char[] row : Board) {
            for (char square : row) {
                // Check if the square is a valid character
                if (square != '.' && square != 'S' && square != '*') {
                    return -1;  // Invalid square found
                }
            }
        }
        return 1;  // All squares are valid
    }
 
    // Method 1.3
    public static int NumberSunk(char[][] Board) {
        int count = 0;
        // Define the sizes of the different ships
        int[] shipSizes = {5, 4, 3, 2};

        // Count the number of sunk ships on the board
        for (int i = 0; i < Board.length; i++) {
            int length = 0;
            for (int j = 0; j < Board[i].length; j++) {
                if (Board[i][j] == '*') {
                    // Check for a sunk ship in the row
                    length++;
                } else {
                    // Check if the length matches the size of any ship
                    for (int size : shipSizes) {
                        if (length == size) {
                            count++;
                            break;
                        }
                    }
                    length = 0;  // Reset the length for the next ship
                }
            }
            // Check for a ship at the end of the row
            for (int size : shipSizes) {
                if (length == size) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
//method 4
    public static int Hit(int row, char column, char[][] board) {
        int colIndex = column - 'A';
        if (row < 1 || row > 10 ) return -1;
        else if ( colIndex < 0 || colIndex > 9) return -2;// Invalid indices
    
        switch (board[row - 1][colIndex]) {
            case 'S':
                return 1; // Hit
            case '.':
                return 2; // Miss
            case '*':
                return 3; // Repeated hit
            default:
                return 0; // Invalid value
        }
    }

    // Method 1.5
    public static int CountShips(char[][] Board, String ShipType, String DamageType) {
        int count = 0;
        char target = ' ';
        // Determine the target character based on the ship type and damage type
        if (ShipType.equals("Destroyer")) {
            target = DamageType.equals("sunk") ? '*' : 'S';
        }
        // Count the number of target ships on the board
        for (char[] row : Board) {
            for (char square : row) {
                if (square == target) {
                    count++;
                }
            }
        }
        return count;
    }
}

class Tests {
    static char[][] validBoard1 = {
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'S', 'S', 'S', '.', '.', 'S', 'S', 'S', 'S', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'*', '*', 'S', '*', '.', '*', '.', '*', '*', '*'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'S', 'S', '*', '*', '*', '.', '.'},
            {'.', '.', '.', '*', '*', '*', '*', '*', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '*', 'S', 'S', '*', '.', '*', '*', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    };
 
    static char[][] validBoard2 = {
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'S', 'S', 'S', '.', '.', 'S', 'S', 'S', 'S', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'*', '*', 'S', '*', '.', '*', '.', '*', '*', '*'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'S', 'S', '*', '*', '*', '.', '.'},
            {'*', 'S', '.', '*', '*', '*', '*', '*', '.', '.'},
            {'.', '*', '.', '.', '*', '*', 'S', '*', '*', '.'},
            {'.', '.', '*', 'S', 'S', '*', '.', '*', '*', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '*', '*'}
    };
 
    static char[][] invalidBoard = {
            {'.', '.', '.', '.', '.', '.', 'M', '.', '.', '.'},
            {'S', 'S', 'S', '.', '.', 'S', 'S', 'S', 'S', '.'},
            {'.', '.', '.', 'U', '.', 'N', '.', '.', '.', '.'},
            {'*', '*', 'S', '*', '.', '*', '.', '*', '*', '*'},
            {'.', '.', '.', 'G', '.', 'G', '.', '.', 'S', '.'},
            {'.', '.', '.', 'S', 'S', '*', '*', '*', '.', '.'},
            {'|', '.', '3', '*', '*', '*', '*', '*', '.', '2'},
            {'.', '.', '.', '.', '.', '.', '.', 'G', '.', '.'},
            {'.', 'b', '*', 'S', 'S', '*', '.', '*', '*', '.'},
            {'.', '.', '.', '.', 'Z', '.', '.', '.', '.', '.'}
    };
 
    public static void RunTests() {
        Test(Battleships.ValidBoardSquare('*'), "Valid Board Square", 1);
        Test(Battleships.ValidBoardSquare('S'), "Valid Board Square", 2);
        Test(!Battleships.ValidBoardSquare('6'), "Valid Board Square", 3);
        System.out.println();
 
        Test(Battleships.ValidBoard(validBoard1) == 1, "Valid Board", 1);
        Test(Battleships.ValidBoard(invalidBoard) == -1, "Valid Board", 2);
        System.out.println();
 
        Test(Battleships.NumberSunk(validBoard1) == 3, "Number Sunk", 1);
        Test(Battleships.NumberSunk(validBoard2) == 4, "Number Sunk", 2);
        System.out.println();
 
        Test(Battleships.Hit(1, 'A', validBoard1) == 1, "Hit", 1);
        Test(Battleships.Hit(1, 'G', invalidBoard) == -1, "Hit", 2);
        System.out.println();
 
        Test(Battleships.CountShips(validBoard2, "Destroyer", "sunk") == 2, "Count Ships", 1);
        Test(Battleships.CountShips(validBoard1, "2", "damaged") == 2, "Count Ships", 2);
        Test(Battleships.CountShips(validBoard2, "AwesomeShip", "undamaged") == -1, "Count Ships", 3);
        System.out.println();
 
    }
    static void Test(Boolean Expected, String TestName, int TestNumber) {
        if (Expected) {
            System.out.println("Running test '" + TestName + "' Case " + TestNumber + " ... \u001b[32mok\u001b[0m");
        } else {
            System.out.println("Running test '" + TestName + "' Case " + TestNumber + " ... \u001b[31mfail\u001b[0m");
        }
    }
}