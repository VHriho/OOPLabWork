import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTest {

    //конструктори, що створюють пусту матрицю, матрицю заданого розміру та копію іншої матриці
    @Test
    void step2() {
        Matrix emptyMatrix = new Matrix();
        Matrix selfDimensioned = new Matrix(3, 3);
        Matrix copiedMatrix = new Matrix(selfDimensioned);

        Assertions.assertEquals(3, selfDimensioned.getRows());
        Assertions.assertEquals(3, selfDimensioned.getColumns());
        Assertions.assertEquals(0, emptyMatrix.getMatrix().length);
        Assertions.assertEquals(true, copiedMatrix.equals(selfDimensioned));

        //виключення при введені від'ємного параметру розмірності
        Throwable thrown = Assertions.assertThrows(NegativeArraySizeException.class, () -> new Matrix(-2,3));
        Assertions.assertEquals("Row or column value is negative", thrown.getMessage());
    }

    @Test
    void step2ForImmutable() {
        ImmutableMatrix emptyMatrix = new ImmutableMatrix();
        ImmutableMatrix selfDimensioned = new ImmutableMatrix(3,3);
        ImmutableMatrix copiedMatrix = new ImmutableMatrix(selfDimensioned);
        ImmutableMatrix matrix = new ImmutableMatrix(new Matrix(4,4));
        ImmutableMatrix matrix1 = new ImmutableMatrix(new ImmutableMatrix(5,5));


        Assertions.assertEquals(3, selfDimensioned.getRows());
        Assertions.assertEquals(3, selfDimensioned.getColumns());
        Assertions.assertEquals(0, emptyMatrix.getMatrix().length);
        Assertions.assertEquals(4, matrix.getMatrix().length);
        Assertions.assertEquals(5, matrix1.getMatrix().length);
        Assertions.assertEquals(true, copiedMatrix.equals(selfDimensioned));

        //виключення при введені від'ємного параметру розмірності
        Throwable thrown = Assertions.assertThrows(NegativeArraySizeException.class, () -> new ImmutableMatrix(-1,3));
        Assertions.assertEquals("Row or column value is negative", thrown.getMessage());
    }

    //методи, що дозволяють заповнити матрицю значеннями
    @Test
    void step3() {
        Matrix matrix = new Matrix(2,2);
        matrix.setElem(0,0,1);
        matrix.setElem(0,1,2);
        matrix.setElem(1,0,3);
        matrix.setElem(1,1,4);

        Matrix matrix1 = new Matrix(3,3);
        matrix1.setRandomElem();

        Matrix matrix2 = new Matrix();

        Matrix matrix3 = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix3.fillElem(filledMatrix);

        Assertions.assertEquals(1,matrix3.getMatrix()[0][0]);
        Assertions.assertEquals(2,matrix3.getMatrix()[0][1]);
        Assertions.assertEquals(3,matrix3.getMatrix()[0][2]);
        Assertions.assertEquals(4,matrix3.getMatrix()[1][0]);
        Assertions.assertEquals(5, matrix3.getMatrix()[1][1]);

        //виключення при від'ємному значені рядка або стовпчика
        Throwable thrown = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix.setElem(-1,0,1));
        Assertions.assertEquals("Row or column value is negative", thrown.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.setElem(1,5,1));
        Assertions.assertEquals("Row or column value out of matrix dimension", thrown1.getMessage());

        //виключення при заповнені матриці розмірності 0
        Throwable thrown2 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix2.setRandomElem());
        Assertions.assertEquals("Dimension of matrix is 0", thrown2.getMessage());
    }

    @Test
    void step3ForImmutable() {
        ImmutableMatrix matrix = new ImmutableMatrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};

        //виключення при зміні значення immutable матриці
        Throwable thrown = Assertions.assertThrows(UnsupportedOperationException.class, () -> matrix.setElem(1,0,1));
        Assertions.assertEquals("Immutable Matrix cannot be changed", thrown.getMessage());

        //виключення при зміні випадковими значеннями immutable матриці
        Throwable thrown1 = Assertions.assertThrows(UnsupportedOperationException.class, () -> matrix.setRandomElem());
        Assertions.assertEquals("Immutable Matrix cannot be changed", thrown1.getMessage());

        //виключення при зміні заданим набором значеннь
        Throwable thrown2 = Assertions.assertThrows(UnsupportedOperationException.class, () -> matrix.fillElem(filledMatrix));
        Assertions.assertEquals("Immutable Matrix cannot be changed", thrown2.getMessage());
    }

    //методи, що дозволяють отримати заданий елемент, рядок чи стовпчик
    @Test
    void step4() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix.fillElem(filledMatrix);

        Assertions.assertEquals(1, matrix.getElem(0,0));
        Assertions.assertEquals(filledMatrix[0][0], matrix.getRow(0)[0]);
        Assertions.assertEquals(filledMatrix[0][1], matrix.getColumn(1)[0]);

        //виключення при від'ємному значені рядка або стовпчика
        Throwable thrown = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix.getElem(2, -1));
        Assertions.assertEquals("Row or column value is negative", thrown.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.getElem(2, 4));
        Assertions.assertEquals("Row or column value out of matrix dimension", thrown1.getMessage());

        //виключення при від'ємному значені рядка
        Throwable thrown3 = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix.getRow(-1));
        Assertions.assertEquals("Row value is negative", thrown3.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown4 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.getRow(4));
        Assertions.assertEquals("Row value out of matrix dimension", thrown4.getMessage());

        //виключення при від'ємному значені стовпця
        Throwable thrown5 = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix.getColumn(-1));
        Assertions.assertEquals("Column value is negative", thrown5.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown6 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.getColumn(4));
        Assertions.assertEquals("Column value out of matrix dimension", thrown6.getMessage());
    }

    @Test
    void step4ForImmutable() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix.fillElem(filledMatrix);
        ImmutableMatrix matrix1 = new ImmutableMatrix(matrix);

        matrix.setElem(0,0,0); //іммутабельна матриця не змінилась

        Assertions.assertEquals(1, matrix1.getElem(0,0));
        Assertions.assertEquals(1, matrix1.getRow(0)[0]);
        Assertions.assertEquals(2, matrix1.getColumn(1)[0]);

        //виключення при від'ємному значені рядка або стовпчика
        Throwable thrown = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix1.getElem(2, -1));
        Assertions.assertEquals("Row or column value is negative", thrown.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown1 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix1.getElem(2, 4));
        Assertions.assertEquals("Row or column value out of matrix dimension", thrown1.getMessage());

        //виключення при від'ємному значені рядка
        Throwable thrown3 = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix1.getRow(-1));
        Assertions.assertEquals("Row value is negative", thrown3.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown4 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix1.getRow(4));
        Assertions.assertEquals("Row value out of matrix dimension", thrown4.getMessage());

        //виключення при від'ємному значені стовпця
        Throwable thrown5 = Assertions.assertThrows(NegativeArraySizeException.class, () -> matrix1.getColumn(-1));
        Assertions.assertEquals("Column value is negative", thrown5.getMessage());

        //виключення при порушені вказання розмірності
        Throwable thrown6 = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix1.getColumn(4));
        Assertions.assertEquals("Column value out of matrix dimension", thrown6.getMessage());
    }

    //розмірність матриці
    @Test
    public void step5() {
        Matrix matrix = new Matrix();
        Matrix matrix1 = new Matrix(3,3);
        Matrix matrix2 = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix2.fillElem(filledMatrix);
        Matrix matrix3 = new Matrix(matrix2);

        Assertions.assertEquals(0, matrix.getDimension().get("Rows"));
        Assertions.assertEquals(3,matrix1.getDimension().get("Columns"));
        Assertions.assertEquals(3,matrix3.getDimension().get("Columns"));
    }

    @Test
    public void step5ForImmutable() {
        ImmutableMatrix matrix = new ImmutableMatrix();
        ImmutableMatrix matrix1 = new ImmutableMatrix(3,3);
        Matrix matrix2 = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix2.fillElem(filledMatrix);
        ImmutableMatrix matrix3 = new ImmutableMatrix(matrix2);

        Assertions.assertEquals(0, matrix.getDimension().get("Rows"));
        Assertions.assertEquals(3,matrix1.getDimension().get("Columns"));
        Assertions.assertEquals(3,matrix3.getDimension().get("Columns"));
    }

    //методи equals та hashCode
    @Test
    public void step6() {
        Matrix matrix = new Matrix();
        Matrix matrix1 = new Matrix(3,3);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix3.fillElem(filledMatrix);

        Assertions.assertEquals(false, matrix.equals(matrix1));
        Assertions.assertEquals(false, matrix3.equals(matrix1));
        Assertions.assertEquals(true, matrix1.equals(matrix2));
        Assertions.assertEquals(true, matrix1.hashCode() == matrix2.hashCode());
    }

    @Test
    public void step6ForImmutable() {
        ImmutableMatrix matrix = new ImmutableMatrix();
        ImmutableMatrix matrix1 = new ImmutableMatrix(3,3);
        ImmutableMatrix matrix2 = new ImmutableMatrix(matrix1);
        Matrix matrix3 = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix3.fillElem(filledMatrix);
        ImmutableMatrix matrix4 = new ImmutableMatrix(matrix3);

        Assertions.assertEquals(false, matrix.equals(matrix1));
        Assertions.assertEquals(true, matrix1.equals(matrix2));
        Assertions.assertEquals(false, matrix4.equals(matrix3));

        Assertions.assertEquals(false, matrix1.hashCode() == matrix4.hashCode());
        Assertions.assertEquals(true, matrix1.hashCode() == matrix2.hashCode());
        Assertions.assertEquals(true, matrix3.hashCode() == matrix4.hashCode());
    }

    //перевірка мутабельності матриці
    @Test
    public void step7() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix.fillElem(filledMatrix);

        Matrix matrix1 = new Matrix(matrix);

        matrix.setElem(0,0,0);

        Assertions.assertEquals(0, matrix.getElem(0,0));
        Assertions.assertEquals(0,matrix1.getElem(0,0));
    }

    //перевірка іммутабельності матриці
    @Test
    public void step7ForImmutable() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix.fillElem(filledMatrix);

        ImmutableMatrix matrix1 = new ImmutableMatrix(matrix);

        matrix.setElem(0,0,0);

        ImmutableMatrix matrix2 = new ImmutableMatrix(matrix);

        Assertions.assertEquals(0, matrix.getElem(0,0));
        Assertions.assertEquals(1,matrix1.getElem(0,0));
        Assertions.assertEquals(0,matrix2.getElem(0,0));
    }

    //додавання матриць та множення на скаляр
    @Test
    public void step8() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix.fillElem(filledMatrix);
        Matrix matrix1 = new Matrix(matrix);

        Matrix matrix2 = new Matrix(2,2);
        matrix2.setElem(0,0,1);
        matrix2.setElem(0,1,2);
        matrix2.setElem(1,0,3);
        matrix2.setElem(1,1,4);

        Assertions.assertEquals(2, Matrix.sumMatrix(matrix, matrix1).getMatrix()[0][0]);
        Assertions.assertEquals(4, Matrix.sumMatrix(matrix, matrix1).getMatrix()[0][1]);
        Assertions.assertEquals(6, Matrix.sumMatrix(matrix, matrix1).getMatrix()[0][2]);
        Assertions.assertEquals(8, Matrix.sumMatrix(matrix, matrix1).getMatrix()[1][0]);
        Assertions.assertEquals(10, Matrix.sumMatrix(matrix, matrix1).getMatrix()[1][1]);

        Assertions.assertEquals(3, Matrix.multMatrix(matrix, 3).getMatrix()[0][0]);
        Assertions.assertEquals(6, Matrix.multMatrix(matrix, 3).getMatrix()[0][1]);
        Assertions.assertEquals(9, Matrix.multMatrix(matrix, 3).getMatrix()[0][2]);
        Assertions.assertEquals(12, Matrix.multMatrix(matrix, 3).getMatrix()[1][0]);
        Assertions.assertEquals(15, Matrix.multMatrix(matrix, 3).getMatrix()[1][1]);

        //виняток при нерівності розмірностей матриць, що сумуються
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> Matrix.sumMatrix(matrix1, matrix2));
        Assertions.assertEquals("Matrices must have an equal number of columns and rows", thrown.getMessage());
    }

    //множення матриць
    @Test
    public void step9() {
        Matrix matrix = new Matrix(2,2);
        matrix.setElem(0,0,2);
        matrix.setElem(0,1,3);
        matrix.setElem(1,0,2);
        matrix.setElem(1,1,7);

        Matrix matrix1 = new Matrix(1,2);
        matrix1.setElem(0,0,4);
        matrix1.setElem(0,1,5);

        Matrix matrix2 = new Matrix(3,3);
        matrix2.setRandomElem();

        Assertions.assertEquals(18, Matrix.multiplication(matrix1, matrix).getMatrix()[0][0]);
        Assertions.assertEquals(47, Matrix.multiplication(matrix1, matrix).getMatrix()[0][1]);

        //виняток при нерівності розмірностей матриць, що перемножуються
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> Matrix.multiplication(matrix, matrix2));
        Assertions.assertEquals("Matrices must have an equal number of columns and rows", thrown.getMessage());
    }

    //повертає транспоновану матрицю
    @Test
    public void step10() {
        Matrix matrix = new Matrix(3,3);
        float[][] filledMatrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix.fillElem(filledMatrix);

        Assertions.assertEquals(1, Matrix.transposeMatrix(matrix).getMatrix()[0][0]);
        Assertions.assertEquals(4, Matrix.transposeMatrix(matrix).getMatrix()[0][1]);
        Assertions.assertEquals(7, Matrix.transposeMatrix(matrix).getMatrix()[0][2]);
        Assertions.assertEquals(2, Matrix.transposeMatrix(matrix).getMatrix()[1][0]);
    }
}
