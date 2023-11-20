import java.util.Arrays;

public class Matrix implements IMatrix {
    private float[][] elements;

    //конструктор, що створює пусту матрицю
    public Matrix() {
        elements = new float[0][0];
    }

    //конструктор, що створює матрицю заданого розміру
    public Matrix(int m, int n) {
        if (m < 0 || n < 0)
            throw new NegativeArraySizeException("Row or column value is negative");
        else
            elements = new float[m][n];
    }

    //конструктор, що створює копію іншої матриці
    public Matrix(IMatrix copiedMatrix) {
        elements = new float[copiedMatrix.getRows()][copiedMatrix.getColumns()];
        for (int i = 0; i < copiedMatrix.getRows(); i++)
            elements[i] = Arrays.copyOf(copiedMatrix.getMatrix()[i], copiedMatrix.getMatrix()[i].length);
    }

    //повертає кількість рядків
    @Override
    public int getRows(){
        return elements.length;
    }

    //повертає кількість стовпців
    @Override
    public int getColumns(){
        return elements[0].length;
    }

    //Повертає матрицю
    @Override
    public float[][] getMatrix() {
        return elements;
    }
}
