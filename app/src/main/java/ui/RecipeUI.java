package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    //1.readRecipes()でArrayListを受け取る用のArrayListを作成する
    //2.読み込んだレシピデータが空の場合という条件分岐を行う
    //3.readRecipes()でArrayListを受け取ったArrayList要素(0）を","で分割し、配列に入れる
    //4.配列を再度ArrayListに入れる（利便性のため）
    //5."Recipe Name:" + lineList.get(0)と表示させる
    //6.lineList.get(0)をremove
    //7.残りの要素を表示
    //8.3からをArrayListの大きさまで繰り返す
    private void displayRecipes() {
        ArrayList<String> fileslist = new ArrayList<>(fileHandler.readRecipes());
        System.out.println("Recipes:");
        
        if(!(fileslist.isEmpty())){
            for(String s: fileslist){
                System.out.println("-----------------------------------");
                String[] line = s.split(",");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line));
                System.out.println("Recipe Name:" + lineList.get(0));
                lineList.remove(0);
                System.out.print("Main Ingredients:");
                for(int i = 0; i < lineList.size(); i++){
                    if(i == lineList.size() - 1){
                        System.out.println(lineList.get(i));
                    }else{
                        System.out.print(lineList.get(i) + ", ");
                    }
                }
            }
        }else{
            System.out.println("No recipes available.");
        }


    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    //1.BufferedReaderで"Enter recipe name:"と"Enter main ingredients (comma separated):"の入力を受け付ける
    //2.入力した値を引数にしたfileHandler.addRecipe(recipeName, ingredients);を実行する
    private void addNewRecipe() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter recipe name:");
        String recipeName = reader.readLine();
        System.out.print("Enter main ingredients (comma separated):");
        String ingredients = reader.readLine();
        fileHandler.addRecipe(recipeName, ingredients);
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

