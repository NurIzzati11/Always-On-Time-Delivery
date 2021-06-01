


public class Test {

    public static void main(String[] args) {
       
        String s = "instances\\n5-c10.txt";
        InputData data = new InputData(s);
        int[][] loc = data.getCoordinate(); // coordinate for each customer include depot
        int[] demand = data.getDemand();
        //Customer[] cus = new Customer[data.getN()];
         MyCustomer<Integer, Integer> customer = new MyCustomer<>(); // class graph
        for (int i = 0; i < data.getN(); i++) {
            //cus[i] = new Customer(i, s);
            customer.addCustomer(i, loc[i][0], loc[i][1], demand[i]);
        }
        
        System.out.println("Number of Customer(s): " + (customer.getSize()-1));
        System.out.println("Customer and their demand");
        for (int i = 0; i < customer.getSize(); i++) {
            if (i == 0)
                System.out.println("Depot" + customer.getCoordinate(i) + ": " + customer.getDemand(i));
            else
                System.out.println("Customer " + i + customer.getCoordinate(i) + ": "  + customer.getDemand(i));
        }

        Vehicle car = new Vehicle(data.getC());
        System.out.println("Capacity of each vehicles: " + car.getCapacity());
        System.out.println();
        double[][] cost = new double[customer.getSize()][customer.getSize()];
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                cost[i][j] = customer.calCost(i, j);
                customer.addEdge(i, j, cost[i][j]);
            }
        }
        //customer.printEdges(); // print edges for each location
       

        for (int i = 0; i < cost.length; i++) {
            System.out.printf("%d |\t", i);
            for (int j = 0; j < cost[i].length; j++) {
                System.out.printf("%.2f \t", cost[i][j]);
            }
            System.out.printf("|\n");
        }
        GreedySearch greedy = new GreedySearch();
        greedy.searchRoute(customer, car);
        System.out.println("\n" + greedy.toString());
        BestFirstSearch best = new BestFirstSearch();
        best.searchRoute(data, customer, car);
        System.out.println(best.toString());
       
    }
}
