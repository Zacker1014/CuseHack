// Kevin Kao
// kkao4@u.rochester.edu
// Lab6
// I did not collaborate with anyone on this lab

import java.util.ArrayList;

public class EightQueen {

    public static void main(String[] args) {

        System.out.println("finding eight queen (all solutions): ");
        ArrayList<ArrayList<Integer>> solutions = findEightQueen(); // call the findEightQueen method
        ArrayListToArray(solutions); // change the 2DArrayList to 2DArray

    }

    // findEightQueen method
    private static ArrayList<ArrayList<Integer>> findEightQueen() {
        ArrayList<ArrayList<Integer>> allAnswers = new ArrayList<>(); // initialize the 2DArrayList
        solveEightQueen( new ArrayList<>(), allAnswers, 0); // call the solveEightQueen method
        return allAnswers; // return all the outcome
    }

    private static void solveEightQueen(ArrayList<Integer> oneAnswer, ArrayList<ArrayList<Integer>> allAnswer, int column) {
        if (column == 8) {
            allAnswer.add(new ArrayList<>(oneAnswer)); // add solution to the all solutions
        } else {
            for (int col = 0; col < 8; col++) {
                oneAnswer.add(col);
                /*
                 * check if there is no conflict in the current location, if there is no conflict,
                 * go to the next column.
                 */
                if (noConflict(oneAnswer)) {
                    solveEightQueen( oneAnswer, allAnswer,  column + 1); // call the method again (recursion)
                }
                oneAnswer.remove(oneAnswer.size() - 1); // remove the conflict location
            }
        }
    }

    private static boolean noConflict(ArrayList<Integer> oneAnswer) {
        int currentLocation = oneAnswer.size() - 1;
        for (int i = 0; i < currentLocation; i++) {
            int difference = Math.abs(oneAnswer.get(i) - oneAnswer.get(currentLocation)); // check the location
            /*
             * This if statement tells us whether the current location is diagonally unavailable or conflict with the
             * previous queens locations or not.
             */
            if ((difference == 0) || (difference == currentLocation - i)) {
                return false; // return false if the current location conflicts with previous locations
            }
        }
        return true;
    }

    private static void ArrayListToArray(ArrayList<ArrayList<Integer>> solution) {
        for (int i = 0; i < solution.size(); i++) {
            int[][] board = new int[8][8]; // initialize a new 2DArray
            for (int j = 0; j < solution.get(i).size(); j++) {
                board[solution.get(i).get(j)][j] = 1; // only set the queen locations to 1, whereas the other locations 0
            }
            System.out.println("solution " + (i + 1) + ": ");
            printBoard(board); // call the printBoard method to print out the board
            System.out.println();
        }
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int column : row) {
                if (column == 1) {
                    System.out.print("| Q ");
                } else {
                    System.out.print("| * ");
                }
            }
            System.out.println("|");
        }
    }

}
