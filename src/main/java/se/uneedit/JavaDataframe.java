package se.uneedit;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Contruct a dataframe using a array of strings (column names) and
 * two dimensional array of floats (data sheet or matrix).
 * Returns a excel like dataframe.
 * psudo code: JavaDataframe({col1, col2, col3}, {{1,2,3}, {4,5,6}, {7,8,9}})
 * returns:
 * col1   col2   col3
 *  1      2      3
 *  4      5      6
 *  7      8      9
 */

public class JavaDataframe {

    static class col{
        int id;
        String name;

        private col(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    static class row{
        int id;

        private row(int id){
            this.id = id;
        }
    }

    static class element{
        int Col;
        int Row;
        float data;

        public element(int col, int row, float data) {
            Col = col;
            Row = row;
            this.data = data;
        }

        public int getRow(){
            return Row;
        }
        public int getCol(){
            return Col;
        }




    }

    ArrayList<col> columns = new ArrayList<col>();
    ArrayList<element> elements = new ArrayList<element>();

    public JavaDataframe(String[] cols, Float[][] initialData){
        for(int i = 0 ; i < cols.length ; i++){
            columns.add(new col(i, cols[i]));
        }
        for(int rowId = 0 ; rowId < initialData.length; rowId++){
            for(int colId = 0 ; colId < cols.length ; colId++){
                elements.add(new element(colId, rowId, initialData[rowId][colId]));
            }
        }
    }

    public col[] getColumns() {
        ArrayList cols = this.columns;
        col[] colArray = (col[]) cols.toArray(new col[cols.size()]);
        return colArray;
    }

    public String getColumnName(int colId){
        for(col c : getColumns()){
            if (c.id == colId){
                return c.name;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private ArrayList<element> getElements() {
        return elements;
    }

    public float getElement(int row, int col){
        //TODO May use sorting to speed this up.
        for(element ele : getElements()){
            if(ele.Col == col && ele.Row == row){
                return ele.data;
            }
        }
        throw new ArrayIndexOutOfBoundsException();

    }

    public HashMap<String, Float> getRowById(int rowId){
        ArrayList<element> foundElements = new ArrayList<element>();
        for(element ele : getElements()){
            if (ele.Row == rowId){
                foundElements.add(ele);
            }
        }

        HashMap<String, Float> row = new HashMap<String, Float>();
        for(element ele : foundElements){
            row.put(getColumnName(ele.Col), ele.data);
        }

        return row;
    }

    public void addColumnByOperation(){
        //TODO Add a column to dataframe, data is an operation of some column
    }

    public void addColumnFromData(){
        // TODO Add a column to dataframe, data is provided as an array
    }

    public void applyToCol(int colId){
        // TODO Apply function to column to modify data
    }

    public void addRow(String name){
        // TODO Add row
    }

    public static void main(String args[]){
        // Just some test from yahoo chartapi. Only for demo! Do not use!
        String APIurl = "http://chartapi.finance.yahoo.com/instrument/1.0/AAPL/chartdata;type=quote;range=10d/csv";
        Float[][] initStringData = testData.testData.getCSVFromUrl(APIurl); // NOTICE: DYNAMIC DATA!!!
        JavaDataframe myDF = new JavaDataframe(new String[] {"timestam", "open", "close", "high", "low", "volume"}, initStringData);
        //System.out.println("FOUND : " + myDF.getElement(10,5));
        System.out.println("ROW : " + myDF.getRowById(0));
        System.out.println("VOLUME : " + myDF.getRowById(0).get("volume"));




    }


}
