import random
import copy


class Graph :
    def __init__(self, m=set(), n=set()) :
        self.inbound = {}
        self.outbound = {}
        self.costs = {}
        for vertex in m :
            self.add_vertex(vertex)
        for edge in n :
            self.add_edge(edge[0], edge[1], set_cost_to_edge)

    def add_vertex(self, v) :
        if v not in self.inbound.keys() :
            self.inbound[v] = list()
            self.outbound[v] = list()
        else :
            raise ValueError("This vertex already exists.")

    def add_edge(self, start, end, value):
        if start not in self.inbound.keys():
            self.add_vertex(start)
        self.outbound[start].append(end)
        if end not in self.inbound.keys():
            self.add_vertex(end)
        self.inbound[end].append(start)
        if (start, end) not in self.costs.keys():
            self.costs[(start, end)] = value
        else:
            raise ValueError("Edge already exists.")

    def set_cost(self, start, end, value):
        if self.is_edge(start, end) :
            self.costs[(start, end)] = value
        else:
            raise ValueError("This edge doesn't exist in the graph.")

    def is_edge(self, start, end) :
        return (start, end) in self.costs.keys()

    def get_cost(self, start, end) :
        return self.costs[(start, end)]

    def number_of_vertices(self, file_name) :
        with open(file_name, "r") as f :
            n, m = map(int, f.readline().split())
        for i in range(n) :
            if i not in self.inbound.keys() :
                self.inbound[i] = list()
                self.outbound[i] = list()
        return len(self.inbound.keys())

    def iterate_set_of_vertices(self) :
        return [v for v in self.inbound.keys()]

    def in_n_out_degree(self, v) :
        if v not in self.inbound.keys() :
            raise ValueError("This vertex is not in the graph")
        else :
            in_dg = len(self.inbound[v])
            out_dg = len(self.outbound[v])

        return in_dg, out_dg

    def iterate_in(self, v) :
        if v not in self.inbound.keys() :
            raise ValueError("vertex not in graph")
        return [x for x in self.inbound[v]]

    def iterate_out(self, v) :
        if v not in self.outbound.keys() :
            raise ValueError("This vertex is not in the graph")
        return [x for x in self.outbound[v]]

    def remove_vertex(self, v) :
        if v not in self.inbound.keys() :
            raise ValueError("This vertex is not in the graph")
        for start in self.inbound[v] :
            self.costs.pop((start, v))
        for end in self.outbound[v] :
            self.costs.pop((v, end))
        self.inbound.pop(v)
        self.outbound.pop(v)

    def remove_edge(self, start, end) :
        if (start, end) not in self.outbound :
            raise ValueError("This edge doesn't exist")
        else :
            self.inbound[end].pop(start)
            self.outbound[start].pop(end)
            self.costs.pop((start, end))

    def copy_graph(self) :
        new_graph = Graph()
        new_graph.inbound = copy.deepcopy(self.inbound)
        new_graph.outbound = copy.deepcopy(self.outbound)
        new_graph.costs = copy.deepcopy(self.costs)
        return new_graph

    def reset(self) :
        self.inbound = {}
        self.outbound = {}
        self.costs = {}


def set_cost_to_edge() :
    return random.randint(-100, 100)


def random_graph(num_v, num_e) :
    if num_e > num_v * (num_v - 1) :
        raise ValueError("Too many edges for the number of vertices")

    graph = Graph()

    for i in range(num_v) :
        graph.add_vertex(i)

    edges_added = set()
    while len(edges_added) < num_e :
        start = random.randint(0, num_v - 1)
        end = random.randint(0, num_v - 1)
        if start != end and (start, end) not in edges_added :
            cost = random.randint(-100, 100)
            graph.add_edge(start, end, cost)
            edges_added.add((start, end))

    return graph


def read_from_file(file_name):
    graph = Graph()
    file = open(file_name, "rt")  # rt -> read, text-mode
    file.readline()
    for line in file.readlines() :
        start, end, cost = line.split(sep=' ')
        cost = cost.strip('\n')
        graph.add_edge(int(start), int(end), int(cost))
    file.close()
    return graph


def write_into_file(graph, file_name) :
    file = open(file_name, "wt")  # wt -> write, text-mode
    num_v = len(graph.inbound.keys())
    num_e = len(graph.costs.keys())
    file.write(str(num_v) + ' ' + str(num_e) + "\n")
    for edge in graph.costs.keys() :
        file.write(str(edge[0]) + ' ' + str(edge[1]) + ' ' + str(graph.costs[edge]) + "\n")
    file.close()


def valid(file_name) :
    file_name_tokens = file_name.split(sep=".")
    if len(file_name_tokens) == 2 :
        if file_name_tokens[1] == 'txt' :
            return True
    return False


def ford_bellman_algorithm(graph, start_vertex, end_vertex):
    """
    This function creates and uses 2 dictionaries (dist and pred), one for retaining the path
    chosen and one for distances to each vertex from the starting vertex
    :param graph: graph object
    :param start_vertex: vertex from graph
    :param end_vertex: vertex from graph
    :return:the lowest cost walk starting from start_vertex to end_vertex, or a message
    if a negative cycle is detected
    """
    distances = {vertex: float("inf") for vertex in graph.inbound.keys()}
    distances[start_vertex] = 0
    predecessors = {}

    for i in range(len(graph.inbound.keys()) - 1):
        for from_vertex in graph.inbound.keys():
            for to_vertex in graph.outbound[from_vertex]:
                if distances[from_vertex] + graph.costs[(from_vertex, to_vertex)] < distances[to_vertex]:
                    distances[to_vertex] = distances[from_vertex] + graph.costs[(from_vertex, to_vertex)]
                    predecessors[to_vertex] = from_vertex

    for from_vertex in graph.inbound.keys():
        for to_vertex in graph.outbound[from_vertex]:
            if distances[from_vertex] + graph.costs[(from_vertex, to_vertex)] < distances[to_vertex]:
                print("Negative cycle detected!")
                return

    path = []
    current_vertex = end_vertex
    while current_vertex != start_vertex:
        path.append(current_vertex)
        current_vertex = predecessors[current_vertex]
    path.append(start_vertex)
    path.reverse()
    cost = distances[end_vertex]
    return path, cost


def print_options():
    print("0. Exit.")
    print("1. Get the number of vertices.")
    print("2. Iterate the set of vertices.")
    print("3. Verify if there is an edge between 2 vertices.")
    print("4. In-degree and out-degree of a vertex.")
    print("5. Iterate outbound edges of a vertex.")
    print("6. Iterate inbound edges of a vertex.")
    print("7.Retrieve the cost of an edge")
    print("8. Modify the cost of an edge.")
    print("9. Modify the graph. (add or remove vertices/edges).")
    print("10. Create a copy of the graph")
    print("11. Write in a file.")
    print("12.Find the lowest cost walk between 2 vertices")


def menu():
    graph_ = Graph()

    while True:
        print("How do you want the graph to be generated?")
        print("1. Random vertices/edges")
        print("2. 1k vertices file.")
        print("3. 10k vertices file.")
        print("4. 100k vertices file.")
        print("5. 1m vertices file.")
        print("0.Exit")
        command = int(input(">>>"))

        if command == 1:
            graph_.reset()
            num_v = int(input("Number of vertices: "))
            num_e = int(input("Number of edges: "))
            graph_ = random_graph(num_v, num_e)
            file_name = input("Please introduce the file name: ")
            if valid(file_name):
                write_into_file(graph_, file_name)
            else :
                print("please introduce a valid file name")

        elif command == 2:
            graph_.reset()
            file_name = "graph1k.txt"
            graph_ = read_from_file(file_name)

        elif command == 3 :
            graph_.reset()
            file_name = "graph10k.txt"
            graph_ = read_from_file(file_name)

        elif command == 4 :
            graph_.reset()
            file_name = "graph100k.txt"
            graph_ = read_from_file(file_name)

        elif command == 5:
            graph_.reset()
            file_name = "graph1m"
            graph_ = read_from_file(file_name)

        elif command == 6:
            graph_.reset()
            file_name = "random_graph1.txt"
            graph_ = read_from_file(file_name)

        elif command == 7:
            graph_.reset()
            file_name = "random_graph2.txt"
            graph_ = read_from_file(file_name)

        elif command == 0:
            break

        else :
            raise ValueError("Please introduce a valid options!")

    while True :
        print_options()
        command = int(input(">>>"))

        if command == 0:
            break

        elif command == 1:
            file_name = input("Please introduce the file name: ")
            if valid(file_name):
                print("Number of vertices:", graph_.number_of_vertices(file_name))

        elif command == 2 :
            print(graph_.iterate_set_of_vertices())

        elif command == 3 :
            start = int(input("Source vertex:"))
            end = int(input("Target vertex:"))
            edge_exist = graph_.is_edge(start, end)
            if edge_exist :
                print("The edge between ", start, " and ", end, " exists.")
            else :
                print("The edge between ", start, " and ", end, " does not exist.")

        elif command == 4 :
            v = int(input("The vertex: "))
            if v in graph_.inbound.keys() :
                print(f"in_degree: {graph_.in_n_out_degree(v)[0]} out_degree: {graph_.in_n_out_degree(v)[1]}")
            else :
                print("This vertex is not in the graph")

        elif command == 5 :
            v = int(input("Enter the vertex: "))
            if v in graph_.inbound.keys() :
                print(graph_.iterate_out(v))
            else :
                print("This vertex is not in the graph")

        elif command == 6 :
            v = int(input("Enter the vertex: "))
            if v in graph_.inbound.keys() :
                print(graph_.iterate_in(v))
            else :
                print("This vertex is not in the graph")

        elif command == 7 :
            start = int(input("Source vertex: "))
            end = int(input("Target vertex: "))
            if not graph_.is_edge(start, end) :
                print("There is no edge between ", start, " and ", end)
            else :
                print("The cost of the edge is:", graph_.get_cost(start, end))

        elif command == 8 :
            start = int(input("Source vertex: "))
            end = int(input("Target vertex: "))
            if not graph_.is_edge(start, end) :
                print("There is no edge between ", start, " and ", end)
            else :
                cost = int(input("Enter the new price: "))
                graph_.set_cost(start, end, cost)

        elif command == 9 :
            print("What do you want to modify?")
            print("1. Edges.")
            print("2. Vertices.")
            command = int(input(">>>"))

            if command == 1 :
                print("1. Add an edge.")
                print("2. Remove an edge.")
                command = int(input(">>>"))

                if command == 1 :
                    start = int(input("Source vertex: "))
                    end = int(input("Target vertex: "))
                    if not graph_.is_edge(start, end) :
                        cost = int(input("Cost: "))
                        print(graph_.add_edge(start, end, cost))
                    else :
                        print("This edge already exists in the graph.")

                elif command == 2 :
                    start = int(input("Source vertex: "))
                    end = int(input("Target vertex: "))
                    if not graph_.is_edge(start, end) :
                        print("This edge doesn't exist in the graph.")
                    else :
                        print(graph_.remove_edge(start, end))

            elif command == 2 :
                print("1. Add a vertex.")
                print("2. Remove a vertex.")
                command = int(input(">>>"))

                if command == 1 :
                    v = int(input("Vertex: "))
                    if v not in graph_.inbound.keys() :
                        graph_.add_vertex(v)
                    else :
                        print("This vertex is already part of the graph.")

                elif command == 2 :
                    v = int(input("Vertex: "))
                    if v not in graph_.inbound.keys() :
                        print("This vertex is not part of the graph.")
                    else :
                        print(graph_.remove_vertex(v))

        elif command == 10 :
            new_graph = graph_.copy_graph()
            file_name = "copied_graph.txt"
            write_into_file(new_graph, file_name)

        elif command == 11 :
            file_name = input("Please introduce the file name: ")
            if valid(file_name) :
                write_into_file(graph_, file_name)
            else :
                print("please introduce a valid file name")

        elif command == 12 :
            start = int(input("Source vertex: "))
            end = int(input("Target vertex: "))
            print(ford_bellman_algorithm(graph_, start, end))

        else :
            print("please introduce a valid option")


menu()
