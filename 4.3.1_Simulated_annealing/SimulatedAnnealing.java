import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class SimulatedAnnealing {
    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        // Create and add our cities
        try (BufferedReader br = new BufferedReader(new FileReader("TSP_Matrix.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coords = line.split("[,]");
                City city = new City(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
                TourManager.addCity(city);
            }
        } catch (Exception e) {
            System.err.println("File not found or another issue");
        }

        // Set initial temp
        double temp = 10.00;

        // Cooling rate
        double coolingRate = 0.995;

        // Initialize intial solution
        Tour currentSolution = new Tour();
        currentSolution.generateTour();

        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        // Set as current best
        Tour best = new Tour(currentSolution.getTour());

        // Loop until system has cooled
        while (temp > 0.0005) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // Get a random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);

            // Get energy of solutions
            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }

            // Cool system
            temp *= coolingRate;
        }

        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);
    }
}
