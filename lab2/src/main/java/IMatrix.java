import java.util.HashMap;

public interface IMatrix {

    void setElem(int m, int n, float elem); //заповнює матрицю за вказаними параметрами

    void fillElem(float[][] matrix); //заповнює матрицю переданими значеннями

    void setRandomElem(); //заповнює матрицю випадковими значеннями

    float getElem(int m, int n); //повертає елемент за вказаними параметрами

    float[] getRow(int m); //повертає заданий рядок

    float[] getColumn(int n); //повертає заданий стовпчик

    HashMap<String, Integer> getDimension(); //повертає розмірність матриці

    float[][] getMatrix(); //повертає матрицю

    int getRows(); //повертає кількість рядки

    int getColumns(); //повертає кількість стовпців
}
