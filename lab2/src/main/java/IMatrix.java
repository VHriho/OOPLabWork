public interface IMatrix {

    void setElem(int m, int n, float elem); //заповнює матрицю за вказаними параметрами

    void fillElem(float[][] matrix); //заповнює матрицю переданими значеннями

    void setRandomElem(); //заповнює матрицю випадковими значеннями

    float[][] getMatrix(); //повертає матрицю

    int getRows(); //повертає кількість рядки

    int getColumns(); //повертає кількість стовпців
}
