package seafight;

public interface Field {
    void inicialize(int sizeOfField);
    void showField();
    void markCellOfField(int i, int j, char signOfMark);
    char getMarkOfCell(int i, int j);
}
