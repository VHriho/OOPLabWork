import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

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
    public Matrix(Matrix copiedMatrix) {
         this.elements = copiedMatrix.elements;
    }

    //заповнює матрицю значеннями
    @Override
    public void setElem(int m, int n, float elem) {
        if (m < 0 || n < 0)
            throw new NegativeArraySizeException("Row or column value is negative");
        if (m >= elements.length || n >= elements[0].length)
            throw new ArrayIndexOutOfBoundsException("Row or column value out of matrix dimension");
        else
            elements[m][n] = elem;
    }

    //заповнює матрицю випадковими значеннями
    @Override
    public void setRandomElem() {
        if (elements.length == 0)
            throw new ArrayIndexOutOfBoundsException("Dimension of matrix is 0");
        Random setRandom = new Random();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                elements[i][j] = setRandom.nextFloat(10);
            }
        }
    }

    @Override
    public void fillElem(float[][] matrix) {
        for (int i = 0; i < elements.length; i++)
            System.arraycopy(matrix[i], 0, elements[i], 0, elements[0].length);
    }

    //повертає елемент на претині рядка і стовпчика
    @Override
    public float getElem(int m, int n) {
        if (m < 0 || n < 0)
            throw new NegativeArraySizeException("Row or column value is negative");
        if (m >= elements.length || n >= elements[0].length)
            throw new ArrayIndexOutOfBoundsException("Row or column value out of matrix dimension");
        else
            return elements[m][n];
    }

    //повертає заданий рядок
    @Override
    public float[] getRow(int m) {
        if (m < 0)
            throw new NegativeArraySizeException("Row value is negative");
        if (m >= elements.length)
            throw new ArrayIndexOutOfBoundsException("Row value out of matrix dimension");
        else
            return elements[m];

    }

    //повертає кількість рядків
    @Override
    public int getRows(){
        return elements.length;
    }

    //повертає заданий стовпчик
    @Override
    public float[] getColumn(int n) {
        if (n < 0)
            throw new NegativeArraySizeException("Column value is negative");
        if (n >= elements[0].length)
            throw new ArrayIndexOutOfBoundsException("Column value out of matrix dimension");
        else {
            float[] column = new float[elements[0].length];
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[0].length; j++) {
                    if (j == n)
                        column[i] = elements[i][j];
                }
            }
            return column;
        }
    }

    //повертає кількість стовпців
    @Override
    public int getColumns(){
        return elements[0].length;
    }

    //повертає розмірність матриці
    @Override
    public HashMap<String, Integer> getDimension() {
        HashMap<String, Integer> map = new HashMap<>();
        if (elements.length == 0 || elements[0].length == 0) {
            map.put("Rows", 0);
            map.put("Columns", 0);
        }
        else {
            map.put("Rows", elements.length);
            map.put("Columns", elements[0].length);
        }
        return map;
    }

    //перевизначений метод equals
    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(getClass() != other.getClass())
            return false;
        Matrix newMatrix = (Matrix) other;
        return (elements.length == newMatrix.elements.length && elements[0].length == newMatrix.elements[0].length && Arrays.deepEquals(elements, newMatrix.elements));
    }

    //перевизначений метод hashCode
    @Override
    public int hashCode() {
        return Objects.hash(elements.length, elements[0].length, Arrays.deepHashCode(elements), 31);
    }

    //сума матриць
    public static Matrix sumMatrix(Matrix matrix, Matrix otherMatrix) {
        if (matrix.getRows() != otherMatrix.getRows() || matrix.getColumns() != otherMatrix.getColumns())
            throw new IllegalArgumentException("Matrices must have an equal number of columns and rows");
        Matrix sumOfMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                sumOfMatrix.setElem(i, j, matrix.getElem(i, j) + otherMatrix.getElem(i, j));
            }
        }
        return sumOfMatrix;
    }

    //множення на скаляр
    public static Matrix multMatrix(Matrix matrix, float mult) {
        Matrix resMult = new Matrix(matrix.elements.length, matrix.elements[0].length);
        for (int i = 0; i < matrix.elements.length; i++) {
            for (int j = 0; j < matrix.elements[0].length; j++) {
                resMult.setElem(i, j, matrix.elements[i][j] * mult);
            }
        }
        return resMult;
    }

    //множення матриць
    public static Matrix multiplication(Matrix matrix, Matrix otherMatrix) {
        if (matrix.getColumns() != otherMatrix.getColumns())
            throw new IllegalArgumentException("Matrices must have an equal number of columns and rows");
        Matrix multiplct = new Matrix(matrix.getRows(), otherMatrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < otherMatrix.getColumns(); j++) {
                multiplct.elements[i][j] = 0;
                for (int a = 0; a < matrix.getColumns(); a++) {
                    multiplct.elements[i][j] += matrix.elements[i][a] * otherMatrix.getElem(a,j);
                }
            }
        }
        return multiplct;
    }

    //транспонування матриці
    public static Matrix transposeMatrix(Matrix matrix) {
        Matrix transpose = new Matrix(matrix.elements.length, matrix.elements[0].length);
        for (int i = 0; i < matrix.elements.length; i++)  {
            for (int j = 0; j < matrix.elements[0].length; j++) {
                transpose.elements[i][j] = matrix.elements[j][i];
            }
        }
        return transpose;
    }

    //повертає діагональну матрицю за вказаним вектором
    public static Matrix diagonal(float[] vector) {
        Matrix matrix = new Matrix(vector.length, vector.length);
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                if (i == j) {
                    matrix.elements[i][j] = vector[i];
                }
                else{
                    matrix.elements[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    //Повертає матрицю
    @Override
    public float[][] getMatrix() {
        return elements;
    }
}
