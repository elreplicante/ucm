package tp.pr5.testprofesor.maploader;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.junit.Test;

//import tp.pr5.maploader.MapLoaderFromTxtFile_split;
import tp.pr5.maploader.MapLoaderFromTxtFile;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

public class MapLoaderTest {
	
	String file;
	
	private boolean loadString() {
		String endl = System.getProperty("line.separator");
		file = "BeginMap" + endl +
				"BeginRooms" + endl +
				"room 0 Entrada Estamos_en_la_entrada._Comienza_la_aventura! noexit" + endl +
				"room 1 Pasillo Esto_es_un_pasillo. noexit" + endl +
				"room 2 Salon Hemos_llegado_al_salon._Al_Norte_parece_que_hay_una_puerta. noexit" + endl + 
				"room 3 Salida Llegamos_a_la_salida exit" + endl +
				"EndRooms" + endl +
				"BeginDoors" + endl +
				"door 0 bidirectional room 0 south room 1 open" + endl +
				"door 1 directional room 1 east room 2 open" + endl +
				"door 2 directional room 2 north room 3 closed" + endl +
				"EndDoors" + endl +
				"BeginItems" + endl +
				"food 0 Agua Botella_de_agua,_entran_tres_tragos 10 3 room 0" + endl +
				"food 1 Carne Parece_carne,_pero_no_huele_igual... -50 1 room 0" + endl +
				"key 2 Llave Una_llave_brillante door 2 room 1" + endl +
				"valuable 3 moneda Moneda_brillante,_parece_de_oro. 30 room 2" + endl +
				"EndItems" + endl +
				"EndMap"; 
		return true;

	}

	@Test
	public void testLoadMap() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();  		 
		/**/
		if (loadString()){
			for (int i = 1; i<file.length(); i++) {
				try {
					InputStream is = new ByteArrayInputStream(file.substring(0, i).getBytes());
					ml.loadMap(is);
				}
				catch (WrongMapFormatException e) {
					// Continue
				}
				catch (Exception e) {
					fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
				}
			}
		}
	}
	
	@Test
	public void testRoomFirstId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();  		 
		/**/
		if (loadString()){
			file= file.replace("room 0 Entrada Estamos_en_la_entrada._Comienza_la_aventura! noexit",
								// Change first room id
							   "room 11 Entrada Estamos_en_la_entrada._Comienza_la_aventura! noexit");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: First room is not room 0");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testRoomConsecutiveId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();  		 
		/**/
		if (loadString()){
			file = file.replace("room 2 Salon Hemos_llegado_al_salon._Al_Norte_parece_que_hay_una_puerta. noexit",
								// Change with a non-consecutive room id
								"room 11 Salon Hemos_llegado_al_salon._Al_Norte_parece_que_hay_una_puerta. noexit");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: A room with id=11 after a room with id=1 is not correct");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testDoorFirstId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();		 
		/**/
		if (loadString()){
			file= file.replace("door 0 bidirectional room 0 south room 1 open",
							   // Change first door id
							   "door 11 bidirectional room 0 south room 1 open");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: First door is not door 0");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testDoorConsecutiveId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();		 
		/**/
		if (loadString()){
			file = file.replace("door 2 directional room 2 north room 3 closed",
								// Change with a non-consecutive door id
								"door 11 directional room 2 north room 3 closed");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: A door with id=11 after a door with id=1 is not correct");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testItemFirstId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();  		 
		/**/
		if (loadString()){
			file= file.replace("food 0 Agua Botella_de_agua,_entran_tres_tragos 10 3 room 0",
							   // Change first item id
							   "food 11 Agua Botella_de_agua,_entran_tres_tragos 10 3 room 0");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: First item id is not 0");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testItemConsecutiveId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();		 
		/**/
		if (loadString()){
			file = file.replace("key 2 Llave Una_llave_brillante door 2 room 1",
								// Change with a non-consecutive item id
								"key 11 Llave Una_llave_brillante door 2 room 1");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: An item with id=11 after an item with id=1 is not correct");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testDoorBetweenWrongRoomsId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();	 
		/**/
		if (loadString()){
			file = file.replace("door 0 bidirectional room 0 south room 1 open",
								// Change target room with a wrong id
								"door 0 bidirectional room 0 south room 11 open");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: loadMad should fail when trying to create a door between two rooms with wrong id");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testItemWrongRoomId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split(); 		 
		/**/
		if (loadString()){
			file = file.replace("food 0 Agua Botella_de_agua,_entran_tres_tragos 10 3 room 0",
								// Change the room that will contain the item with a wrong id
								"food 0 Agua Botella_de_agua,_entran_tres_tragos 10 3 room 11");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: loadMad should fail when trying to create an item in a room with wrong id");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}
	
	@Test
	public void testKeyWrongDoorId() {
		//*
		MapLoaderFromTxtFile ml = new MapLoaderFromTxtFile();
		/*/
		MapLoaderFromTxtFile_split ml = new MapLoaderFromTxtFile_split();  		 
		/**/
		if (loadString()){
			file = file.replace("key 2 Llave Una_llave_brillante door 2 room 1",
								// Change the door that the key will open with a wrong id
								"key 2 Llave Una_llave_brillante door 11 room 1");
			try {
				InputStream is = new ByteArrayInputStream(file.getBytes());
				ml.loadMap(is);
				fail("ERROR: loadMad should fail when trying to create a key to open a door with wrong id");
			}
			catch (WrongMapFormatException e) {
				// Continue
			}
			catch (Exception e) {
				fail("ERROR: loadMap throws an exception different from WrongMapFormatException: "+e);					
			}
		}
	}

}
