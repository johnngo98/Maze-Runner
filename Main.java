import java.util.*;

// this program is a simple maze solving program that goes through the maze, however, it cannot have any dead ends and only one solution through the maze
class Main
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter the width of the maze: ");
    int col = Integer.parseInt(scan.nextLine()); // stores the user input in col as an int

    System.out.println("Enter the height of the maze: ");
    int row = Integer.parseInt(scan.nextLine());

    char [][] maze = new char [row][col]; // create a 2d array that takes the height and width from the user
    System.out.println("\nCreate your maze: Use * for the walls, ' ' for the empty spaces, O for start, and X for the end: ");

    for (int i = 0; i < row; i++) // loop through the row of the array
    {
      String create = scan.nextLine(); // for each row, the user will create the maze and store that inside the string variable create
      for (int j = 0; j < col; j++) // for each element inside of the string for each character, put that into our array
      {
        maze[i][j] = create.charAt(j); // in the row at i and col at j, set that equal to the element at j in the line that the user typed in
      }
    }

    System.out.println("Direction: " + solve(maze, col, row)); // call the path method to print
    scan.close(); // close the scanner
  }

  public static String solve(char[][] maze, int col, int row)
  {
    int startX = -1; // starting positions
    int startY = -1;

    // search through the 2d array
    for (int i = 0; i < row; i++)
    {
      for (int j = 0; j < col; j++)
      {
        if (maze[i][j] == 'O') // check to see if our starting position is equal to the elements at i and java
        {
          startX = j; // if so, set the starting positions to the correct element
          startY = i;
        }
      }
    }

    if (startX == -1 || startY == -1) // checks to see if the user actually enters a starting character
    {
      System.out.println("No solution");
      return "Starting position was not found"; // if not, print error message
    }

    // initiate variables for where our current location is in the maze
    // set our current positions equal to our starting positions since that is where we have to start when going through the maze
    int currentX = startX;
    int currentY = startY;

    // initiate the variable path to store the path so that as we walk through the variables, we can store in the string the directions that we've gone
    String path = "";

    // now look in each direction to see if there is a wall or '*'
    while (maze[currentY][currentX] != 'X') // keep taking steps until the current location reaches the end ("X")
    {
      // first check to see if we can move down and not out of bounds
      // note: currentY + 1 because currentY starts at 0 and gets bigger as it moves down
      if (currentY + 1 < col && maze[currentY + 1][currentX] != '*')
      {
        path += "Down ";
        currentY += 1; // change currentY to equal to the movement in that direction
      }
      // if can't move down, check to see if it can move right
      else if (currentX + 1 < row && maze[currentY][currentX + 1] != '*')
      {
        path += "Right ";
        currentX += 1;
      }
      // if can't move right, check to see if it can move up
      // now it's >= 0 since all indices have to be positive
      else if (currentY - 1 >= 0 && maze[currentY - 1][currentX] != '*')
      {
        path += "Up ";
        currentY -= 1;
      }
      // if can't move up, check to see if it can move left
      else if (currentX - 1 >= 0 && maze[currentY][currentX - 1] != '*')
      {
        path += "Left ";
        currentX -= 1;
      }
      // if there is a dead end, then return an error message
      else
      {
        System.out.println("No Solution");
        return "Dead end found!";
      }
    } // end while loop
    return path;
  } // end path method
} // end Main class