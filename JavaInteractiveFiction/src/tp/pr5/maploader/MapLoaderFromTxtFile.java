package tp.pr5.maploader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Map;
import tp.pr5.Room;
import tp.pr5.items.Food;
import tp.pr5.items.Key;
import tp.pr5.items.Valuable;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

public class MapLoaderFromTxtFile {

	private String fileLine;
	private BufferedReader bufferedReader;

	private void loadRooms(ArrayList<Room> rooms, int num)
			throws IOException {

		int contRoom = 0;
		String roomDescription = ("");
		boolean isExit = false;

		while (!(fileLine = bufferedReader.readLine())
				.equalsIgnoreCase("EndRooms")) {

			String[] parsedLine = fileLine.split(" ");
			num = Integer.parseInt(parsedLine[1]);
			String roomName = parsedLine[2];
			roomDescription = parsedLine[3].replaceAll("_", " ");

			if (parsedLine[4].equals("noexit")) {
				isExit = false;
			} else if (parsedLine[4].equals("exit")) {
				isExit = true;
			} else {
				throw new WrongMapFormatException();
			}

			if (num == contRoom) {
				rooms.add(new Room(isExit, roomDescription, roomName));
				contRoom++;
			} else {

				throw new WrongMapFormatException("Bad data order in file");
			}
		}
	}

	private void loadDoors(ArrayList<Room> rooms, ArrayList<Door> doors,
			Map map, int num) throws IOException {

		int contDoor = 0;
		boolean bidirectionalDoor = false;
		boolean isOpen = false;

		while (!(fileLine = bufferedReader.readLine())
				.equalsIgnoreCase("EndDoors")) {

			String[] parsedLine = fileLine.split(" ");
			num = Integer.parseInt(parsedLine[1]);

			if (parsedLine[2].equals("bidirectional")) {
				bidirectionalDoor = true;
			} else if (parsedLine[2].equals("directional")) {
				bidirectionalDoor = false;
			} else {
				throw new WrongMapFormatException();
			}
			if (parsedLine[8].equals("open")) {
				isOpen = true;
			} else if (parsedLine[8].equals("closed")) {
				isOpen = false;
			} else {
				throw new WrongMapFormatException();
			}

			Room sourceRoom = rooms.get(Integer.parseInt(parsedLine[4]));
			Room targetRoom = rooms.get(Integer.parseInt(parsedLine[7]));

			Directions direction = Directions.valueOf(parsedLine[5]
					.toUpperCase());
			if (num == contDoor) {
				if (bidirectionalDoor) {
					doors.add(map.addBidirectionalDoor(sourceRoom, direction,
							targetRoom));
				} else {
					doors.add(map.addDoor(sourceRoom, direction, targetRoom));
				}
				if (isOpen) {
					doors.get(num).open();
				} else {
					doors.get(num).close();
				}
				contDoor++;
			} else {
				throw new WrongMapFormatException();
			}
		}
	}

	private void loadItems(ArrayList<Room> rooms, ArrayList<Door> doors, int num)
			throws  IOException{

		int itemCounter = 0;
		String itemDescription = ("");
		String name;
		int health, times, score;

		while (!(fileLine = bufferedReader.readLine())
				.equalsIgnoreCase("EndItems")) {

			String[] parsedLine = fileLine.split(" ");
			num = Integer.parseInt(parsedLine[1]);
			itemDescription = parsedLine[3].replaceAll("_", " ");
			name = parsedLine[2];

			if (num == itemCounter) {
				if (parsedLine[0].equalsIgnoreCase("food")) {
					Room itemRoom = rooms.get(Integer.parseInt(parsedLine[7]));
					health = Integer.parseInt(parsedLine[4]);
					times = Integer.parseInt(parsedLine[5]);
					itemRoom.addItem(new Food(name, itemDescription, health,
							times));

				} else if (parsedLine[0].equalsIgnoreCase("key")) {
					Room numRoom = rooms.get(Integer.parseInt(parsedLine[7]));
					Door door = doors.get(Integer.parseInt(parsedLine[5]));
					numRoom.addItem(new Key(name, itemDescription, door));

				} else if (parsedLine[0].equalsIgnoreCase("valuable")) {
					Room numRoom = rooms.get(Integer.parseInt(parsedLine[6]));
					score = Integer.parseInt(parsedLine[4]);
					numRoom.addItem(new Valuable(name, itemDescription, score));

				} else {
					throw new WrongMapFormatException();
				}
				itemCounter++;
			} else {
				throw new WrongMapFormatException();
			}
		}
	}

	/**
	 * Builds a Map from an input File.
	 * 
	 * @param file
	 *            - input File
	 * @return loadMap Map instance Builded from the file
	 * @throws java.io.IOException
	 * @throws WrongMapFormatException
	 */
	public Map loadMap(InputStream file) throws IOException
			 {

		Map map = new Map();
		ArrayList<Room> rooms = new ArrayList<Room>();
		ArrayList<Door> doors = new ArrayList<Door>();

		int num = 0;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(file));
			fileLine = bufferedReader.readLine();

			if (fileLine.equalsIgnoreCase("BeginMap")) {
				fileLine = bufferedReader.readLine();
				if (fileLine.equalsIgnoreCase("BeginRooms")) {

					loadRooms(rooms, num);

				} else {
					throw new WrongMapFormatException(
							"BeginRooms not found in file");
				}

				map = new Map(rooms.get(0));
				fileLine = bufferedReader.readLine();

				if (fileLine.equalsIgnoreCase("BeginDoors")) {

					loadDoors(rooms, doors, map, num);

				} else {
					throw new WrongMapFormatException();
				}

				fileLine = bufferedReader.readLine();

				if (fileLine.equalsIgnoreCase("BeginItems")) {

					loadItems(rooms, doors, num);

				} else {
					throw new WrongMapFormatException(
							"BeginItems not found in the file");
				}

			} else {
				throw new IOException("BeginMap not found in the file");
			}

		} catch (Exception e) {
			throw new WrongMapFormatException();

		} finally {
			bufferedReader.close();
		}

		return map;
	}

}
