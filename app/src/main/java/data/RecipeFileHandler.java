package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    //1.BufferedReaderでファイルの全て読み込み
    //2.読み込んだデータを行ごとにArrayListに追加
    //3.作成したArrayListを返り値として返す
    public ArrayList<String> readRecipes() {
        ArrayList<String> recipesArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null){
                recipesArrayList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return recipesArrayList;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName  レシピ名
     * @param ingredients 材料名
     */
    //1.addRecipeで受け取った引数を入れるArrayListを作成
    //2.引数(recipeName)をArrayListに入れる
    //3.引数(ingredients)を","で分割した要素をArrayListに入れる
    //4.BufferedWriterでファイルに追記させる
    public void addRecipe(String recipeName, String ingredients) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String input = recipeName + "," + ingredients;
            writer.write(input);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        System.out.println("Recipe added successfully.");
    }

}
