package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.User;
import model.Photo;
import model.Album;
import model.Tag;

public class MenuController extends SceneController{

	@FXML
	private Label dateLabel;
	@FXML
	private Label tag1;
	@FXML
	private Label tag2;
	@FXML
	private Label tag3;
	@FXML
	private Label captionLabel;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPaneCenter;
	@FXML
	private Button addPhotoButton;
	@FXML
	private Button copyPhotoButton;
	@FXML
	private Button deletePhotoButton;
	@FXML
	private Button addTagButton;
	@FXML
	private Button displayButton;
	@FXML
	private Button homeButton;
	@FXML
	private Button mainLogoutButton;
	@FXML
	private Button mainQuitButton;
	@FXML
	private Button movePhotoButton;
	@FXML
	private Button renameAlbumButton;
	@FXML
	private Button renameCaptionButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button slideshowButton;
	@FXML
	private CheckBox createAlbumCheck;
	@FXML
	private CheckBox dateCheck;
	@FXML
	private CheckBox tagValueCheck;
	@FXML
	private DatePicker dateFrom;
	@FXML
	private DatePicker dateTo;
	@FXML
	private ImageView imageView;
	@FXML
	private TextField albumField;
	@FXML
	private TextField newTagField;
	@FXML
	private TextField newValueField;
	@FXML
	private TextField renameField;
	@FXML
	private TextField tagField1;
	@FXML
	private TextField tagField2;
	@FXML
	private TextField tagField3;
	@FXML
	private TextField valueField1;
	@FXML
	private TextField valueField2;
	@FXML
	private TextField valueField3;
	@FXML
	private HBox hboxRightInfoBottom;
	@FXML
	private ScrollPane scrollPane1;
	@FXML 
	private ScrollPane scrollPane2;
	
	private ArrayList<AnchorPane> photoThumbs = new ArrayList<AnchorPane>();
	private int selectedThumb = 0;
	private User user;
	private ArrayList<Album> albums;
	private Album currentAlbum;
	
	@Override
	public void start(Stage s) {
		super.start(s);
//		this.user = u;
//		albums = user.getAlbums();
		showAlbums(albums);
	}
	
	private TableView<Album> showAlbums(ArrayList<Album> a){
		ObservableList<Album> list = FXCollections.observableArrayList(a);
		TableView<Album> albumList = new TableView<Album>();
		albumList.setItems(list);
		
		TableColumn<Album, String> nameCol = new TableColumn<Album, String>("Album Name");
		nameCol.setCellValueFactory(new Callback<CellDataFeatures<Album, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Album, String> p) {
				return new ReadOnlyStringWrapper(p.getValue().getAlbumName());
			}
		});

		TableColumn<Album, String> oldestDateCol = new TableColumn<Album, String>("Oldest Photo");
		oldestDateCol.setCellValueFactory(new Callback<CellDataFeatures<Album, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Album, String> p) {
				return new ReadOnlyStringWrapper(p.getValue().getOldest());
			}
		});

		TableColumn<Album, String> newestDateCol = new TableColumn<Album, String>("Newest Photo");
		newestDateCol.setCellValueFactory(new Callback<CellDataFeatures<Album, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Album, String> p) {
				return new ReadOnlyStringWrapper(p.getValue().getLastModified());
			}
		});

		TableColumn<Album, String> photoCountCol = new TableColumn<Album, String>("Photo Count");
		photoCountCol.setCellValueFactory(new Callback<CellDataFeatures<Album, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Album, String> p) {
				return new ReadOnlyStringWrapper(Integer.toString(p.getValue().getNumPhotos()));
			}
		});

		nameCol.setPrefWidth(100);
		oldestDateCol.setPrefWidth(146);
		newestDateCol.setPrefWidth(147);
		photoCountCol.setPrefWidth(100);
		albumList.getColumns().add(nameCol);
		albumList.getColumns().add(oldestDateCol);
		albumList.getColumns().add(newestDateCol);
		albumList.getColumns().add(photoCountCol);

		albumList.setRowFactory(t -> {
			TableRow<Album> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					currentAlbum = row.getItem();
				}
			});
			return row;
		});

		albumList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection.getNumPhotos() != 0) {
				imageView.setImage(new Image(newSelection.getPhotos().get(0).getURL()));
			} else {
				imageView.setImage(null);
			}
			captionLabel.setText(newSelection.getAlbumName());
		});

		if (!albums.isEmpty()) {
			albumList.getSelectionModel().select(0);
		}

		return albumList;
	}
	
	private TilePane createPhotoPane(ArrayList<Photo> photos) {
		TilePane tp = new TilePane();
		tp.setPrefWidth(480);

		photoThumbs.clear();
		for (Photo p : photos) {
			AnchorPane ap = createNewPhotoSquare(p.getCaption(), p.getURL());
			tp.getChildren().add(ap);
			photoThumbs.add(ap);
		}

		return tp;
	}


//	private void updatePhotoPreview() {
//		Photo selectedPhoto = currentAlbum.getPhotos().get(selectedThumb);
//		imageView.setImage(new Image(selectedPhoto.getURL()));
//		captionLabel.setText(selectedPhoto.getCaption());
////		labelRightInfo2.setText(selectedAlbum.getName());
////		labelRightInfo4.setText(selectedPhoto.getDateString());
//		ArrayList<String> tags = selectedPhoto.getTagsAsText();
//		@SuppressWarnings("unchecked")
//		ListView<String> container = (ListView<String>) hboxRightInfoBottom.getChildren().get(1);
//		container.setItems(FXCollections.observableArrayList(tags));
//	}

	private AnchorPane createNewPhotoSquare(String caption, String URL) {

		AnchorPane ap = new AnchorPane();
		ap.setPrefHeight(135);
		ap.setPrefWidth(150);

		VBox vb = new VBox();
		vb.setPrefHeight(120);
		vb.setPrefWidth(150);
		vb.setAlignment(Pos.CENTER);
		ap.getChildren().add(vb);
		AnchorPane.setTopAnchor(vb, 0.0);

		Label label = new Label(caption);
		label.setPrefHeight(15);
		label.setPrefWidth(150);
		label.setAlignment(Pos.CENTER);
		ap.getChildren().add(label);
		AnchorPane.setBottomAnchor(label, 0.0);

		System.out.println("\n\n" + URL);
		ImageView iv = new ImageView(URL);
		iv.setFitHeight(120);
		iv.setFitWidth(150);
		iv.setPickOnBounds(true);
		iv.setPreserveRatio(true);
		vb.getChildren().add(iv);
		
		return ap;
	}
	
	private void selectPhoto(AnchorPane ap){
		int i=0;
		for(; i<photoThumbs.size(); i++){
			if(ap.equals(photoThumbs.get(i))){
				break;
			}
		}
		selectPhoto(i);
	}
	
	private void selectPhoto(int photoNum){
		// Deselect Previous
				photoThumbs.get(selectedThumb).getChildren().get(0).setStyle(null);
				photoThumbs.get(selectedThumb).getChildren().get(1).setStyle(null);
				((Label) photoThumbs.get(selectedThumb).getChildren().get(1)).setTextFill(Color.BLACK);

				// Select New
				photoThumbs.get(photoNum).getChildren().get(0).setStyle("-fx-background-color: #E0E0E0");
				photoThumbs.get(photoNum).getChildren().get(1).setStyle("-fx-background-color: #0950D0");
				((Label) photoThumbs.get(photoNum).getChildren().get(1)).setTextFill(Color.WHITE);

				selectedThumb = photoNum;

			//	updatePhotoPreview();

				Photo selectedPhoto = currentAlbum.getPhotos().get(selectedThumb);
//				if (selectedPhoto != null) {
//					labelCenterStatus.setText(
//							username + " --> " + currentAlbum.getName() + " --> " + selectedPhoto.getCaption());
//				}
//			}
	}
	
//	public void mainLogoutPressed(ActionEvent e){
//		loadStage("/view/LoginScreen.fxml", null);
//	}
	
	public void mainQuitPressed(ActionEvent e){
		Platform.exit();
	}
	
	public void handleNewAlbumFromResults(ActionEvent e) {

//		user.getLib().addAlbum(currentAlbum.getAlbumName());
//		albums = user.getLib().getAlbums();
//		showAlbums(albums);

		scrollPane1.setContent(showAlbums(albums));
		@SuppressWarnings("unchecked")
		TableView<Album> tv = (TableView<Album>) scrollPane1.getContent();
		tv.getSelectionModel().select(currentAlbum);

	}
	
	
}
