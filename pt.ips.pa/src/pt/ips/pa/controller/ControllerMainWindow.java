/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ips.pa.model.jogo.GestorJogo;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class ControllerMainWindow implements Initializable {

    //atributos do package model e extras
    private static final String FICHEIRO = "saveFile.bak";

    //atributos para iniciar um jogo
    private String tema;

    //items da janela main
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonIniciarJogo;

    @FXML
    private MenuItem itemNovoJogo;

    @FXML // fx:id="itemUndo"
    private MenuItem itemUndo; // Value injected by FXMLLoader

    @FXML
    private MenuItem itemReturn;

    @FXML // fx:id="menuView"
    private Menu menuView; // Value injected by FXMLLoader

    @FXML // fx:id="zonaJogo"
    private StackPane zonaJogo; // Value injected by FXMLLoader

    //altera o que o utilizador quer ver
    @FXML // fx:id="viewPontuacao"
    private CheckMenuItem viewPontuacao; // Value injected by FXMLLoader

    @FXML // fx:id="viewNome"
    private CheckMenuItem viewNome; // Value injected by FXMLLoader

    @FXML // fx:id="viewTempo"
    private CheckMenuItem viewTempo; // Value injected by FXMLLoader

    @FXML
    private MenuItem loggerOptions;

    //controladores e respetivo "pai"
    private FXMLLoginController loginController;
    private Parent loginParent;

    private FXMLOptionsWindowController optionsController;
    private Parent optionsParent;

    private FXMLGameWindowController gameController;
    private Parent gameParent;

    private FXMLMusicWindowController musicController;
    private Stage musicStage;

    private FXMLLoggerOptionsController loggerOptionsController;
    private Parent loggerOptionsParent;

    private Stage help;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestorJogo gestor = lerFicheiroSerializado(FICHEIRO);
            GestorJogo.setInstance(gestor);

            FXMLLoader fl1 = createPane("FXMLLogin");
            loginController = (FXMLLoginController) fl1.getController();
            loginParent = fl1.getRoot();

            FXMLLoader fl2 = createPane("FXMLOptionsWindow");
            optionsController = (FXMLOptionsWindowController) fl2.getController();
            optionsParent = fl2.getRoot();

            FXMLLoader fl3 = createPane("FXMLGameWindow");
            gameController = (FXMLGameWindowController) fl3.getController();
            gameParent = fl3.getRoot();

            FXMLLoader fl4 = createPane("FXMLMusicWindow");
            musicController = (FXMLMusicWindowController) fl4.getController();
            musicController.setMusica(new MediaPlayer(new Media(getClass().getResource("/temas/Musica/Main.mp3").toString())), "Main song");
            musicStage = new Stage(StageStyle.UTILITY);
            musicStage.setScene(new Scene(fl4.getRoot()));
            musicStage.setTitle("Music");

            FXMLLoader fl5 = createPane("FXMLLoggerOptions");
            loggerOptionsController = (FXMLLoggerOptionsController) fl5.getController();
            loggerOptionsController.setControladorPrincipal(this);
            loggerOptionsParent = fl5.getRoot();

            zonaJogo.setStyle("-fx-background-image: url('" + getClass().getResource("/temas/background.png").toExternalForm() + "')");
        } catch (IOException ex) {
            Logger.getLogger(ControllerMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void undo() {
        GestorJogo.getInstance().getCareTaker().restoreState(GestorJogo.getInstance().getJogo());
    }

    /**
     * Metodo que grava o estado da aplicacao no final do jogo
     *
     * @param gestor
     */
    public void gravarGestor(GestorJogo gestor) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO))) {
                System.out.println(gestor.getRankingBaseNormal().size());
                oos.writeObject(gestor);
                oos.flush();
                oos.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que recupera as informacoes da aplicacao
     *
     * @param nomeFicheiro
     * @return o estado da aplicacao
     */
    public GestorJogo lerFicheiroSerializado(String nomeFicheiro) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            GestorJogo jogo = (GestorJogo) ois.readObject();
            jogo.reiniciarCareTaker();
            System.out.println(jogo.getRankingBaseNormal().size());

            return jogo;
        } catch (IOException | ClassNotFoundException e) {
            return new GestorJogo();
        }
    }

    public void loadPaneLogin() {
        buttonIniciarJogo.setDisable(true);
        buttonIniciarJogo.setOpacity(0.0);
        this.zonaJogo.getChildren().add(loginParent);
        loginController.setControladorPrincipal(this);
    }

    public void loadPaneOptions() {
        this.zonaJogo.getChildren().add(optionsParent);
        optionsController.setControladorPrincipal(this);
        optionsController.setControladorLogger(loggerOptionsController);
    }

    public void loadPaneGame() {
        if (gameController.isJogoIniciou()) {
            gameController.reiniciar();
        }
        this.zonaJogo.getChildren().add(gameParent);
        gameController.setControladorPrincipal(this);
        gameController.setJogo();
        enableMenu();
    }

    public void loadPaneMusic() throws IOException {
        musicStage.show();
    }

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        if (zonaJogo.getChildren().size() > 1) {
            int size = zonaJogo.getChildren().size();
            for (int i = size - 1; i > 0; i--) {
                zonaJogo.getChildren().remove(i);
            }
        }
        this.itemUndo.setDisable(true);
        loadPaneLogin();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        gravarGestor(GestorJogo.getInstance());
        System.exit(0);
    }

    @FXML
    private void loadHelpWindow() throws IOException {
        help = new Stage(StageStyle.UTILITY);
        help.setTitle("Help");
        help.setScene(new Scene(createPane("FXMLHelpWindow").getRoot()));
        help.show();
    }

    @FXML
    private void estatisticaNormalBase() throws IOException {
        FXMLLoader loader = setPanel("FXMLEstatisticasWindow");
        FXMLEstatisticasWindowController controller = (FXMLEstatisticasWindowController) loader.getController();
        controller.setStatisticsNormalBase();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void estatisticaNormalCorrida() throws IOException {
        FXMLLoader loader = setPanel("FXMLEstatisticasWindow");
        FXMLEstatisticasWindowController controller = (FXMLEstatisticasWindowController) loader.getController();
        controller.setStatisticsNormalCorrida();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void estatisticaRapidoBase() throws IOException {
        FXMLLoader loader = setPanel("FXMLEstatisticasWindow");
        FXMLEstatisticasWindowController controller = (FXMLEstatisticasWindowController) loader.getController();
        controller.setStatisticsRapidoBase();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void estatisticaRapidoCorrida() throws IOException {
        FXMLLoader loader = setPanel("FXMLEstatisticasWindow");
        FXMLEstatisticasWindowController controller = (FXMLEstatisticasWindowController) loader.getController();
        controller.setStatisticsRapidoCorrida();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void loadRankings() throws IOException {
        FXMLLoader loader = setPanel("FXMLRankingWindow");
        FXMLRankingWindowController controller = (FXMLRankingWindowController) loader.getController();
        controller.iniciarRankings();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void loadHistorico() throws IOException {
        FXMLLoader loader = setPanel("FXMLHistoricoWindow");
        FXMLHistoricoWindowController controller = (FXMLHistoricoWindowController) loader.getController();
        controller.iniciarCombo();
        controller.iniciarLista();
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void loadLoggerOptions() throws IOException {
        buttonIniciarJogo.setDisable(true);
        buttonIniciarJogo.setOpacity(0.0);
        zonaJogo.getChildren().add(loggerOptionsParent);
        this.itemReturn.setDisable(false);
    }

    @FXML
    private void loadCreditos()throws IOException {
        setPanel("FXMLCreditosWindow");
        this.itemReturn.setDisable(false);
    }
    
    public FXMLLoader createPane(String fxml) throws IOException {

        URL location = getClass().getResource("/pt/ips/pa/view/" + fxml + ".fxml");
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(location);
        fl.load();
        return fl;
    }

    private FXMLLoader setPanel(String fxml) throws IOException {
        buttonIniciarJogo.setDisable(true);
        buttonIniciarJogo.setOpacity(0.0);
        FXMLLoader loader = createPane(fxml);
        Parent root = loader.getRoot();
        zonaJogo.getChildren().add(root);
        return loader;
    }

    //Aqui estão os métodos que põe 
    public void showAction(CheckMenuItem menuItem, Label label) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (menuItem.isSelected()) {
                    label.setOpacity(1.0);
                } else {
                    label.setOpacity(0.0);
                }
            }
        });
    }

    @FXML
    public void voltarAtras() {
        if (zonaJogo.getChildren().size() > 1) {
            zonaJogo.getChildren().remove(zonaJogo.getChildren().size() - 1);
            if (zonaJogo.getChildren().get(zonaJogo.getChildren().size() - 1).equals(gameParent)) {
                this.itemReturn.setDisable(true);
            }
        }
        if (zonaJogo.getChildren().size() == 1) {
            buttonIniciarJogo.setDisable(false);
            buttonIniciarJogo.setOpacity(1.0);
            this.itemReturn.setDisable(true);
        }
    }

    public void setActionViews(Label nome, Label pontuacao, Label tempo) {
        setAction(viewNome, nome);
        setAction(viewPontuacao, pontuacao);
        setAction(viewTempo, tempo);
    }

    private void setAction(CheckMenuItem item, Label label) {
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (item.isSelected()) {
                    label.setOpacity(1.0);
                } else {
                    label.setOpacity(0.0);
                }
            }
        });
    }

    public void enableMenu() {
        this.itemUndo.setDisable(!itemUndo.isDisable());
        this.menuView.setDisable(!menuView.isDisable());
    }

    public StackPane getZonaJogo() {
        return zonaJogo;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public MenuItem getItemNovoJogo() {
        return itemNovoJogo;
    }

    public MenuItem getItemUndo() {
        return itemUndo;
    }

    public MenuItem getItemReturn() {
        return itemReturn;
    }

    public FXMLMusicWindowController getMusicController() {
        return musicController;
    }

    public Menu getMenuView() {
        return menuView;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public MenuItem getLoggerOptions() {
        return loggerOptions;
    }

    public Button getButtonIniciarJogo() {
        return buttonIniciarJogo;
    }
}
