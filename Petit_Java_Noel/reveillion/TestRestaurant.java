package reveillion;

import java.util.Random;

public class TestRestaurant {

	public static void main(String[] args) {
		int nb_groupes = 5;
		int nb_tables=15;
		
		Restaurant r = new Restaurant(nb_tables);
		Random gen = new Random();
		
		for (int i =0; i<nb_groupes; i++)
			new GroupeClients(r,1+gen.nextInt(10));

	}

}
