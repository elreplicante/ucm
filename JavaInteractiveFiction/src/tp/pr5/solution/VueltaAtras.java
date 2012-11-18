package tp.pr5.solution;

import java.util.Vector;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.Command;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.items.Item;

public class VueltaAtras {

	private Game game;
	private Map map;
	private Player player;
	private Vector<Directions> lastDirection;
	private boolean pintado = false;
	private Vector<PickVector> pickVector;
	private Vector<UseVector> useVector;

	public VueltaAtras(Game game, Map map) {
		this.game = game;
		this.map = map;
		player = new Player();
		pickVector = new Vector<PickVector>();
		useVector = new Vector<UseVector>();

		lastDirection = new Vector<Directions>();
		Command[] sol = new Command[100];
		this.solution(sol, 0);
		if (!pintado) {
			System.out.println("Solucion no encontrada");
		}
	}

	public void solution(Command[] solution, int k) {

		// Preparamos las direcciones posibles a las que podemos ir
		Vector<Directions> directions = addPossibleDirections();

		// Preparamos los items del player para el use
		Vector<Item> itemsUsables = new Vector<Item>();

		// Preparar los items de la habitacion para el pick
		Vector<Item> itemsCogibles = new Vector<Item>();

		for (Directions direction : directions) {
			// Que el player coja todos los objetos de la habitacion
			if (k == 0)
				playerPicksAllRoomItems(itemsCogibles, k);
			else
				playerPicksAllRoomItems(itemsCogibles, k - 1);

			lastDirection.add(direction);
			GoCommand goCommand = new GoCommand(direction);
			goCommand.configureContext(game, map, player);
			solution[k] = goCommand;
			if (map.getDoor(map.getCurrentRoom(), direction) != null) {
				if (map.getDoor(map.getCurrentRoom(), direction).isOpen()) {
					// Para evitar morir usamos todos los objetos cuando quede
					// la mitad de la vida
					if (player.getHealth() == Constants.INITIAL_LIVE / 2) {
						playerUsesAllRoomItems(itemsUsables, k);
					}
					try {
						solution[k].execute();
						if (map.getCurrentRoom().isExit() && !pintado) {
							printSolution(solution);
						} else {
							solution(solution, k + 1);
							lastDirection.remove(direction);
							undoInserviblePick(k);
							solution[k].undo();
						}
					} catch (CommandExecutionException e) {
					}
				} else {
					// Si la puerta esta cerrada que el player use todos los
					// objetos que tiene, pero si es malo
					// (le resta vida) que haga un undo()
					playerUsesAllRoomItems(itemsUsables, k);
					solution(solution, k);
				}
			}
		}

	}

	private Vector<Directions> addPossibleDirections() {
		Vector<Directions> directions = new Vector<Directions>();
		if (lastDirection.size() != 0) {
			if (!lastDirection.lastElement().opposite()
					.equals(Directions.NORTH))
				directions.add(Directions.NORTH);
			if (!lastDirection.lastElement().opposite()
					.equals(Directions.SOUTH))
				directions.add(Directions.SOUTH);
			if (!lastDirection.lastElement().opposite().equals(Directions.EAST))
				directions.add(Directions.EAST);
			if (!lastDirection.lastElement().opposite().equals(Directions.WEST))
				directions.add(Directions.WEST);
		} else if (lastDirection.size() == 0) {
			directions.add(Directions.NORTH);
			directions.add(Directions.SOUTH);
			directions.add(Directions.EAST);
			directions.add(Directions.WEST);
		}
		return directions;
	}

	private void playerPicksAllRoomItems(Vector<Item> itemsCogibles, int k) {
		if (map.getCurrentRoom() != null) {
			if (map.getCurrentRoom().getRoomItems().size() > 0) {
				itemsCogibles.addAll(map.getCurrentRoom().getRoomItems());
				for (Item item : itemsCogibles) {
					PickCommand pickCommand = new PickCommand(item.getId());
					pickCommand.configureContext(game, map, player);
					try {
						pickCommand.execute();
						pickVector.add(new PickVector(pickCommand, k));
					} catch (CommandExecutionException e) {
					}
				}
			}
		}
	}

	private void playerUsesAllRoomItems(Vector<Item> itemsUsables, int k) {
		if (player.getInventory().size() > 0) {
			itemsUsables.addAll(player.getInventory());
			for (Item item : itemsUsables) {
				int playerHealthBefore = player.getHealth();
				UseCommand useCommand = new UseCommand(item.getId());
				useCommand.configureContext(game, map, player);
				try {
					useCommand.execute();
					useVector.add(new UseVector(useCommand, k));
				} catch (CommandExecutionException e) {
				}
				if (playerHealthBefore > player.getHealth()) {
					useCommand.undo();
					useVector.removeElementAt(useVector.size() - 1);
				}
			}
		}
	}

	private void undoInserviblePick(int k) {
		for (int i = 0; i < pickVector.size(); i++) {
			if (pickVector.elementAt(i).go == k) {
				for (int j = 0; j < pickVector.elementAt(i).picks.size(); j++) {
					player.removeItem(pickVector.elementAt(i).picks
							.elementAt(j).getId());
					pickVector.elementAt(i).picks.remove(j);
				}
			}
		}
	}

	private void printSolution(Command[] solution) {

		// Calcular el numero de elementos que tiene el array solution
		int j = 0;
		while (j < solution.length && solution[j] != null) {
			j++;
		}

		System.out.print("La solution es: ");
		for (int i = 0; i < j; i++) {
			printPicks(i);
			printUses(i);
			System.out.print(solution[i].getCommandName() + " "
					+ solution[i].getId() + "   ");
		}
		pintado = true;
	}

	private void printPicks(int i) {
		for (int k = 0; k < pickVector.size(); k++) {
			if (pickVector.elementAt(k).go == i) {
				for (int l = 0; l < pickVector.elementAt(k).picks.size(); l++) {
					System.out.print(pickVector.elementAt(k).picks.elementAt(l)
							.getCommandName()
							+ " "
							+ pickVector.elementAt(k).picks.elementAt(l)
									.getId() + "   ");
				}
			}
		}
	}

	private void printUses(int i) {
		for (int k = 0; k < useVector.size(); k++) {
			if (useVector.elementAt(k).go == i) {
				for (int l = 0; l < useVector.elementAt(k).uses.size(); l++) {
					System.out.print(useVector.elementAt(k).uses.elementAt(l)
							.getCommandName()
							+ " "
							+ useVector.elementAt(k).uses.elementAt(l).getId()
							+ "   ");
				}
			}
		}
	}

	private class PickVector {
		private Vector<PickCommand> picks = new Vector<PickCommand>();
		private int go;

		PickVector(PickCommand pickCommand, int go) {
			picks.add(pickCommand);
			this.go = go;
		}
	}

	private class UseVector {
		private Vector<UseCommand> uses = new Vector<UseCommand>();
		private int go;

		UseVector(UseCommand useCommand, int go) {
			uses.add(useCommand);
			this.go = go;
		}
	}
}
