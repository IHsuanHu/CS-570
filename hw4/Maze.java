import java.util.ArrayList;
import java.util.Stack;

/**
 * Name: I-Hsuan Hu
 * CWID: 20010728
 */
public class Maze implements GridColors {
  /** The maze */
  private TwoDimGrid maze;
  public Maze(TwoDimGrid m) {
    maze = m;
  }
  /** Wrapper method. */
  public boolean findMazePath() {
    return findMazePath(0, 0); // (0, 0) is the start point.
  }

  /**
   * Attempts to find a path through point (x, y).
   * @pre Possible path cells are in BACKGROUND color;
   *      barrier cells are in ABNORMAL color.
   * @post If a path is found, all cells on it are set to the
   *       PATH color; all cells that were visited but are
   *       not on the path are in the TEMPORARY color.
   * @param x The x-coordinate of current point
   * @param y The y-coordinate of current point
   * @return If a path through (x, y) is found, true;
   *         otherwise, false
   */
  public boolean findMazePath(int x, int y) {
    // COMPLETE HERE FOR PROBLEM 1
    //return false if point or path is not in grid
    if(x < 0 || y <0 || x >= maze.getNCols() || y >= maze.getNRows() || !maze.getColor(x,y).equals(NON_BACKGROUND)){
      return false;
    //return true if the point is at exit
    }else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
      maze.recolor(x,y,GridColors.PATH);
      return true;
    }
    // the point paint color
      maze.recolor(x,y,GridColors.PATH);
      // if the next point is exit
      if(findMazePath(x - 1,y) || findMazePath(x + 1, y) || findMazePath(x, y+1) || findMazePath(x, y -1)){
        return true;
      }
      // recolor current point to TEMPORARY
      maze.recolor(x,y,GridColors.TEMPORARY);
      return false;
  }

  // ADD METHOD FOR PROBLEM 2 HERE
  // helper method
  public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x , int y ){
    ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    Stack<PairInt> trace = new Stack<>();
    findMazePathStackBased(0,0,result,trace);
    return result;
  }

  public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
    if(maze.getColor(x,y).equals(NON_BACKGROUND)){
      return;
    }else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
      // add point to trace, add trace to result
      ArrayList<PairInt> temp = new ArrayList<>();
      temp.add(0, new PairInt(x, y));
      temp.addAll(0, trace);
      result.add(temp);
      return;
    }
      // push point to trace
      trace.push(new PairInt(x,y));
      maze.recolor(x,y,GridColors.PATH);
      // using recursion to find neighbors
      findMazePathStackBased(x + 1, y, result, trace);
      findMazePathStackBased(x, y + 1, result, trace);
      findMazePathStackBased(x - 1, y, result, trace);
      findMazePathStackBased(x, y - 1, result, trace);
      trace.pop();
      maze.recolor(x, y, GridColors.NON_BACKGROUND);
  }

  // ADD METHOD FOR PROBLEM 3 HERE
  public ArrayList<PairInt> findMazePathMin (int x, int y) {
    ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x, y);
    int index = 0;
    int min = paths.get(index).size();
    // to find minimum path from all possible paths
    for (int i = 1; i < paths.size(); i++) {
      if (paths.get(i).size() < min) {
        index = i;
        min = paths.get(i).size();
      }
    }
    return paths.get(index);
  }

  /*<exercise chapter="5" section="6" type="programming" number="2">*/
  public void resetTemp() {
    maze.recolor(TEMPORARY, BACKGROUND);
  }
  /*</exercise>*/

  /*<exercise chapter="5" section="6" type="programming" number="3">*/
  public void restore() {
    resetTemp();
    maze.recolor(PATH, BACKGROUND);
    maze.recolor(NON_BACKGROUND, BACKGROUND);
  }
  /*</exercise>*/
}
/*</listing>*/
