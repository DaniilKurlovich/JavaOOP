package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class Main {
    public static void main(String[] args) {
        PlotTree<String> root = new PlotTree<String>("level_0");
        {
            PlotTree<String> node0 = root.addChild("level_1.1");
            PlotTree<String> node1 = root.addChild("level_1.2");
            PlotTree<String> node2 = root.addChild("level_1.3");
            {
                PlotTree<String> node20 = node2.addChild(null);
                PlotTree<String> node21 = node2.addChild("node21");
                {
                    PlotTree<String> node210 = node20.addChild("node210");
                }
            }
        }
    }
}
