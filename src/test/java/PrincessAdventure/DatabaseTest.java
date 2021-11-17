package PrincessAdventure;

import ge.DatabaseOperation;

public class DatabaseTest {

    public static void main(String[] args) {
        DatabaseOperation dataBaseTry = new DatabaseOperation();
        dataBaseTry.setTableName("recordTest2");
        dataBaseTry.inputString("2048");
    }
}
