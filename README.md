# TSP-SA
TSP solver using Simulated Annealing

I used Nearest Neighbour + 2 opt to generate an initial solution.
Then as metaheuristic I used Simulated Annealing + 2 opt.
My initial temperature is 10000, cooling rate is 0.97.

I also randomly decide whether to use Nearest Neighbour or a random initial solution.

Improvements: I use a fast version of Math.sqrt().
