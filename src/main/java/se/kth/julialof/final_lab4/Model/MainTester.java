package se.kth.julialof.final_lab4.Model;

public class MainTester {
    public static void main(String[] args){

        SudokuMain test = new SudokuMain();
        System.out.println("Values of gameplan:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.printSquare(row,col));

            }
        }


        System.out.println("\nAll values of correct tiles:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.Tile.getCorrectTile(row,col));

            }
        }

        System.out.println("\nAll values of fixed tiles:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.Tile.getFixedTile(row,col));

            }
        }

        System.out.println("System.out.println(test.checkTiles());");
        test.checkTiles();
        System.out.println(test.checkTiles());

        System.out.println("\nSystem.out.print(test.printSquare(4,0));\n");
        System.out.print(test.printSquare(4,0));
        System.out.println("test.setTiles(4,0,3);\n");
        test.setTile(4,0,3);
        System.out.println("System.out.print(test.printSquare(4,0));\n");
        System.out.print(test.printSquare(4,0));

        System.out.println("test.setTiles(4,0,3);\n");
        test.setTile(3,0,3);




        System.out.println("Values of gameplan:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.printSquare(row,col));

            }
        }

        System.out.println("System.out.println(test.checkTiles());");
        test.checkTiles();
        System.out.println(test.checkTiles());
        System.out.println("\ntest.resetBoard();\n");
//        test.resetBoard();


        System.out.println("Values of gameplan:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.printSquare(row,col));

            }
        }

        System.out.println("Testing hint: \n");
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();
        test.hint();

        System.out.println("Values of gameplan:");
        for(int row = 0; row<9; row++){
            System.out.print("\n");
            for(int col = 0; col<9; col++){
                System.out.print(" " + test.printSquare(row,col));

            }
        }

//        int juliasmamma[][] = test.getTiles();

//        System.out.println("Values of gameplan:");
//        for(int row = 0; row<9; row++){
//            System.out.print("\n");
//            for(int col = 0; col<9; col++){
//                System.out.print(" " + juliasmamma[row][col]);
//
//            }
//        }

//        0 0 0 9 1 4 0 6 2
//        0 1 0 0 0 0 0 5 4
//        0 4 0 0 5 2 1 0 0
//        0 0 6 5 7 9 0 4 1
//        4 0 1 2 3 8 5 0 6
//        3 0 0 1 0 0 8 0 0
//        0 3 9 0 0 0 4 1 8
//        7 5 0 8 2 0 0 3 0
//        0 0 8 4 0 3 2 7 0


//        5 0 3 9 1 4 0 7 0
//        7 1 0 0 0 0 0 5 4
//        0 4 0 0 5 2 0 0 0
//        0 0 7 5 6 9 3 0 1
//        4 0 1 0 0 0 5 9 0
//        3 0 0 1 0 0 8 0 0
//        2 3 9 6 0 0 4 0 8
//        6 5 4 8 0 1 0 3 9
//        0 0 0 4 0 3 2 6 0

    }


}
